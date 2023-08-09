package br.com.bank.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

    private String nome;

    private final Map<String, Conta> contas = new HashMap<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }    

    public void adicionarConta(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("conta inválida");
        }
        contas.put(conta.getCpf(), conta);
    }

    public Optional<Conta> pesquisarContaDoCliente(String cpf) {
        Conta conta = contas.get(cpf);
        if (conta == null){
            return Optional.empty();
        }
        return Optional.of(conta);
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= 10000);
    }

    public int getNumeroDeContas(){
        return contas.size();
    }


    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.values().stream().filter(filtro).collect(Collectors.toList());
    }
}
