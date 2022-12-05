package codigo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import codigo.Enums.Combustivel;
import codigo.Exceptions.ExceptionCombustivel;
import codigo.Exceptions.ExceptionRouteTooBig;

/**
 * Veiculo
 */
public abstract class Veiculo implements Serializable{
    
    // Atributos
    private static final long serialVersionUID = 1L;
    protected String placa;
    protected double valorVenda;
    protected Tanque TANQUE;
    protected double kmMedio;
    protected ArrayList<Rota> rotas;
    protected LinkedList<Gasto> custosAdicionais;

    //Métodos abstratos

    public abstract double calcularSeguro();

    public abstract double calcularIPVA();

    public abstract double calcularCustos();

    /**
     * 
     * @param tipo tipo do combustível
     * @throws ExceptionCombustivel se o tipo do combustivel nâo for adequado ao tipo veículo
     */
    public abstract void abastecer(Combustivel tipo) throws ExceptionCombustivel;

    /**
     * Relatório do veículo contendo: O tipo, a placa, número de rotas relaizadas , o total e os detalhes dos gastos
     * @return String
     */
    public abstract String gerarRelatorio();

    // Construtor

    Veiculo(String placa, double valorVenda, double kmMedio){
        this.placa = placa;
        this.valorVenda = valorVenda;
        this.kmMedio = kmMedio;
        this.rotas = new ArrayList<>();
        this.custosAdicionais = new LinkedList<>();
    }

    //Métodos

    
    public double quilometragem(){

        return this.rotas.stream()
        .mapToDouble(Rota :: getKmTotal)
        .sum();
    }

    /**
     * @return Soma dos custos adicionais
     */
    public double totalCustosAdicionais(){
        return this.custosAdicionais.stream()
        .mapToDouble(Gasto :: getValor)
        .sum();
    }

    private boolean validarRota(Rota rota) {
        if(rota.getKmTotal() <= this.TANQUE.distanciaPossivel())
            return true;
        else if(rota.getKmTotal() <= this.TANQUE.distanciaMaximaPossivel()) {
            try {
                this.abastecer(this.TANQUE.getCombustivel());
            } catch (ExceptionCombustivel e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    /**
     * 
     * @param rota
     * @return caso a rota tenha sido adicionada corretamente
     * @throws ExceptionRouteTooBig 
     */
    public boolean addRota(Rota rota) throws ExceptionRouteTooBig{
        if(this.validarRota(rota))
            return this.rotas.add(rota);
        else
        throw new ExceptionRouteTooBig();
        
    }

    public String getPlaca(){
        return this.placa;
    }


    public double getValorVenda() {
        return valorVenda;
    }

    public double getKmMedio(){
        return this.kmMedio;
    }
    

    public ArrayList<Rota>  getRotas(){
        return new ArrayList<Rota>(this.rotas);
    }

    
    
}
