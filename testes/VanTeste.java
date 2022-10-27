package testes;

import codigo.Van;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VanTeste {

    static String placa = "teste-123";
    static double kmMedio = 10.0;
    static double valorVenda = 100.0;

    @Test
    public void vanIPVA(){
        Van van = new Van(placa, valorVenda, kmMedio);
        assertEquals(3, van.calcularIPVA(), 0.1);
    }

    @Test
    public void vanSeguro(){
        Van van = new Van(placa, valorVenda, kmMedio);
        assertEquals(3, van.calcularSeguro(), 0.1);
    }
}
