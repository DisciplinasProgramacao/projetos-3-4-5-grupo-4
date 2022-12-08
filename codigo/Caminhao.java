package codigo;

import codigo.Enums.CaminhaoEnum;
import codigo.Enums.Combustivel;
import codigo.Exceptions.ExceptionCombustivel;

public class Caminhao extends Veiculo{
    
    //Construtor

    public Caminhao(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = new Tanque(CaminhaoEnum.TANQUE.getValor(),Combustivel.DIESEL);
    }

    // Override

    @Override
    public double calcularSeguro() {
        return (this.valorVenda * CaminhaoEnum.SEGURO.getValor()) + 
        CaminhaoEnum.VALORADICIONALSEGURO.getValor();
    }

    @Override
    public double calcularIPVA() {
        return  this.valorVenda * CaminhaoEnum.IPVA.getValor();
    }

    @Override
    public double calcularCustos() {

        if(this.quilometragem() == 0)
            return 0;
        else
            return this.custoManutencao() + this.custoVistoria() + this.totalCustosAdicionais();
    }
    
    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("\nVeículo : "+"Caminhão"+"\n");
        relatorio.append("Placa: " + this.getPlaca() + "\n");
        relatorio.append("Seguro: " + this.calcularSeguro() + "\n");
        relatorio.append("IPVA: " + this.calcularIPVA() + "\n");
        relatorio.append("Número de Rotas realizadas: " + this.rotas.size() + "\n" );
        relatorio.append("Total de Gastos: " + "R$" + this.calcularCustos() +
         "\n"+ "\n");
        relatorio.append("Detalhes dos gastos: "  + "\n"+ "\n");
        relatorio.append("Manutenção: " + this.custoManutencao()+ "\n");
        relatorio.append("Vistoria: " + this.custoVistoria()+ "\n");

        for (Gasto gasto : this.custosAdicionais) {
            relatorio.append(gasto.getTipo()+ ": " + gasto.getValor() + "\n");
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
        double manutencao = km_rodados / CaminhaoEnum.KM_MANUTENCAO.getValor();


        return  CaminhaoEnum.CUSTOMANUTENCAO.getValor() * manutencao;
    }

    private double custoVistoria(){
        double km_rodados = this.quilometragem();
         double vistoria = km_rodados /CaminhaoEnum.KM_VISTORIA.getValor();


        return CaminhaoEnum.CUSTOVISTORIA.getValor() * vistoria;
    }

}
