package codigo;

import codigo.Enums.Combustivel;

public class Tanque {

    private int capacidade;
    private Combustivel combustivel;
    private double qtnAtual;

    // ICombustivel tipo
    public Tanque(int capacidade, Combustivel tipo) {
        this.capacidade = capacidade;
        combustivel = tipo;

    }

    /**
     * Calcula a distância pode pecorrer com o valor do tanque atual sem abastecer
     * 
     * @return Double
     */
    public double distanciaPossivel() {
        return this.combustivel.getConsumo() * this.qtnAtual;
    }

    /**
     * Calcula a distância máxima pode pecorrer com o tanque cheio
     * 
     * @return Double
     */
    public double distanciaMaximaPossivel() {
        return this.combustivel.getConsumo() * this.capacidade;
    }

    /**
     * Coloca o tanque no máximo possível
     * @param tipo tipo do combustível
     * @return retorna o custo em double do abastecimento
     */
    public double abastecer(Combustivel tipo) {
        double custo  = (this.capacidade - this.qtnAtual) * tipo.getPreco();
        this.qtnAtual = this.capacidade;
        return custo;
    }

    public void setCombustivel(Combustivel tipo) {
        this.combustivel = tipo;
    }

    public Combustivel getCombustivel() {
        return this.combustivel;
    }
}
