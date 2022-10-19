package codigo;

public class Caminhao extends Veiculo{
    @Override
    public double calcularSeguro() {
        return this.getValorVenda()*0.02+2000;
    }

    @Override
    public double calcularIPVA() {
        return  this.getValorVenda()*0.01;
    }

    @Override
    public double calcularCustos() {
        return 0 ;
    }

    
}
