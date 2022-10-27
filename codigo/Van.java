package codigo;



public class Van extends Veiculo{


    Van(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        //TODO Auto-generated constructor stub
    }

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
        double custoAlinhamento = 120.0;
        double custoVistoria = 500.0;
        int quociente =(int) this.quilometragem()/10000;
        double custoTotal = (custoAlinhamento + custoVistoria ) * quociente;
        return custoTotal;
    }
    
    
    @Override
    public String gerarRelatorio() {
        return "Van :" +
        "Placa: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas" + this.rotas.size() + "\n" +
        "Total de Gastos: " + String.format("%02d",this.calcularCustos());
    }
}
