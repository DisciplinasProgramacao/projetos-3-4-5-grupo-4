package codigo;

import java.util.ArrayList;

public class Carro extends Veiculo{

    Carro(){super();}

    Carro(String placa, ArrayList<Rota> rotas){
        setPlaca(placa);
        setTANQUE(50);
        for(Rota rota : rotas) {
            addRota(rota);
        }
    }

    @Override
    public double calcularSeguro() {
        return this.getValorVenda()*0.05+3000;
    }

    @Override
    public double calcularIPVA() {
        return this.getValorVenda()*0.04;
    }

    @Override
    public double calcularCustos() {
        double custoAlinhamento = 80.0;
        int quociente =(int) this.quilometragem()/10000;
        return custoAlinhamento * quociente;
    }
    
    public String gerarRelatorio() {
        return "Carro :" +
        "Placa: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas" + this.rotas.size() + "\n" +
        "Total de Gastos: " + String.format("%02d",this.calcularCustos());
    }
}
