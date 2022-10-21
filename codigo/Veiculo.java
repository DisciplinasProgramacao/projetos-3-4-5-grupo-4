package codigo;


import java.util.ArrayList;

/**
 * Veiculo
 */
public abstract class  Veiculo {

    private String placa;
    private double valorVenda;
    protected double TANQUE;
    


    private  double kmMedio;
    protected ArrayList<Rota> rotas;

    
    public abstract double calcularSeguro();

    
    public abstract double calcularIPVA();

    public abstract double calcularCustos();

    private boolean validarRota(Rota rota){
        double kmMaxima = this.TANQUE * this.kmMedio;
        if(rota.getKmTotal() < kmMaxima)
            return true;
        else
            return false;
    }

    /**
     * Relatório do veículo contendo: O tipo, a placa, número de rotas relaizadas e o total de gastos
     * @return String
     */
    public abstract String gerarRelatorio();

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

    public double getTANQUE() {
        return TANQUE;
    }


    public void setTANQUE(double tANQUE) {
        TANQUE = tANQUE;
    }

    
    
}
