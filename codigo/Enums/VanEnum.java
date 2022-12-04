package codigo.Enums;

public enum VanEnum {
    CUSTOALINHAMENTO (120),
    CUSTOVISTORIA (500),
    KM_VISTORIA (10000),
    TANQUE (60),
    IPVA(0.03),
    SEGURO(0.03);

    private double valor;

    VanEnum(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }
}
