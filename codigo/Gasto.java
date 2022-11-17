package codigo;

public class Gasto {
    private String tipo;
    private double valor;

   

    Gasto(String tipo, double valor){
        this.tipo =tipo ;
        this.valor = valor;
    }   

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }
}
