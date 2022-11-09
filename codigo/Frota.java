package codigo;

import java.util.ArrayList;
import java.util.List;

public class Frota {
    
    private ArrayList<Veiculo> veiculos;

    public Frota(){
        this.veiculos = new ArrayList<>();
    }

    /**
     * 
     * @param v -> Objeto veiculo a ser adicionado
     * @return true -> adicionado com sucesso, false -> deu algum erro;
     */
    public boolean addVeiculo(Veiculo v) {
        return this.veiculos.add(v);
    }

    /**
     * 
     * @param placa -> placa do veiculo a ser buscado
     * @return O veículo em caso de sucesso, NULL em caso de falha
     */
    public Veiculo localizarVeiculo(String placa) {

        for (int i = 0; i < this.veiculos.size(); i++) {
            if (placa.equals(veiculos.get(i).getPlaca())) {
                return veiculos.get(i);
            }
        }
        return null;
    }


    /**
     * 
     * @param placa -> placa do veículo que deseja adicionar a rota
     * @param rota  -> rota q deseja adicionar
     * @return true caso tenha dado certo, false caso não tenha achado o veículo
     */
    public boolean addRota(String placa, Rota rota) {
        Veiculo aux = this.localizarVeiculo(placa);
        if (aux != null)
            return aux.addRota(rota);
        else
            return false;
    }
    
    /**
     * 
     * @param placa -> placa do veículo que deseja o relatório
     * @return  String com o relatório achado
     */
    public String imprimirRelatorioVeiculo(String placa) {
        Veiculo aux = this.localizarVeiculo(placa);
        if (aux != null)
            return aux.gerarRelatorio();
        else
            return "Não possível localizar o veículo no sistema";
    }

    public List<Veiculo> getVeiculos(){
        return this.veiculos;
    }
}
