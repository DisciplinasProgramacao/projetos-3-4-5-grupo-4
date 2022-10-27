package testes;

import codigo.Furgao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FurgaoTeste {

    static String placa = "teste-123";
    static double kmMedio = 10.0;
    static double valorVenda = 100.0;

    @Test
    public void furgaoipva(){
        Furgao fur = new Furgao(placa, valorVenda, kmMedio);
        assertEquals(3, fur.calcularIPVA(), 0.1);
    }

    @Test
    public void furgaoseguro(){
        Furgao fur = new Furgao(placa, valorVenda, kmMedio);
        assertEquals(3, fur.calcularSeguro(), 0.1);
    }
}
