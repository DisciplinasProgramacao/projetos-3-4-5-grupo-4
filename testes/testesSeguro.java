package testes;

import codigo.Caminhao;
import codigo.Carro;
import codigo.Furgao;
import codigo.Rota;
import codigo.Van;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class testesSeguro {

    static String placa = "teste-123";
    static Rota rota = new Rota("01/02/2002", 2.0);
    ArrayList<Rota> rotas = new ArrayList<>();
    static double valorVenda = 100.0;

    @Test
    public void carroSeguro(){
        rotas.add(rota);
        Carro carro = new Carro(placa, rotas, valorVenda);
        assertEquals(305, carro.calcularSeguro(), 0.1);
    }

    @Test
    public void vanSeguro(){
        rotas.add(rota);
        Van van = new Van(placa, rotas, valorVenda);
        assertEquals(3, van.calcularSeguro(), 0.1);
    }
    public void furgaoseguro(){
        rotas.add(rota);
        Furgao fur = new Furgao(placa, rotas, valorVenda);
        assertEquals(3, fur.calcularSeguro(), 0.1);
    }
    public void caminhaoseguro(){
        rotas.add(rota);
        Caminhao car = new Caminhao(placa, rotas, valorVenda);
        assertEquals(2002, car.calcularSeguro(), 0.1);
    }
}
