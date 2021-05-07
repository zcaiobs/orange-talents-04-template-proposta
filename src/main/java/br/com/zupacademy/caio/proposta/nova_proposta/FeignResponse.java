package br.com.zupacademy.caio.proposta.nova_proposta;

public class FeignResponse {
      private final String documento;
      private final String nome;
      private final String resultadoSolicitacao;
      private final Long idProposta;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public FeignResponse(String documento, String nome, String resultadoSolicitacao, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }
}
