package codigo;

public class ExceptionCombustivel extends  IllegalArgumentException{
    ExceptionCombustivel(){
        super("Tipo do combustível inadequado.");
    }
}
