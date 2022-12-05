package codigo.Exceptions;

public class ExceptionRouteTooBig extends  IllegalArgumentException{
    public ExceptionRouteTooBig(){
        super("A rota passada é distante demais para o veículo");
    }
}