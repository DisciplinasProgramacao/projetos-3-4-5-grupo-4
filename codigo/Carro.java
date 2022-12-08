package codigo;

import codigo.Enums.CarroEnum;
import codigo.Enums.Combustivel;
import codigo.Exceptions.ExceptionCombustivel;

public class Carro extends Veiculo {

    //Construtor

    public Carro(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = new Tanque(CarroEnum.TANQUE.getValor(),Combustivel.GASOLINA);
    }

    // Overrides

    @Override
    public double calcularSeguro() {
        return (this.valorVenda * CarroEnum.SEGURO.getValor()) 
        + CarroEnum.VALORADICIONALSEGURO.getValor();
    }

    @Override
    public double calcularIPVA() {
        return this.valorVenda * CarroEnum.IPVA.getValor();
    }

    @Override
    public double calcularCustos() {
        
        if(this.quilometragem() == 0)
            return 0;
        else
            return this.custoAlinhamento() + this.totalCustosAdicionais();
    
    }
    
    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("\nVeículo : "+"Carro"+"\n");
        relatorio.append("\nPlaca: " + this.getPlaca() + "\n");        
        relatorio.append("Seguro: " + this.calcularSeguro() + "\n");
        relatorio.append("IPVA: " + this.calcularIPVA() + "\n");
        relatorio.append("Número de Rotas realizadas: " + this.rotas.size() + "\n" );
        relatorio.append("Total de Gastos: " + "R$" + this.calcularCustos() +
         "\n"+ "\n");
        relatorio.append("Detalhes dos gastos: "  + "\n"+ "\n");
        relatorio.append("Alinhamento: " + this.custoAlinhamento()+ "\n");
        

        for (Gasto gasto : this.custosAdicionais) {
            relatorio.append(gasto.getTipo()+ ": " + gasto.getValor()+ "\n");
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
        double alinhamento = km_rodados / CarroEnum.KM_VISTORIA.getValor();


        return  CarroEnum.CUSTOALINHAMENTO.getValor()   * alinhamento;
    }

    
}
