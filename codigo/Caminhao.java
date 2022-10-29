package codigo;

public class Caminhao extends Veiculo{
    
    //Construtor

    public Caminhao(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = TANQUE_CAMINHAO;
        
    }

    // Override

    @Override
    public double calcularSeguro() {
        return (this.valorVenda * SEGURO_CAMINHAO) + VALORADICIONALSEGURO_CAMINHAO;
    }

    @Override
    public double calcularIPVA() {
        return  this.valorVenda * IPVA_CAMINHAO;
    }

    @Override
    public double calcularCustos() {

        double km_rodados = this.quilometragem();

        if(km_rodados == 0){
            return 0;
        }

        double manutencao = km_rodados / KM_MANUTENCAO_CAMINHAO;
        double vistoria = km_rodados / KM_VISTORIA_CAMINHAO;

        return (CUSTOMANUTENCAO_CAMINHAO * manutencao) + (CUSTOVISTORIA_CAMINHAO * vistoria);
    }
    
    @Override
    public String gerarRelatorio() {
        return "Caminhão :" +
        "\nPlaca: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas: " + this.rotas.size() + "\n" +
        "Total de Gastos: " + this.calcularCustos() + "R$";
    }
}
