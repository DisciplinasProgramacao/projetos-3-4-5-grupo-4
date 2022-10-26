package testes;

import codigo.Caminhao;
import codigo.Carro;
import codigo.Furgao;
import codigo.Rota;
import codigo.Van;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class testesIPVA {

    static String placa = "teste-123";
    static Rota rota = new Rota("01/02/2002", 2.0);
     ArrayList<Rota> rotas = new ArrayList<>();
    static double valorVenda = 100.0;

    @Test
    public void carroIPVA(){
        rotas.add(rota);
        Carro carro = new Carro(placa, rotas, valorVenda);
        assertEquals(4, carro.calcularIPVA(), 0.1);
    }

    @Test
    public void vanIPVA(){
        rotas.add(rota);
        Van van = new Van(placa, rotas, valorVenda);
        assertEquals(3, van.calcularIPVA(), 0.1);
    }
    @Test
    public void caminhaoipva(){
        rotas.add(rota);
        Caminhao cam = new Caminhao(placa, rotas, valorVenda);
        assertEquals(1, cam.calcularIPVA(), 0.1);
    }
    @Test
    public void furgaoipva(){
        rotas.add(rota);
        Furgao fur = new Furgao(placa, rotas, valorVenda);
        assertEquals(3, fur.calcularIPVA(), 0.1);
    }
    
}
