package codigo.Fabricas;

import codigo.Rota;

public class FabricaRota implements IFabricavel {

    @Override
    public Rota criar(String dados) {
        
        String split[] = dados.split(";");

        return new Rota(split[1],Double.parseDouble(split[0]));
    }
    
}
