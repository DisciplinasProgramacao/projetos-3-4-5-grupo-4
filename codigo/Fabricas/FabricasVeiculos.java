package codigo.Fabricas;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import codigo.Veiculo;

/**
 * já possui as fabricas dos veiculos do sistema
 */
public class FabricasVeiculos extends ColecaoFabricas<Veiculo> {

    public FabricasVeiculos()  {
        super();
        configurarFabrica();
    }
    

    private void configurarFabrica() {
       
        this.acrescentarFabrica("carro",new FabricaCarro());
        this.acrescentarFabrica("caminhao",new FabricaCaminhao());
        this.acrescentarFabrica("van",new FabricaVan());
        this.acrescentarFabrica("furgao",new FabricaFurgao());
    }

    // private void configurarFabricaReflexao(String file) throws FileNotFoundException{
    //     Scanner leitor = new Scanner(new File(file));
    //     while(leitor.hasNextLine()){
    //         String[] dadosFabrica = leitor.nextLine().split(";");
    //         this.acrescentarFabrica(dadosFabrica[0], 
    //                                           (IFabricavel<Veiculo>)Class.forName(dadosFabrica[1])
    //                                           .getConstructor().newInstance());
    //     }
    // }
}
