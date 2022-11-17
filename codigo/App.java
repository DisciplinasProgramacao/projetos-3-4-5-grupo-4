package codigo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App{

    private static Frota frota = new Frota();  
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        
        interfaceUsuario();


    }

    /* métodos públicos */

    /**
     * uma interface pública para os acessos aos métodos.
     */
    public static void interfaceUsuario(){
        
        String escolha;
        boolean continuar = true;

        do{

            imprimiOpcoes();
            escolha = teclado.nextLine();
            continuar = trataOpcoes(escolha);

        }while(continuar);


    }

    /* métodos privados */

    /**
     * método pára procuar veiculo de frota e gerar o relatório
     * @param isGerarRelatorio -> se for para gerar o relatório a variável vem como true
     */
    private static void localizaVeiculoFrota(boolean isGerarRelatorio){

        System.out.println("Informe a placa do veículo");
        String placa = teclado.nextLine();

        Veiculo veiculo = retornaVeiculo(placa, "placaFinalizar");
        
        if(veiculo != null){
            
            if(isGerarRelatorio){
                String relatorio = veiculo.gerarRelatorio();
                System.out.println(relatorio);
            }else{
                System.out.println("Veículo existe");
            }    
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
    private static void incluirRota(){

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

        System.out.println("0 - Sair");
        System.out.println("1 - Carregar conjunto de veículos");
        System.out.println("2 - Salvar conjunto de arquivos");
        System.out.println("3 - Incluir novo veículo");
        System.out.println("4 - Incluir rotas para veículo");
        System.out.println("5 - Localizar veículo da frota");
        System.out.println("6 - Imprimir relatório do veículo");

    }

    /**
     * @param escolha -> recebe a esoclha da opção do usuário
     * @return -> retorna true para caso seja para continuar a aplicação e false para encerrar
     */
    private static boolean trataOpcoes(String escolha){

        switch(escolha){
            
            case "0":
                return false;
            case "1":
                carregaArquivo("C:\\Users\\pablo\\Documents\\GitHub\\projetos-3-4-5-grupo-4\\codigo\\resources\\veiculos.txt");
                return true;
            case "2":
                salvarArquivo("C:\\Users\\pablo\\Documents\\GitHub\\projetos-3-4-5-grupo-4\\codigo\\resources\\veiculosSalvos.txt");
                return true;
            case "3":
                criaVeiculo();
                return true;
            case "4":
                incluirRota();
                return true;
            case "5":
                localizaVeiculoFrota(false);
                return true;
            case "6":
                localizaVeiculoFrota(true);
                return true;        
            default:
                System.out.println("Escolha inválida");
                return true;
        }

        
    }

    /**
     * @param path -> recebe o caminho do arquivo e faz o seu tratamento
     */
    private static void carregaArquivo(String path){

        File file = new File(path);

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

        } catch (FileNotFoundException e) {
            throw new RuntimeException("ERROR: " + e);
        } catch (IOException e) {
            throw new RuntimeException("ERROR: " + e);
        }
    }

    /**
     * método criado para salvar um arquivo com os dados do vetor de veículos
     * @param path -> recebe o caminho do arquivo e faz o seu tratamento
     */
    private static void salvarArquivo(String path){

        File file = new File(path);

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
    private static void criaVeiculo(){

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

    /** STREAMS INÍCIO */

    // Ainda a testar !
    public static double kmMediaTodasRotas(Frota frota){
        
        double media =  frota.getVeiculos().stream()
        .mapToDouble(Veiculo :: quilometragem)
        .average()
        .getAsDouble();

        return media;
    } 
    /** STREAMS FIM */


}