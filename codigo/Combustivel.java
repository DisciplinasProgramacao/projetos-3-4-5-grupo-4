package codigo;

public enum Combustivel {

    GASOLINA(4.80, 12.0),
    ETANOL(3.65, 8.0),
    DIESEL(6.65, 3.5);

    private double preco;
    private double consumo;

    Combustivel(double preco, double consumo){
        this.preco = preco;
        this.consumo = consumo;
    }

    public double getPreco(){
        return this.preco;
    }

    public double getConsumo(){
        return this.consumo;
    }
}
