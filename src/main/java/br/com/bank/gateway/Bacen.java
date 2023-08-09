package br.com.bank.gateway;


import br.com.bank.exception.BancoNaoCadastradoException;
import br.com.bank.model.Banco;

public class Bacen {

	public long cadastrarBanco(Banco banco) {
		try{
			System.out.println("acessando de banco de dados");
			System.out.println("chamada de rede");
			return 0;
		} catch (Exception ex) {
			throw new BancoNaoCadastradoException(ex.getMessage());
		}

	}

}
