package codigo.Fabricas;

import codigo.Furgao;
import codigo.Veiculo;

public class FabricaFurgao implements IFabricavel<Veiculo> {

    /* (non-Javadoc)
     * @see codigo.Fabricas.IFabricavel#criar(java.lang.String)
     */
    @Override
    public Veiculo criar(String dados) {
        String[] d = dados.split(";");
        Furgao f = new Furgao(d[0], Double.parseDouble(d[1]),Double.parseDouble(d[2]));
        return f;
    }
    
}
