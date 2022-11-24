package codigo.Fabricas;

import codigo.Caminhao;
import codigo.Veiculo;

public class FabricaCaminhao implements IFabricavel<Veiculo> {

    /* (non-Javadoc)
     * @see codigo.Fabricas.IFabricavel#criar(java.lang.String)
     */
    @Override
    public Veiculo criar(String dados) {
        String[] d = dados.split(";");
        Caminhao c = new Caminhao(d[0], Double.parseDouble(d[1]),Double.parseDouble(d[2]));
        return c;
    }
    
}
