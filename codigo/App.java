package codigo;

import codigo.Exceptions.ExceptionRouteTooBig;
import codigo.Fabricas.FabricasVeiculos;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App{

    private static final String PATH_OPCOES_APP = "codigo/config/opcoesApp.txt";
    private static final String VEICULOS_TXT = "codigo/resources/veiculos.txt";
    private static final String SALVAR_VEICULOS = "codigo/resources/veiculosSalvos.txt";
    private static final String ESCOLHAS_USUARIO = "codigo/config/escolhaUsuario.txt";

    private static final App APP = new App();
    private static Frota FROTA = new Frota();
    private static Scanner TECLADO = new Scanner(System.in);

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
            escolha = TECLADO.nextLine();
            continuar = trataOpcoesReflexao(escolha);

        }while(continuar);


    }
    public static boolean localizaVeiculoFrota(){

        Veiculo veiculo = retornaVeiculoFrota();
        
        if(veiculo != null){
            System.out.println("Veículo existe"); 
        }

        return true;
        
    }
    public static Veiculo retornaVeiculoFrota(){

        System.out.println("Informe a placa do veículo");
        String placa = TECLADO.nextLine();

        return retornaVeiculo(placa, "placaFinalizar");

    }
    public static boolean gerarRelatorio(){
    
        Veiculo veiculo = retornaVeiculoFrota();

        if(veiculo != null){
            veiculo.gerarRelatorio();
        }else{
            System.out.println("Veículo não existe");
        }

        return true;

    }

    /**
     * @param placa -> recebe a placa do veículo a ser procurado
     * @param tratamento -> dependendo do método que chama esta função ele devolve um resultado diferente
     *, então, para que cada tratamento seja feito em uma mesma função, ele trata cada caso de um jeito específico
     * @return -> retorna o veículo achado
     */
    private static Veiculo retornaVeiculo(String placa, String tratamento){

        if(FROTA.getVeiculos().isEmpty()){
            System.out.println("Não existe veículos cadastrados");
        }


        Veiculo veiculo = FROTA.localizarVeiculo(placa);

        if(veiculo != null){
            return veiculo;
        }

        if(tratamento.contains("placa")){

            System.out.println("Veiculo não existe");

            if(tratamento.contains("Finalizar")){
                return null;
            }else{
                System.out.println("Informe uma placa válida: ");
                String placa_nova = TECLADO.nextLine();
                retornaVeiculo(placa_nova, "incluirRota");
            }
        }

        return null;
        

    }

    /**
     * pega as informações para incluir uma rota
     */
    public static boolean incluirRota(){

        System.out.println("Informe a placa do veículo que será feita a rota: ");
        String placa = TECLADO.nextLine();
        Veiculo veiculo = retornaVeiculo(placa, "placa");
        System.out.println("Informe o tamanho da rota: ");
        String km_total = TECLADO.nextLine();
        System.out.println("Informe a data que foi feita a rota: ");
        String data = TECLADO.nextLine();

        criaRota(data, km_total, veiculo);

        return true;

    }

    /**
     * @param data -> recebe a data da rota
     * @param km_total -> recebe o tamanho da rota
     * @param veiculo -> recebe o veículo que fez a rota
     */
    private static void criaRota(String data, String km_total, Veiculo veiculo){

        try {
            veiculo.addRota(new Rota(data, Double.parseDouble(km_total)));
            System.out.println("Rota salva com sucesso!");
        } catch (ExceptionRouteTooBig e) {
            System.out.println(e.getMessage());
        }

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
                return carregaArquivo();
            case "2":
                return salvarArquivo();
            case "3":
                return criaVeiculo();
            case "4":
                return incluirRota();
            case "5":
                return localizaVeiculoFrota();
            case "6":
                return gerarRelatorio();
            case "7":
                return imprimiVeiculosComAs3MaioresRotas();
            case "8":
                return imprimiKmMedia();
            case "9":
                return imprimiListaOrdenadaDecrescente();
            default:
                System.out.println("Escolha inválida");
                return true;
        }

        
    }

    /**
     */
    public static boolean carregaArquivo(){

        File file = new File(VEICULOS_TXT);

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = bufferedReader.readLine();

            do{
                String[] dados = linha.split(";", 2);

                Veiculo veiculo = new FabricasVeiculos().criar(dados[0], dados[1]);
                FROTA.addVeiculo(veiculo);

                linha = bufferedReader.readLine();
            }while(linha != null);

            bufferedReader.close();
            fileReader.close();
            return true;

        } catch (IOException e) {
            // throw new RuntimeException("ERROR: " + e);
            System.out.println(" \nNão foi possivel ler o arquivo devido: ");
            System.out.println("\n"+e.getMessage()+"\n");
            return false;

        }
    }

    /**
     * método criado para salvar um arquivo com os dados do vetor de veículos
     */
    public static boolean salvarArquivo(){

        File file = new File(SALVAR_VEICULOS);

        try {

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Veiculo veiculo : FROTA.getVeiculos()){
                
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


                if(veiculo.getRotas().size() > 0){

                    bufferedWriter.newLine();
                    bufferedWriter.write("Rota;");

                    for(Rota rotas : veiculo.getRotas()){
                        bufferedWriter.write(rotas.getKmTotal() + ";" + rotas.getData().dataFormatada());
                    }

                }

                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
            return true;
        
        } catch (IOException e) {
            
            // throw new RuntimeException("ERROR: " + e);
            System.out.println(" \nNão foi possivel ler o arquivo devido: ");
            System.out.println("\n"+e.getMessage()+"\n");
            return false;
        }
    }

    /**
     * cria um veículo através de dados colteados pelo usuário
     */
    public static boolean criaVeiculo(){

        System.out.println("Informe o tipo do veículo: ");
        String tipo = TECLADO.nextLine();
        System.out.println("Informe a placa do veículo: ");
        String placa = TECLADO.nextLine();
        System.out.println("Informe o valor de venda do veículo: ");
        String valor_venda = TECLADO.nextLine();
        System.out.println("Informe o km médio do veículo: ");
        String km_medio = TECLADO.nextLine();

        String detalhe = placa + ';' + valor_venda + ';' + km_medio;

        Veiculo veiculo = new FabricasVeiculos().criar(tipo, detalhe);
        FROTA.addVeiculo(veiculo);

        return true;

    }

    private static Veiculo criarVeiculo(){


        try {

            Scanner leitor = new Scanner(new File(VEICULOS_TXT));

            String veiculos[] = leitor.nextLine().toLowerCase().split(";", 2);

            Veiculo veiculo = new FabricasVeiculos().criar(veiculos[0], veiculos[1]);

            FROTA.addVeiculo(veiculo);

            return veiculo;


        } catch (SecurityException | FileNotFoundException | IllegalArgumentException e
        ) {
            e.printStackTrace();
        }

        return null;

    }

    /* Imprimi veículo com as 3 maiores rotas */
    public static boolean imprimiVeiculosComAs3MaioresRotas(){

        try {
            veiculosMaioresRotas(FROTA, 3).forEach( v -> System.out.println(v.gerarRelatorio()));
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
    /* Imprimi km média de todos veículos de uma frota */
    public static boolean imprimiKmMedia(){

        System.out.println("Km médio da frota: "  + kmMediaTodasRotas(FROTA));

        return true;

    }
    /* Retorna a lista de custos em forma descrescente da frota de veículos */
    public static boolean imprimiListaOrdenadaDecrescente(){

        custosDecrescentes(FROTA).forEach(v -> System.out.println(v.gerarRelatorio()));

        return true;
    }

    // reflexão

    /**
     *
     * Este método pega todas as funções que engloba o app e a partir delas é analisado qual a escolha do usúario...
     * ..., pós isso é executado tal método
     * @param opcao-> opção escolhida pelo usuário
     *
     * */
    private static boolean trataOpcoesReflexao(String opcao){

        try {

            HashMap<String, Method> metodos = retornaHashMetodos();

            if(metodos.get(opcao) != null){
                return (boolean) metodos.get(opcao).invoke(APP);
            }else{
                return false;
            }

        } catch (SecurityException | IllegalAccessException
                 | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return false;

    }
    private static HashMap<String, Method> retornaHashMetodos(){

        String classe = "codigo.App";

        HashMap<String, Method> metodos_hash = new HashMap<>();

        try {

            Scanner leitor = new Scanner(new File(ESCOLHAS_USUARIO));

            Method[] metodos = Class.forName(classe).getMethods();

            while(leitor.hasNext()){

                String[] metodoTxt = leitor.nextLine().split(";");

                String chave = metodoTxt[0];
                String valor = metodoTxt[1];

                for(Method metodo : metodos){

                    if(metodo.toString().contains(valor)){
                        metodos_hash.put(chave,metodo);
                    }
                }
            }

        } catch (SecurityException | ClassNotFoundException | FileNotFoundException | IllegalArgumentException e
                 ) {
            e.printStackTrace();
        }

        return metodos_hash;

    }

    /** STREAM */

    public static double kmMediaTodasRotas(Frota frota){

        return frota.getVeiculos().stream()
                .mapToDouble(Veiculo :: quilometragem)
                .average()
                .getAsDouble();
    }
    /**
     * @param frota -> recebe a frota de veículos a ser localizada
     * @param limit -> limite de quantos veículos devem ser retornados
     * @return -> lista de veículos ordenadas
     * @throws RuntimeException -> exception em relação ao tamanho da frota
     */
    private static List<Veiculo> veiculosMaioresRotas(Frota frota, int limit){

        if(frota.getVeiculos().size() >= limit){

            return frota.getVeiculos()
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



}