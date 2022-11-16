package codigo;

public enum IConstantsCarro{

    CUSTOALINHAMENTO_CARRO(80),
    KM_VISTORIA_CARRO(10000),
    TANQUE_CARRO(50),
    IPVA_CARRO (0.04),
    SEGURO_CARRO (0.05),
    VALORADICIONALSEGURO_CARRO (300);

    public double valor;

    IConstantsCarro(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }

}