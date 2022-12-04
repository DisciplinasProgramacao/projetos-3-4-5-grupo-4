package codigo;

public class Carro extends Veiculo {

    //Construtor

    public Carro(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = new Tanque((int)IConstantsCarro.TANQUE_CARRO.getValor(),Combustivel.GASOLINA);
    }

    // Overrides

    @Override
    public double calcularSeguro() {
        return (this.valorVenda * IConstantsCarro.SEGURO_CARRO.getValor()) 
        + IConstantsCarro.VALORADICIONALSEGURO_CARRO.getValor();
    }

    @Override
    public double calcularIPVA() {
        return this.valorVenda * IConstantsCarro.IPVA_CARRO.getValor();
    }

    @Override
    public double calcularCustos() {
        
        if(this.quilometragem() == 0)
            return 0;
        else
            return this.custoAlinhamento() + this.custosAdicionais();
    
    }
    
    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("Veículo : "+"Carro"+"\n");
        relatorio.append("\nPlaca: " + this.getPlaca() + "\n");
        relatorio.append("Número de Rotas realizadas: " + this.rotas.size() + "\n" );
        relatorio.append("Total de Gastos: " + "R$" + String.format("%02d", this.calcularCustos()) +
         "\n"+ "\n");
        relatorio.append("Detalhes dos gastos: "  + "\n"+ "\n");
        relatorio.append("Alinhamento: " + this.custoAlinhamento());
        

        for (Gasto gasto : this.custosAdicionais) {
            relatorio.append(gasto.getTipo()+ ": " + gasto.getValor());
        }

        return relatorio.toString();
    }

    
    @Override
    public void abastecer(Combustivel tipo) throws ExceptionCombustivel {
        if(tipo == Combustivel.GASOLINA || tipo == Combustivel.ETANOL )
            this.custosAdicionais.add(new Gasto("abastecimento", this.TANQUE.abastecer(tipo)));
        else
            throw new ExceptionCombustivel();
    }

    private double custoAlinhamento(){
        double km_rodados = this.quilometragem();
        double alinhamento = km_rodados / IConstantsCarro.KM_VISTORIA_CARRO.getValor();


        return  IConstantsCarro.CUSTOALINHAMENTO_CARRO.getValor()   * alinhamento;
    }

    private double custosAdicionais(){
        
        return this.custosAdicionais.stream()
        .mapToDouble(Gasto :: getValor)
        .sum();
        
    }
}
