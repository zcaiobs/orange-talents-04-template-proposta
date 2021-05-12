package br.com.zupacademy.caio.proposta.nova_proposta.cartao.biometria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fingerprint;
    private LocalDate data;

    public Long getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public LocalDate getData() {
        return data;
    }

    public Biometria(String fingerprint, LocalDate data) {
        this.fingerprint = fingerprint;
        this.data = data;
    }

    public Biometria() {
    }
}
