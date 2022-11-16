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
        
        double km_rodados = this.quilometragem();

        if(km_rodados == 0){
            return 0;
        }else{
            return IConstantsCarro.CUSTOALINHAMENTO_CARRO.getValor() 
            * (km_rodados / IConstantsCarro.KM_VISTORIA_CARRO.getValor());
        }
    
    }
    
    @Override
    public String gerarRelatorio() {
        return "Carro :" +
        "\nPlaca: " + this.getPlaca() + "\n"+
        "Número de Rotas realizadas: " + this.rotas.size() + "\n" +
        "Total de Gastos: " + this.calcularCustos() + "R$";
    }

    @Override
    public void abastecer(Combustivel tipo) {
        if(tipo.equals(Combustivel.ETANOL)){
            this.TANQUE.setCombustivel(tipo);
            this.TANQUE.abastecer();
        }else if(tipo.equals(Combustivel.GASOLINA)){
            this.TANQUE.setCombustivel(tipo);
            this.TANQUE.abastecer();
        }
        
    }
}
