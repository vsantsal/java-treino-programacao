package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {


    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        StringBuilder caminho = new StringBuilder();
        caminho.append("/tmp/");
        caminho.append(
            BigDecimal
            .valueOf(id / 1000.0)
            .setScale(0, RoundingMode.CEILING));
        caminho.append("/");
        return new CaminhoArquivo(
            Paths.get(caminho.toString()), 
            Paths.get(caminho.append(id).toString()));

    }

}
