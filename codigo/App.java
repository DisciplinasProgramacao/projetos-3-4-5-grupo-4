package codigo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App{

    private static List<Veiculo> veiculos = new ArrayList<>();  
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
                carregaArquivo("");
                return true;
            case "2":
                salvarArquivo();
                return true;
            case "3":
                criaVeiculo();
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

                String dados[] = linha.split(linha);
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
     */
    private static void salvarArquivo(){

        File file = new File("");

        try {

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Veiculo veiculo : veiculos){

                bufferedWriter.append(veiculo.getClass() + ";" + veiculo.getPlaca() + ";" + 
                veiculo.getValorVenda() + ";" + veiculo.getKmMedio() + "\n");

            }

            fileWriter.close();
            bufferedWriter.close();
        
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
     * @param placa -> atributo
     * @param valor_venda -> atributo
     * @param km_medio -> atributo
     * @return -> Retorna o veículo criado
     */
    private static Veiculo criarVeiculo(String tipo, String placa, String valor_venda, String km_medio){

        tipo = tipo.toLowerCase();

        double valor_venda_convertido = Double.parseDouble(valor_venda);
        double km_medio_convertido = Double.parseDouble(km_medio);
        
        switch(tipo){

            case "carro":
                //Carro carro = new Carro(...);
                //veiculos.add(carro);
                break;
            case "van":
                //Van van = new Van(...);
                //veiculos.add(van);
                break;
            case "furgao":
            case "furgão":
                //Furgao furgao = new Furgao(...);
                //veiculos.add(Furgao);
                break;
            case "caminhao":
            case "caminhão":
                //veiculos.add(Caminhao);
                //Caminhao caminhao = new Caminhao(...);
                break;
            default:
                System.out.println("Este tipo não existe, informe um válido (Carro, Van, Furgao ou Caminhao): ");
                String novo_tipo = teclado.nextLine();
                criarVeiculo(novo_tipo, placa, valor_venda, km_medio);                
        }

        return null;

    }


}