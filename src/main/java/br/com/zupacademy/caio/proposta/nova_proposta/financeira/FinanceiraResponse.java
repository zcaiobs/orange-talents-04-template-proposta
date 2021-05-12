package br.com.zupacademy.caio.proposta.nova_proposta.financeira;

import br.com.zupacademy.caio.proposta.validator.Documento;

import javax.validation.constraints.NotBlank;

public class FinanceiraResponse {
    @NotBlank @Documento
    private final String documento;
    @NotBlank
    private final String nome;
    @NotBlank
    private final String idProposta;
    @NotBlank
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
