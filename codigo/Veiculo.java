package codigo;

import java.util.ArrayList;

/**
 * Veiculo
 */
public abstract class  Veiculo {

    private String placa;
    private double valorVenda;
    private double TANQUE;
    private double IPVA;
    private double seguro;
    private static double kmMedio;
    private ArrayList<Rota> Rotas;

    
    public abstract double calcularSeguro();

    
    public abstract double calcularIPVA();

    public abstract double calcularCustos();

    private boolean validarRota(){
        return false;
    }

    public String gerarRelatorio(){
        return "";
    }

    public boolean addRota(Rota rota){
        return this.Rotas.add(rota);
    }

    public String getPlaca(){
        return this.placa;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }
    
}