package codigo;

import java.util.ArrayList;

public class Van extends Veiculo{

    Van(){super();}

    public Van(String placa, ArrayList<Rota> rotas, double valorVenda){
        this.setPlaca(placa);
        this.setTANQUE(60);
        this.setValorVenda(valorVenda);
        for(Rota rota : rotas) {
            addRota(rota);
        }
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
