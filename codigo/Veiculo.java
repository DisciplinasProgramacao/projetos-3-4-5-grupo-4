package codigo;


import java.util.ArrayList;

/**
 * Veiculo
 */
public abstract class Veiculo{
    
    // Atributos

    protected String placa;
    protected double valorVenda;
    protected Tanque TANQUE;
    protected double kmMedio;
    protected ArrayList<Rota> rotas;

    //Métodos abstratos

    public abstract double calcularSeguro();

    public abstract double calcularIPVA();

    public abstract double calcularCustos();

    /**
     * Relatório do veículo contendo: O tipo, a placa, número de rotas relaizadas e o total de gastos
     * @return String
     */
    public abstract String gerarRelatorio();

    // Construtor

    Veiculo(String placa, double valorVenda, double kmMedio){
        this.placa = placa;
        this.valorVenda = valorVenda;
        this.kmMedio = kmMedio;
        this.rotas = new ArrayList<>();
    }

    //Métodos

    public double quilometragem(){
        
        double km=0;

        for(int i = 0 ; i < this.rotas.size() ; i++){
            km += this.rotas.get(i).getKmTotal();
        }
        
        return km;
    }

    private boolean validarRota(Rota rota){
        
        if(rota.getKmTotal() < this.TANQUE.distanciaPossivel())
            return true;
        else
            return false;
    }

    public boolean addRota(Rota rota){
        if(this.validarRota(rota))
            return this.rotas.add(rota);
        else
            return false;
        
    }

    public String getPlaca(){
        return this.placa;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public double getKmMedio() {
        return kmMedio;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public ArrayList<Rota>  getRotas(){
        return this.rotas;
    }

    /**
     * Coloca o Tanque na quantidade máxima
     * 
     */
    public abstract void abastecer(Combustivel tipo);
    
}
