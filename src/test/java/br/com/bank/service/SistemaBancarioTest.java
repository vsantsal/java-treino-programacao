package br.com.bank.service;

import br.com.bank.exception.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

   @InjectMocks
   private SistemaBancario sistemaBancario;
   @Mock
   private Bacen bacen;

   private Banco banco;

   @BeforeEach
   public void setUp(){
    this.banco = new Banco("BANCO");
   }

   @Test
   @DisplayName("Testa retorna número de registro do banco para chamada com sucesso usando Fake")
   public void testCenario1(){
    // Arrange 
    Bacen bacenFake = new BacenFake();
    Mockito.when(bacen.cadastrarBanco(banco))
            .thenReturn(bacenFake.cadastrarBanco(banco));
    // Act
    long numeroRegistroObtido = sistemaBancario.registrarBanco(banco);

    // Assert
    assertEquals(banco.getNome().hashCode(), numeroRegistroObtido);

   }

   @Test
   @DisplayName("Testa retorna número de registro do banco para chamada com sucesso usando Stub")
   public void testCenario2(){
    // Arrange 
    Bacen stubBacen = new BacenStub();
    Mockito.when(bacen.cadastrarBanco(banco))
            .thenReturn(stubBacen.cadastrarBanco(banco));
    // Act
    long numeroRegistroObtido = sistemaBancario.registrarBanco(banco);

    // Assert
    assertEquals(42, numeroRegistroObtido);

   }   

   @Test
   @DisplayName("Testa retorna número de registro do banco para chamada com sucesso usando Mockito")
   public void testCenario3(){
    // Arrange 
    long respostaSucesso = 101L;
    Mockito.when(bacen.cadastrarBanco(banco))
            .thenReturn(respostaSucesso);
    // Act
    long numeroRegistroObtido = sistemaBancario.registrarBanco(banco);

    // Assert
    assertEquals(respostaSucesso, numeroRegistroObtido);

   }   

   @Test
   @DisplayName("Testa Lançamento de exceção")
   public void testCenario4(){
    // Arrange 
    Mockito.when(bacen.cadastrarBanco(banco))
            .thenThrow(BancoNaoCadastradoException.class);
    // Act / Assert
    assertThrows(
        BancoNaoCadastradoException.class,
        () -> sistemaBancario.registrarBanco(banco)
    );

   }      


}