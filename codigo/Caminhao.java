package codigo;
import java.util.ArrayList;
public class Caminhao extends Veiculo{

    Caminhao(){super();}
    public Caminhao(String placa, ArrayList<Rota> rotas, double valorVenda){
        this.setPlaca(placa);
        this.setTANQUE(250);
        this.setValorVenda(valorVenda);
        for(Rota rota : rotas) {
            addRota(rota);
        }
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
