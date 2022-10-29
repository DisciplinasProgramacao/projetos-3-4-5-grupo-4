package codigo;

public class Rota {

    private Data data;
    private double kmTotal;

    /**
     * 
     * @param data -> Formato DD/MM/YYYY
     * @param km -> caso for menor que 0, o valor atribuido será 0
     */
    public Rota(String data, double km){
        this.setData(data);
        this.setKmTotal(km);
    }

    public  double getKmTotal(){
        return this.kmTotal;
    }

    /**
     * 
     * @param km -> Caso seja menor que 0, o valor atribuido será 0
     */
    public void setKmTotal(double km){
        if(km < 0)
            this.kmTotal = 0;
        else
            this.kmTotal = km;
    }

    public Data getData(){
        return this.data;
    }

    /**
     * Inicia objeto Data com o valor fornecido
     * @param data -> Foramto DD/MM/YYYY
     */
    public void setData(String data){
        String[] dados = data.split("/");
        int dia = Integer.parseInt(dados[0]);
        int mes = Integer.parseInt(dados[1]);
        int ano = Integer.parseInt(dados[2]);
        this.data = new Data(dia,mes,ano);
    }

    
}
