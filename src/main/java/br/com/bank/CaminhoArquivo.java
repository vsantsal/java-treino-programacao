package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {


    private static final String DIRETORIO_BASE = "/tmp/";

    private static final double NUMERO_ARQUIVOS_POR_DIRETORIO = 1000.0;

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

    /*
     * Método que retorna instância de CaminhoArquivo
     * a partir do id passado, com a seguinte lógica de construção:
     * '/tmp/id_dir/id_file'
     * onde id_dir é maior inteiro maior ou igual à divisão de id/1000
     * e id_file é o id recebido
     * 
     * @param id Integer com id para o arquivo
     * 
     * @throws IllegalArgumentException se id nulo ou negativo
    */
    public static CaminhoArquivo getInstance(Integer id) {
        
        // aplica lógica de validação
        validaIdInformado(id);

        // derivação do diretório
        StringBuilder caminho = new StringBuilder();
        caminho
        .append(DIRETORIO_BASE)
        .append(
            BigDecimal
            .valueOf(id / NUMERO_ARQUIVOS_POR_DIRETORIO)
            .setScale(0, RoundingMode.CEILING));
        
        return new CaminhoArquivo(
            Paths.get(caminho.toString()), 
            Paths.get(caminho.append("/").append(id).toString()));

    }

    private static void validaIdInformado(Integer id){
        if (id == null 
                || id <= 0 ) {
            throw new IllegalArgumentException("id deve ser inteiro positivo");
        }

    }

}
