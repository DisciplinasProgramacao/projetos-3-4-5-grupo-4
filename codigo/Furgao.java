package codigo;

public class Furgao extends Veiculo{
    
    //Construtor

    public Furgao(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE= new Tanque((int)IConstantsVanFurgao.TANQUE_FURGAO.getValor(),Combustivel.GASOLINA);
    }

    //Overrides

    @Override
    public double calcularSeguro() {
        return this.valorVenda * IConstantsVanFurgao.IPVA_SEGURO_VAN_FURGAO.getValor();
    }

    @Override
    public double calcularIPVA() {
        return this.valorVenda * IConstantsVanFurgao.IPVA_SEGURO_VAN_FURGAO.getValor();
    }

    @Override
    public double calcularCustos() {

        if(this.quilometragem() == 0)
            return 0;
        else
            return this.custoAlinhamento() + this.custoVistoria() + this.custosAdicionais();
    }
    
    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("Veículo : "+"Furgão"+"\n");
        relatorio.append("\nPlaca: " + this.getPlaca() + "\n");
        relatorio.append("Número de Rotas realizadas: " + this.rotas.size() + "\n" );
        relatorio.append("Total de Gastos: " + "R$" + String.format("%02d", this.calcularCustos()) +
         "\n"+ "\n");
        relatorio.append("Detalhes dos gastos: "  + "\n"+ "\n");
        relatorio.append("Alinhamento: " + this.custoAlinhamento());
        relatorio.append("Vistoria: " + this.custoVistoria());
        

        for (Gasto gasto : this.custosAdicionais) {
            relatorio.append(gasto.getTipo()+ ": " + gasto.getValor());
        }

        return relatorio.toString();
    }

    @Override
    public void abastecer(Combustivel tipo) throws ExceptionCombustivel{
        if(tipo == Combustivel.GASOLINA)
            this.custosAdicionais.add(new Gasto("abastecimento", this.TANQUE.abastecer(tipo)));
        else
            throw new ExceptionCombustivel();  
    }

    private double custoAlinhamento(){
        double km_rodados = this.quilometragem();
        double alinhamento = km_rodados / IConstantsVanFurgao.KM_VISTORIA_VAN_FURGAO.getValor();


        return  IConstantsVanFurgao.CUSTOALINHAMENTO_VAN_FURGAO.getValor()   * alinhamento;
    }

    private double custoVistoria(){
        double km_rodados = this.quilometragem();
        double vistoria = km_rodados /IConstantsVanFurgao.KM_VISTORIA_VAN_FURGAO.getValor();


        return IConstantsVanFurgao.CUSTOVISTORIA_VAN_FURGAO.getValor() * vistoria;
    }

    private double custosAdicionais(){
        
        return this.custosAdicionais.stream()
        .mapToDouble(Gasto :: getValor)
        .sum();
        
    }
}
