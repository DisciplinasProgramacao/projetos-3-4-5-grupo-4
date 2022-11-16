package codigo;

public enum IConstantsVanFurgao{


    CUSTOALINHAMENTO_VAN_FURGAO (120),
    CUSTOVISTORIA_VAN_FURGAO (500),
    KM_VISTORIA_VAN_FURGAO (10000),
    TANQUE_VAN (60),
    TANQUE_FURGAO (80),
    IPVA_SEGURO_VAN_FURGAO (0.03);

    private double valor;

    IConstantsVanFurgao(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }

}