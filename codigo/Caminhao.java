package codigo;

import codigo.resources.Tanque;

public class Caminhao extends Veiculo{
    Caminhao(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = new Tanque(250);
    }

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
        double custoManutencao = 1000.0;
        double custoVistoria = 1000.0;
        int quociente1 =(int) this.quilometragem()/20000;
        int quociente2 =(int) this.quilometragem()/30000;

        double custoTotal = custoManutencao*quociente1 + custoVistoria*quociente2;
        return custoTotal;
    }
    
    @Override
    public String gerarRelatorio() {
        return "Caminhão :" +
        "Placa: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas" + this.rotas.size() + "\n" +
        "Total de Gastos: " + String.format("%02d",this.calcularCustos());
    }
}
