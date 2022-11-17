package codigo;

public class Caminhao extends Veiculo{
    
    //Construtor

    public Caminhao(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = new Tanque((int)IConstantsCaminhao.TANQUE_CAMINHAO.getValor(),Combustivel.DIESEL);
    }

    // Override

    @Override
    public double calcularSeguro() {
        return (this.valorVenda * IConstantsCaminhao.SEGURO_CAMINHAO.getValor()) + 
        IConstantsCaminhao.VALORADICIONALSEGURO_CAMINHAO.getValor();
    }

    @Override
    public double calcularIPVA() {
        return  this.valorVenda * IConstantsCaminhao.IPVA_CAMINHAO.getValor();
    }

    @Override
    public double calcularCustos() {

        double km_rodados = this.quilometragem();

        if(km_rodados == 0){
            return 0;
        }

        double manutencao = km_rodados / IConstantsCaminhao.KM_MANUTENCAO_CAMINHAO.getValor();
        double vistoria = km_rodados /IConstantsCaminhao.KM_VISTORIA_CAMINHAO.getValor();

        return (IConstantsCaminhao.CUSTOMANUTENCAO_CAMINHAO.getValor() * manutencao) + 
        (IConstantsCaminhao.CUSTOVISTORIA_CAMINHAO.getValor() * vistoria);
    }
    
    @Override
    public String gerarRelatorio() {
        return "Caminhão :" +
        "\nPlaca: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas: " + this.rotas.size() + "\n" +
        "Total de Gastos: " + this.calcularCustos() + "R$";
    }

    @Override
    public void abastecer(Combustivel tipo)throws ExceptionCombustivel {
        if(tipo == Combustivel.DIESEL)
            this.custosAdicionais.add(new Gasto("abastecimento", this.TANQUE.abastecer(tipo)));
        else
            throw new ExceptionCombustivel();
        
    }
}
