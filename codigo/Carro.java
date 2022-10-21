package codigo;

public class Carro extends Veiculo{
    @Override
    public double calcularSeguro() {
        return 0;
    }

    @Override
    public double calcularIPVA() {
        return 0;
    }

    @Override
    public double calcularCustos() {
        return 0;
    }
    
    public String gerarRelatorio() {
        return "Carro :" +
        "Placa: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas" + this.rotas.size() + "\n" +
        "Total de Gastos: " + String.format("%02d",this.calcularCustos());
    }
}
