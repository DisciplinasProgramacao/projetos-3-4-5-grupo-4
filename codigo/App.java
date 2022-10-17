package codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App{

    public static List<Veiculo> veiculos = new ArrayList<>();  

    public static void main(String[] args) {
        
        carregaArquivo("");


    }

    /* métodos públicos */

    /* métodos privados */

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
     * @param tipo -> recebe o tipo do veículo a ser criado
     * @param placa -> atributo
     * @param valor_venda -> atributo
     * @param km_medio -> atributo
     * @return -> Retorna o veículo criado
     */
    private static Veiculo criarVeiculo(String tipo, String placa, String valor_venda, String km_medio){

        switch(tipo){

            case "Carro":
                //Carro carro = new Carro(...);
                //veiculos.add(carro);
                break;
            case "Van":
                //Van van = new Van(...);
                //veiculos.add(van);
                break;
            case "Furgao":
                //Furgao furgao = new Furgao(...);
                //veiculos.add(Furgao);
                break;
            case "Caminhao":
                //veiculos.add(Caminhao);
                //Caminhao caminhao = new Caminhao(...);
                break;            
        }

        return null;

    }
}