package br.com.coruja.application.dto;

import javax.validation.constraints.NotBlank;

import br.com.coruja.domain.model.Aluno;

public class AlunoCadastroDTO {
    
    @NotBlank
    private String nome;

    private String email;

    public Aluno toModel(){
        return new Aluno(nome, email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
