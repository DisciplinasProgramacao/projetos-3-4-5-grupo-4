package codigo.Fabricas;

import java.util.HashMap;

public class ColecaoFabricas<t> {

    private HashMap<String, IFabricavel<t>> Confeccionaria;

    
    public ColecaoFabricas( ){
        Confeccionaria = new HashMap<>();
    }

    public void acrescentarFabrica(String chave, IFabricavel<t> fabrica){
        if(chave!="" && fabrica!=null)
            this.Confeccionaria.put(chave.toLowerCase(), fabrica);
    }

    public t criar(String chave, String dados){
        IFabricavel<t> fabrica = Confeccionaria.get(chave.toLowerCase());
        return fabrica.criar(dados);
    }

    // private void configurarFabricaReflexao(String arquivo) throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
    //     Scanner leitor = new Scanner(new File(arquivo));
    //     while(leitor.hasNextLine()){
    //         String[] dadosFabrica = leitor.nextLine().split(";");
    //         acrescentarFabrica(dadosFabrica[0], 
    //                                           (IFabricavel<t>)Class.forName(dadosFabrica[1])
    //                                           .getConstructor().newInstance());
    //     }
        
    //}
   
    
}
