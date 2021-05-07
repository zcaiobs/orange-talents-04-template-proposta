package br.com.zupacademy.caio.proposta.nova_proposta;

import br.com.zupacademy.caio.proposta.validator.Documento;
import br.com.zupacademy.caio.proposta.validator.ValorUnico;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank @Documento @ValorUnico(domain = Proposta.class, field = "documento")
    private final String documento;
    @NotBlank @Email
    private final String email;
    @NotBlank
    private final String nome;
    @NotBlank
    private final String endereco;
    @NotNull @PositiveOrZero
    private final BigDecimal salario;

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

    public PropostaRequest(String documento, String email, String nome,
                           String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toProposta() {
        return new Proposta(
                this.documento, this.email,
                this.nome, this.endereco,
                this.salario);
    }
}
