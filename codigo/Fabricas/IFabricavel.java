package codigo.Fabricas;

public interface IFabricavel<t> {
    /**
     * 
     * @param dados -> os dados precisam estar separados com " ; "
     * @return um objeto criado
     */
    public t criar(String dados);
}
