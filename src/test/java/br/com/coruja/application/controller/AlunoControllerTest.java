package br.com.coruja.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import br.com.coruja.domain.model.Aluno;
import br.com.coruja.domain.repository.AlunoRepository;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    private final String ENDPOINT = "/api/alunos";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoRepository repository;

    @DisplayName("Teste de matrícula de aluno com dados válidos")
    @Test
    public void testCenario1()  throws Exception  {
        // Arrange
        when(repository.save(any(Aluno.class))).thenReturn(
            new Aluno("Fulano", "fulano@estrategia.com.br")
        );

        // Act
        this.mockMvc.perform(
            post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                "{\"aluno\": \"fulano\", " +
                " \"email\": \"fulano@estrategia.com.br\"}" )
        )
            // Assert
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", containsString(ENDPOINT)));
        
    }
    
}
