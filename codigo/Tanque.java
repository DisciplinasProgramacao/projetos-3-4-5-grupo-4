package codigo;

public class Tanque {

    private int capacidade;
    private Combustivel combustivel;
    private double qtnAtual;

            //ICombustivel tipo
    public Tanque(int capacidade, Combustivel tipo){
        this.capacidade = capacidade;
        combustivel = tipo;
        
    }

    /**
     * Calcula a distância  pode pecorrer com o valor do tanque atual sem abastecer
     * @return Double
     */
    public double distanciaPossivel(){
        return this.combustivel.getConsumo() * this.qtnAtual;
    }

    /**
     * Calcula a distância máxima  pode pecorrer com o tanque cheio
     * @return Double
     */
    public double distanciaMaximaPossivel(){
        return this.combustivel.getConsumo() * this.capacidade;
    }

    /**
     * Coloca o Tanque na quantidade máxima
     * 
     */
    public void abastecer(){
        this.qtnAtual = this.capacidade;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public Combustivel getCombustivel(){
        return this.combustivel;
    }
}
