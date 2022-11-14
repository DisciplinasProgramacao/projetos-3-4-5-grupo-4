package codigo;

public class Carro extends Veiculo{

    //Construtor

    public Carro(String placa, double valorVenda, double kmMedio) {
        super(placa, valorVenda, kmMedio);
        this.TANQUE = new Tanque(TANQUE_CARRO,Combustivel.GASOLINA);
    }

    // Overrides

    @Override
    public double calcularSeguro() {
        return (this.valorVenda * SEGURO_CARRO) + VALORADICIONALSEGURO_CARRO;
    }

    @Override
    public double calcularIPVA() {
        return this.valorVenda * IPVA_CARRO;
    }

    @Override
    public double calcularCustos() {
        
        double km_rodados = this.quilometragem();

        if(km_rodados == 0){
            return 0;
        }else{
            return CUSTOALINHAMENTO_CARRO * (km_rodados / KM_VISTORIA_CARRO);
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
