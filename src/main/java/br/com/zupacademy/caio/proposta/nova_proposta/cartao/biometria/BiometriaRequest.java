package br.com.zupacademy.caio.proposta.nova_proposta.cartao.biometria;

import br.com.zupacademy.caio.proposta.validator.ExistsValue;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class BiometriaRequest {
    @NotBlank @ExistsValue(domainClass = Biometria.class, fieldName = "fingerprint")
    private String fingerprint;

    public String getFingerprint() {
        return fingerprint;
    }

    public Biometria toBiometria() {
        return new Biometria(this.fingerprint, LocalDate.now());
    }
}
