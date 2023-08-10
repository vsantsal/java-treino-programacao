package br.com.coruja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coruja.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
    
}
