package codigo.resources;

public class Tanque {

    private int capacidade;
    private ICombustivel combustivel;
    private double qtnAtual;

            //ICombustivel tipo
    public Tanque(int capacidade){
        this.capacidade = capacidade;
        //combustivel = new tipo();
        
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
     * Caso passe uma quantidade maior que o tanque aguente, o valor excedente é ignorado
     * @param litros -> quantidade que vai abastecer
     * @return o valor final no tanque
     */
    public double abastecer(double litros){
        
        if(litros > this.capacidade){
            this.qtnAtual = this.capacidade;
            return this.qtnAtual;
        }
        else{
            this.qtnAtual += litros;
            return this.qtnAtual; 
        }

        
    }
}
