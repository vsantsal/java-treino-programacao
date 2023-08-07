package br.com.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CaminhoArquivoTest {

    @Test
    @DisplayName("Teste para confirmar comportamento quando ids são informados")
    public void deve_montar_caminho_para_arquivo() {

        CaminhoArquivo caminhoArquivo = CaminhoArquivo.getInstance(1);
        assertEquals(Paths.get("/tmp/1"), caminhoArquivo.getDiretorio());
        assertEquals(Paths.get("/tmp/1/1"), caminhoArquivo.getArquivo());

        caminhoArquivo = CaminhoArquivo.getInstance(2);
        assertEquals(Paths.get("/tmp/1"), caminhoArquivo.getDiretorio());
        assertEquals(Paths.get("/tmp/1/2"), caminhoArquivo.getArquivo());

        caminhoArquivo = CaminhoArquivo.getInstance(1000);
        assertEquals(Paths.get("/tmp/1"), caminhoArquivo.getDiretorio());
        assertEquals(Paths.get("/tmp/1/1000"), caminhoArquivo.getArquivo());

        caminhoArquivo = CaminhoArquivo.getInstance(1500);
        assertEquals(Paths.get("/tmp/2"), caminhoArquivo.getDiretorio());
        assertEquals(Paths.get("/tmp/2/1500"), caminhoArquivo.getArquivo());

        caminhoArquivo = CaminhoArquivo.getInstance(2000);
        assertEquals(Paths.get("/tmp/2"), caminhoArquivo.getDiretorio());
        assertEquals(Paths.get("/tmp/2/2000"), caminhoArquivo.getArquivo());

        caminhoArquivo = CaminhoArquivo.getInstance(2001);
        assertEquals(Paths.get("/tmp/3"), caminhoArquivo.getDiretorio());
        assertEquals(Paths.get("/tmp/3/2001"), caminhoArquivo.getArquivo());

    }

    @Test
    @DisplayName("Teste para avaliar comportamento ao se passar id nulo")
    public void test_cenario_id_nulo(){
        // Arrange/Act/Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> CaminhoArquivo.getInstance(null)
        );

    }

    @Test
    @DisplayName("Teste para avaliar comportamento ao se passar id negativo")
    public void test_cenario_id_negativo(){
        // Arrange/Act/Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> CaminhoArquivo.getInstance(-1)
        );
    }

}