package codigo;

import codigo.resources.Tanque;

public class Van extends Veiculo{

    // Construtor

    public Van(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = new Tanque(TANQUE_VAN);
    }

    //Overrides

    @Override
    public double calcularSeguro() {
        return this.valorVenda * IPVA_SEGURO_VAN_FURGAO;
    }

    @Override
    public double calcularIPVA() {
        return this.valorVenda * IPVA_SEGURO_VAN_FURGAO;
    }

    @Override
    public double calcularCustos() {
        
        double km_rodados = this.quilometragem();

        if(km_rodados == 0){
            return 0;
        }

        return (CUSTOALINHAMENTO_VAN_FURGAO + CUSTOVISTORIA_VAN_FURGAO ) * (km_rodados / KM_VISTORIA_VAN_FURGAO);
    }
    
    
    @Override
    public String gerarRelatorio() {
        return "Van :" +
        "\nPlaca: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas: " + this.rotas.size() + "\n" +
        "Total de Gastos: " + this.calcularCustos() + "R$";
    }
}
