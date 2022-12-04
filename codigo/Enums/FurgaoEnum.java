package codigo.Enums;

public enum FurgaoEnum {

    CUSTOALINHAMENTO (120),
    CUSTOVISTORIA (500),
    KM_VISTORIA (10000),
    TANQUE (80),
    IPVA (0.03),
    SEGURO (0.03);

    private double valor;

    FurgaoEnum(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }
}
