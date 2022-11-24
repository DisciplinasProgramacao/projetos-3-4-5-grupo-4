package codigo.Fabricas;

import codigo.Van;
import codigo.Veiculo;

public class FabricaVan implements IFabricavel<Veiculo>{

    /* (non-Javadoc)
     * @see codigo.Fabricas.IFabricavel#criar(java.lang.String)
     */
    @Override
    public Veiculo criar(String dados) {
        String[] d = dados.split(";");
        Van v = new Van(d[0], Double.parseDouble(d[1]),Double.parseDouble(d[2]));
        return v;
    }
    
}
