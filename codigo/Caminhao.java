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

        if(this.quilometragem() == 0)
            return 0;
        else
            return this.custoManutencao() + this.custoVistoria() + this.custosAdicionais();
    }
    
    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("Veículo : "+"Caminhão"+"\n");
        relatorio.append("\nPlaca: " + this.getPlaca() + "\n");
        relatorio.append("Número de Rotas realizadas: " + this.rotas.size() + "\n" );
        relatorio.append("Total de Gastos: " + "R$" + String.format("%02d", this.calcularCustos()) +
         "\n"+ "\n");
        relatorio.append("Detalhes dos gastos: "  + "\n"+ "\n");
        relatorio.append("Manutenção: " + this.custoManutencao());
        relatorio.append("Vistoria: " + this.custoVistoria());

        for (Gasto gasto : this.custosAdicionais) {
            relatorio.append(gasto.getTipo()+ ": " + gasto.getValor());
        }

        return relatorio.toString();
    }

    @Override
    public void abastecer(Combustivel tipo)throws ExceptionCombustivel {
        if(tipo == Combustivel.DIESEL)
            this.custosAdicionais.add(new Gasto("abastecimento", this.TANQUE.abastecer(tipo)));
        else
            throw new ExceptionCombustivel();
        
    }

    private double custoManutencao(){
        double km_rodados = this.quilometragem();
        double manutencao = km_rodados / IConstantsCaminhao.KM_MANUTENCAO_CAMINHAO.getValor();


        return  IConstantsCaminhao.CUSTOMANUTENCAO_CAMINHAO.getValor() * manutencao;
    }

    private double custoVistoria(){
        double km_rodados = this.quilometragem();
         double vistoria = km_rodados /IConstantsCaminhao.KM_VISTORIA_CAMINHAO.getValor();


        return IConstantsCaminhao.CUSTOVISTORIA_CAMINHAO.getValor() * vistoria;
    }

    private double custosAdicionais(){
        
        return this.custosAdicionais.stream()
        .mapToDouble(Gasto :: getValor)
        .sum();
        
    }
}
