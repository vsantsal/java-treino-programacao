package br.com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BancoTest {

    private Banco banco;

    private static String CPF_CLIENTE_DEFAULT = "111.111.111.11";

    @BeforeEach
    public void setUp(){
        banco = new Banco("EstrategiaBank");
    }

    @Test
    @DisplayName("Adição de primeira conta")
    public void testCenario1(){
        // Arrange
        Conta conta = new Conta(CPF_CLIENTE_DEFAULT);

        // Act
        banco.adicionarConta(conta);

        // Assert
        Optional<Conta> contaObtida = banco.pesquisarContaDoCliente(CPF_CLIENTE_DEFAULT);
        assertFalse(contaObtida.isEmpty());
        assertEquals(conta.getCpf(), contaObtida.get().getCpf());
        assertEquals(conta.getSaldo(), contaObtida.get().getSaldo());
        assertEquals(1, banco.getNumeroDeContas());
    }

    @Test
    @DisplayName("Pesquisa de conta nula deve lançar exceção")
    public void testCenario2(){
        // Act/Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> banco.adicionarConta(null)
        );

    }

    @Test
    @DisplayName("A mesma conta só pode ser adicionada uma vez")
    public void testCenario3(){
        // Arrange
        Conta conta = new Conta(CPF_CLIENTE_DEFAULT);

        // Act
        banco.adicionarConta(conta);
        banco.adicionarConta(conta);

        // Assert
        assertEquals(1, banco.getNumeroDeContas());
    }    

    @Test
    @DisplayName("Pesquisa de conta por CPF inexistente")
    public void testCenario4(){
        // Arrange
        Conta conta = new Conta("123.456.789-10");
        banco.adicionarConta(conta);

        // Act
        Optional<Conta> contaPesquisada = banco.pesquisarContaDoCliente(CPF_CLIENTE_DEFAULT);

        // Assert
        assertTrue(contaPesquisada.isEmpty());
    }


}
