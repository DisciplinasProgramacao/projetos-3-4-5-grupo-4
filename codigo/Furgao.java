package codigo;

public class Furgao extends Veiculo{
    
    //Construtor

    public Furgao(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = TANQUE_FURGAO;
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
        return "Furgão :" +
        "\nPlaca: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas: " + this.rotas.size() + "\n" +
        "Total de Gastos: " + this.calcularCustos() + "R$";
    }
}
