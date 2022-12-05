package codigo.Exceptions;

public class ExceptionCombustivel extends  IllegalArgumentException{
    public ExceptionCombustivel(){
        super("Tipo do combustível inadequado.");
    }
}
