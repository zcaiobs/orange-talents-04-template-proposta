package br.com.zupacademy.caio.proposta.nova_proposta;

public class FinanceiraResponse {
    private final String documento;
    private final String nome;
    private final String idProposta;
    private final String resultadoSolicitacao;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public FinanceiraResponse(String documento, String nome, String idProposta, String resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }
}
