package br.com.zupacademy.caio.proposta.nova_proposta.proposta;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.Cartao;

import java.math.BigDecimal;

public class PropostaResponse {

    private final Long id;
    private final String documento;
    private final String email;
    private final String nome;
    private final String endereco;
    private final BigDecimal salario;
    private final PropostaStatus status;
    private final Cartao cartao;

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public PropostaStatus getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public PropostaResponse(Long id, String documento, String email,
                            String nome, String endereco, BigDecimal salario,
                            PropostaStatus status, Cartao cartao) {
        this.id = id;
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.status = status;
        this.cartao = cartao;
    }

    public static PropostaResponse converter(Proposta proposta) {
        return new PropostaResponse(proposta.getId(), proposta.getDocumento(), proposta.getEmail(),
                proposta.getNome(), proposta.getEndereco(), proposta.getSalario(), proposta.getStatus(),
                proposta.getCartao());
    }
}
