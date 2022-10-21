package codigo;

public class Van extends Veiculo{
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
    
    @Override
    public String gerarRelatorio() {
        return "Van :" +
        "Placa: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas" + this.rotas.size() + "\n" +
        "Total de Gastos: " + String.format("%02d",this.calcularCustos());
    }
}
