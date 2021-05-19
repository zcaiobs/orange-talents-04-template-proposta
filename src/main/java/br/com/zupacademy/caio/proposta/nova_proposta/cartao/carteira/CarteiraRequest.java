package br.com.zupacademy.caio.proposta.nova_proposta.cartao.carteira;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CarteiraRequest {
    @NotBlank @Email
    private final String email;
    @NotBlank
    private final String carteira;

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public CarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public Carteira toCarteira(String numero, Cartao cartao) {
        return new Carteira(numero, this.carteira, this.email, LocalDateTime.now(), cartao);
    }
}
