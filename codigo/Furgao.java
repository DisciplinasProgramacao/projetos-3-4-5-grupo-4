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
    
    @Override
    public String gerarRelatorio() {
        return "Furgão :" +
        "Placa: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas" + this.rotas.size() + "\n" +
        "Total de Gastos: " + String.format("%02d",this.calcularCustos());
    }
}
