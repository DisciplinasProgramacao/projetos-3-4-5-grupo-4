package codigo;

public enum IConstantsCaminhao{

    CUSTOMANUTENCAO_CAMINHAO (1000),
    CUSTOVISTORIA_CAMINHAO (1000),
    KM_MANUTENCAO_CAMINHAO (20000),
    KM_VISTORIA_CAMINHAO (30000),
    TANQUE_CAMINHAO (250),
    IPVA_CAMINHAO (0.01),
    SEGURO_CAMINHAO (0.02),
    VALORADICIONALSEGURO_CAMINHAO (2000);

    private double valor;

    IConstantsCaminhao(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }

}

    



