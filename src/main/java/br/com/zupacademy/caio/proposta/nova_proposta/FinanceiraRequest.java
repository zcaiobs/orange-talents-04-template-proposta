package br.com.zupacademy.caio.proposta.nova_proposta;

public class FinanceiraRequest {
    private final String documento;
    private final String nome;
    private final String idProposta;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public FinanceiraRequest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }
}
