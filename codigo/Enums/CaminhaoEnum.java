package codigo.Enums;

public enum CaminhaoEnum {

    CUSTOMANUTENCAO (1000),
    CUSTOVISTORIA (1000),
    KM_MANUTENCAO (20000),
    KM_VISTORIA (30000),
    TANQUE (250),
    IPVA (0.01),
    SEGURO (0.02),
    VALORADICIONALSEGURO (2000);

    private double valor;
    
    

    CaminhaoEnum(double valor){
        this.valor = valor;
    }

    

    public double getValor(){
        return this.valor;
    }
    
}
