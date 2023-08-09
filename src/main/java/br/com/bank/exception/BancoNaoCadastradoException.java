package br.com.bank.exception;

public class BancoNaoCadastradoException extends RuntimeException {
    public  BancoNaoCadastradoException(String message) {
        super(message);
    }
}