package codigo;

public class Furgao extends Veiculo{
    @Override
    public double calcularSeguro() {
        return this.getValorVenda()*0.03;
        }

    @Override
    public double calcularIPVA() {
        return this.getValorVenda()*0.03;
    }

    @Override
    public double calcularCustos() {
        return 0;
    }
}
