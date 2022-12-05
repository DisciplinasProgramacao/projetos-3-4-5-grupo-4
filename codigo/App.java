package codigo;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App{

    private static final String PATH_OPCOES_APP = "config/opcoesApp.txt";
    private static final String VEICULOS_TXT = "resources/veiculos.txt";
    private static final String SALVAR_VEICULOS = "resources/veiculosSalvos.txt";
    private static final String ESCOLHAS_USUARIO = "config/escolhaUsuario.txt";

    private static final App app = new App();
    private static Frota frota = new Frota();  
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        
        interfaceUsuario();

    }

    /**
     * uma interface pública para os acessos aos métodos.
     */
    public static void interfaceUsuario(){
        
        String escolha;
        boolean continuar;

        do{

            imprimiOpcoes();
            escolha = teclado.nextLine();
            continuar = trataOpcoesReflexao(escolha);

        }while(continuar);


    }
    public static void localizaVeiculoFrota(){

        Veiculo veiculo = retornaVeiculoFrota();
        
        if(veiculo != null){
            System.out.println("Veículo existe"); 
        }
        
    }
    public static Veiculo retornaVeiculoFrota(){

        System.out.println("Informe a placa do veículo");
        String placa = teclado.nextLine();

        Veiculo veiculo = retornaVeiculo(placa, "placaFinalizar");

        return veiculo;

    }
    public static void gerarRelatorio(){
    
        Veiculo veiculo = retornaVeiculoFrota();

        if(veiculo != null){
            veiculo.gerarRelatorio();
        }else{
            System.out.println("Veículo não existe");
        }

    }

    /**
     * @param placa -> recebe a placa do veículo a ser procurado
     * @param tratamento -> dependendo do método que chama esta função ele devolve um resultado diferente
     *, então, para que cada tratamento seja feito em uma mesma função, ele trata cada caso de um jeito específico
     * @return -> retorna o veículo achado
     */
    private static Veiculo retornaVeiculo(String placa, String tratamento){

        if(frota.getVeiculos().isEmpty()){
            System.out.println("Não existe veículos cadastrados");
        }


        Veiculo veiculo = frota.localizarVeiculo(placa);

        if(veiculo != null){
            return veiculo;
        }

        if(tratamento.contains("placa")){

            System.out.println("Veiculo não existe");

            if(tratamento.contains("Finalizar")){
                return null;
            }else{
                System.out.println("Informe uma placa válida: ");
                String placa_nova = teclado.nextLine();
                retornaVeiculo(placa_nova, "incluirRota");
            }
        }

        return null;
        

    }

    /**
     * pega as informações para incluir uma rota
     */
    public static void incluirRota(){

        System.out.println("Informe a placa do veículo que será feita a rota: ");
        String placa = teclado.nextLine();
        Veiculo veiculo = retornaVeiculo(placa, "placa");
        System.out.println("Informe o tamanho da rota: ");
        String km_total = teclado.nextLine();
        System.out.println("Informe a data que foi feita a rota: ");
        String data = teclado.nextLine();

        criaRota(data, km_total, veiculo);

    }

    /**
     * @param data -> recebe a data da rota
     * @param km_total -> recebe o tamanho da rota
     * @param veiculo -> recebe o veículo que fez a rota
     */
    private static void criaRota(String data, String km_total, Veiculo veiculo){

        veiculo.addRota(new Rota(data, Double.parseDouble(km_total)));

    }

    /**
     * imprimi as opções disponíveis ao usuário
     */
    private static void imprimiOpcoes(){

        int posicao = 0;

        try (Scanner leitor = new Scanner(new File(PATH_OPCOES_APP))) {

            while(leitor.hasNext()){

                String linha = leitor.nextLine();

                System.out.println(posicao + "- " + linha);

                posicao++;
            }


        } catch (FileNotFoundException e) {
            
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }

    }

    /**
     * @param escolha -> recebe a esoclha da opção do usuário
     * @return -> retorna true para caso seja para continuar a aplicação e false para encerrar
     */
    @Deprecated
    private static boolean trataOpcoes(String escolha){

        switch(escolha){
            
            case "0":
                return false;
            case "1":
                carregaArquivo();
                return true;
            case "2":
                salvarArquivo();
                return true;
            case "3":
                criaVeiculo();
                return true;
            case "4":
                incluirRota();
                return true;
            case "5":
                localizaVeiculoFrota();
                return true;
            case "6":
                gerarRelatorio();
                return true;
            case "7":
                imprimiVeiculosComAs3MaioresRotas();
                return true;
            case "8":
                imprimiKmMedia();
                return true;
            case "9":
                imprimiListaOrdenadaDecrescente();
                return true;                        
            default:
                System.out.println("Escolha inválida");
                return true;
        }

        
    }

    /**
     */
    public static void carregaArquivo(){

        File file = new File(VEICULOS_TXT);

        try {
            
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha = bufferedReader.readLine();
            
            do{

                String[] dados = linha.split(";");
                String tipo = dados[0];
                String placa = dados[1];
                String valor_venda = dados[2];
                String km_medio = dados[3];

                criarVeiculo(tipo, placa, valor_venda, km_medio);

                linha = bufferedReader.readLine();

            }while(linha != null);

            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            throw new RuntimeException("ERROR: " + e);
        }
    }

    /**
     * método criado para salvar um arquivo com os dados do vetor de veículos
     */
    public static void salvarArquivo(){

        File file = new File(SALVAR_VEICULOS);

        try {

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Veiculo veiculo : frota.getVeiculos()){
                
                String classe_imprimir = veiculo.getClass().getName();

                if(classe_imprimir.contains("Carro")){
                    classe_imprimir = "Carro";
                }else if(classe_imprimir.contains("Van")){
                    classe_imprimir = "Van";
                }else if(classe_imprimir.contains("Furgao")){
                    classe_imprimir = "Furgao";
                }else if(classe_imprimir.contains("Caminhao")){
                    classe_imprimir = "Caminhao";
                }
                

                bufferedWriter.write(classe_imprimir + ";" + veiculo.getPlaca() + ";" + 
                veiculo.getValorVenda() + ";" + veiculo.getKmMedio());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        
        } catch (IOException e) {
            
            throw new RuntimeException("ERROR: " + e);
        }
    }

    /**
     * cria um veículo através de dados colteados pelo usuário
     */
    public static void criaVeiculo(){

        System.out.println("Informe o tipo do veículo: ");
        String tipo = teclado.nextLine();
        System.out.println("Informe a placa do veículo: ");
        String placa = teclado.nextLine();
        System.out.println("Informe o valor de venda do veículo: ");
        String valor_venda = teclado.nextLine();
        System.out.println("Informe o km médio do veículo: ");
        String km_medio = teclado.nextLine();

        criarVeiculo(tipo, placa, valor_venda, km_medio);

    }

    /**
     * @param tipo -> recebe o tipo do veículo a ser criado
     * @param placa -> placa do veículo
     * @param valor_venda -> valor de venda do veículo
     * @param km_medio -> valor de kilometragem média por litro do veículo
     * @return -> Retorna o veículo criado
     */
    private static Veiculo criarVeiculo(String tipo, String placa, String valor_venda, String km_medio){

        tipo = tipo.toLowerCase();

        double valor_venda_convertido = Double.parseDouble(valor_venda);
        double km_medio_convertido = Double.parseDouble(km_medio);
        
        switch(tipo){

            case "carro":
                frota.addVeiculo(new Carro(placa, valor_venda_convertido, km_medio_convertido));
                break;
            case "van":
                frota.addVeiculo(new Van(placa, valor_venda_convertido, km_medio_convertido));
                break;
            case "furgao":
            case "furgão":
                frota.addVeiculo(new Furgao(placa, valor_venda_convertido, km_medio_convertido));
                break;
            case "caminhao":
            case "caminhão":
                frota.addVeiculo(new Caminhao(placa, valor_venda_convertido, km_medio_convertido));
                break;
            default:
                System.out.println("Este tipo não existe, informe um válido (Carro, Van, Furgao ou Caminhao): ");
                String novo_tipo = teclado.nextLine();
                criarVeiculo(novo_tipo, placa, valor_venda, km_medio);                
        }

        return null;

    }

    public static void imprimiVeiculosComAs3MaioresRotas(){

        List<Veiculo> veiculos = veiculosMaioresRotas(frota, 3);

        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo.gerarRelatorio());
        }
    }

    public static void imprimiKmMedia(){

        System.out.println("Km médio da frota: "  + kmMediaTodasRotas(frota));

    }

    public static void imprimiListaOrdenadaDecrescente(){

        custosDecrescentes(frota).forEach(v -> System.out.println(v.gerarRelatorio()));

    }

    /** STREAMS INÍCIO */

    // Ainda a testar !
    
    public static double kmMediaTodasRotas(Frota frota){
        
        double media = frota.getVeiculos().stream()
            .mapToDouble(Veiculo :: quilometragem)
            .average()
            .getAsDouble();

        return media;
    }

    /**
     * @param frota -> recebe a frota de veículos a ser localizada
     * @param limit -> limite de quantos veículos devem ser retornados 
     * @return -> lista de veículos ordenadas
     */
    private static List<Veiculo> veiculosMaioresRotas(Frota frota, int limit){
        
        if(frota.getVeiculos().size() >= limit){

            List<Veiculo> veiculos = frota.getVeiculos()
                    .stream()
                    .sorted(((v1, v2) ->{
                        double maior_rota_v1 = retornaMaiorRotaVeiculo(v1);
                        double maior_rota_v2 = retornaMaiorRotaVeiculo(v2);

                        if(maior_rota_v1 > maior_rota_v2){
                            return 1;
                        }else{
                            return -1;
                        }
                    }))
                    .limit(limit)
                    .collect(Collectors.toList());

            return veiculos;

        }else{
            throw new RuntimeException("A frota não possui veiculos suficientes para consulta");
        }

        
    }

    public static double retornaMaiorRotaVeiculo(Veiculo veiculo){

        OptionalDouble maior_rota =  veiculo.getRotas()
                .stream()
                .mapToDouble(Rota::getKmTotal)
                .max()
                ;

        if(maior_rota.isPresent()){
            return maior_rota.getAsDouble();
        }else{
            return 0;
        }





    }

    public static List<Veiculo> custosDecrescentes(Frota frota){

        return frota.getVeiculos()
                .stream()
                .sorted((v1,v2) -> ((v1.calcularCustos()+v1.totalCustosAdicionais())<(v2.calcularCustos()+v2.totalCustosAdicionais()))?1:-1)
                .collect(Collectors.toList());
    }

    /** STREAMS FIM */

    // reflexão

    private static boolean trataOpcoesReflexao(String opcao){

        String classe = "codigo.App";

        try {

            Scanner leitor = new Scanner(new File(ESCOLHAS_USUARIO));

            Method[] metodos = Class.forName(classe).getMethods();

            while(leitor.hasNext()){

                String[] metodoTxt = leitor.nextLine().split(";");
                
                String chave = metodoTxt[0];
                String valor = metodoTxt[1];
                String retorno = metodoTxt[2];

                for(Method metodo : metodos){

                    if(metodo.toString().contains(valor) && chave.equals(opcao)){
                        metodo.invoke(app);
                        return retorno.equals("true");
                    }                    
                }
            }

        } catch (SecurityException | ClassNotFoundException | FileNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return false;

    }



}