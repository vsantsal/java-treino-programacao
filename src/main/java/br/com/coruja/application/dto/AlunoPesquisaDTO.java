package br.com.coruja.application.dto;

import br.com.coruja.domain.model.Aluno;

public class AlunoPesquisaDTO {
    private final Long id;
    private final String nome;
    private final String email;

    public AlunoPesquisaDTO(Aluno aluno){
        this(aluno.getId(), aluno.getNome(), aluno.getEmail());
    }

    public AlunoPesquisaDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    
}
