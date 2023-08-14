package br.com.coruja.application.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.coruja.application.dto.AlunoCadastroDTO;
import br.com.coruja.application.dto.AlunoPesquisaDTO;
import br.com.coruja.domain.model.Aluno;
import br.com.coruja.domain.repository.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<AlunoPesquisaDTO>> list(){
        List<Aluno> alunos = repository.findAll();

        return ResponseEntity.ok(
            alunos
                .stream()
                .map(
                    AlunoPesquisaDTO::new)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoPesquisaDTO> find(@PathVariable Long id){
        Optional<Aluno> alunoPesquisado = repository.findById(id);
        if (alunoPesquisado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new AlunoPesquisaDTO(alunoPesquisado.get()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AlunoPesquisaDTO> save(
        @RequestBody @Valid AlunoCadastroDTO dto, 
        UriComponentsBuilder uriComponentsBuilder){
            Aluno alunoSalvo = repository.save(dto.toModel());
            URI uri = uriComponentsBuilder.path("/api/alunos/{id}").buildAndExpand(alunoSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(new AlunoPesquisaDTO(alunoSalvo));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AlunoPesquisaDTO> put(
        @PathVariable Long id,
        @RequestBody AlunoCadastroDTO dto
    ){
        Aluno alunoAtualizar = repository.getById(id);
        alunoAtualizar.setEmail(dto.getEmail());
        alunoAtualizar.setNome(dto.getNome());
        repository.save(alunoAtualizar);
        return ResponseEntity.ok(new AlunoPesquisaDTO(alunoAtualizar));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}