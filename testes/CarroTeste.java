package testes;

import codigo.Carro;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarroTeste {

    static String placa = "teste-123";
    static double kmMedio = 10.0;
    static double valorVenda = 100.0;

    @Test
    public void carroIPVA(){
        Carro carro = new Carro(placa, valorVenda, kmMedio);
        assertEquals(4, carro.calcularIPVA(), 0.1);
    }

    @Test
    public void carroSeguro(){
        Carro carro = new Carro(placa, valorVenda, kmMedio);
        assertEquals(305, carro.calcularSeguro(), 0.1);
    }
}
