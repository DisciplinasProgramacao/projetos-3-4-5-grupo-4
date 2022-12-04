package codigo.Enums;

public enum CarroEnum {

    CUSTOALINHAMENTO(80),
    KM_VISTORIA(10000),
    TANQUE(50),
    IPVA (0.04),
    SEGURO (0.05),
    VALORADICIONALSEGURO (300);

    public double valor;

    CarroEnum(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }
}
