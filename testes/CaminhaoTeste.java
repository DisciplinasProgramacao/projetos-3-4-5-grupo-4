package testes;

import codigo.Caminhao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaminhaoTeste {

    static String placa = "teste-123";
    static double kmMedio = 10.0;
    static double valorVenda = 100.0;

    @Test
    public void caminhaoipva(){
        Caminhao cam = new Caminhao(placa, valorVenda, kmMedio);
        assertEquals(1, cam.calcularIPVA(), 0.1);
    }

    @Test
    public void caminhaoseguro(){
        Caminhao cam = new Caminhao(placa, valorVenda, kmMedio);
        assertEquals(2002, cam.calcularSeguro(), 0.1);
    }
}
