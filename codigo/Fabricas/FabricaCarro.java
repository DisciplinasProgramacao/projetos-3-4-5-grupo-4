package codigo.Fabricas;

import codigo.Carro;
import codigo.Veiculo;

public class FabricaCarro implements IFabricavel<Veiculo> {

    /* (non-Javadoc)
     * @see codigo.Fabricas.IFabricavel#criar(java.lang.String)
     */
    @Override
    public Veiculo criar(String dados) {
        String[] d = dados.split(";");
        Carro c = new Carro(d[0], Double.parseDouble(d[1]),Double.parseDouble(d[2]));
        return c;
    }
    
}
