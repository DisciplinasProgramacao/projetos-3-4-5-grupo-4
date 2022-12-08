package codigo;

import codigo.Enums.Combustivel;
import codigo.Enums.FurgaoEnum;
import codigo.Exceptions.ExceptionCombustivel;

public class Furgao extends Veiculo{
    
    //Construtor

    public Furgao(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE= new Tanque(FurgaoEnum.TANQUE.getValor(),Combustivel.GASOLINA);
    }

    //Overrides

    @Override
    public double calcularSeguro() {
        return this.valorVenda * FurgaoEnum.SEGURO.getValor();
    }

    @Override
    public double calcularIPVA() {
        return this.valorVenda * FurgaoEnum.IPVA.getValor();
    }

    @Override
    public double calcularCustos() {

        if(this.quilometragem() == 0)
            return 0;
        else
            return this.custoAlinhamento() + this.custoVistoria() + this.totalCustosAdicionais();
    }
    
    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("\nVeículo : "+"Furgão"+"\n");
        relatorio.append("\nPlaca: " + this.getPlaca() + "\n");
        relatorio.append("Seguro: " + this.calcularSeguro() + "\n");
        relatorio.append("IPVA: " + this.calcularIPVA() + "\n");
        relatorio.append("Número de Rotas realizadas: " + this.rotas.size() + "\n" );
        relatorio.append("Total de Gastos: " + "R$" + this.calcularCustos() +
         "\n"+ "\n");
        relatorio.append("Detalhes dos gastos: "  + "\n"+ "\n");
        relatorio.append("Alinhamento: " + this.custoAlinhamento()+ "\n");
        relatorio.append("Vistoria: " + this.custoVistoria()+ "\n");
        

        for (Gasto gasto : this.custosAdicionais) {
            relatorio.append(gasto.getTipo()+ ": " + gasto.getValor()+ "\n");
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
        double alinhamento = km_rodados / FurgaoEnum.KM_VISTORIA.getValor();


        return  FurgaoEnum.CUSTOALINHAMENTO.getValor()   * alinhamento;
    }

    private double custoVistoria(){
        double km_rodados = this.quilometragem();
        double vistoria = km_rodados /FurgaoEnum.KM_VISTORIA.getValor();


        return FurgaoEnum.CUSTOVISTORIA.getValor() * vistoria;
    }

    
}
