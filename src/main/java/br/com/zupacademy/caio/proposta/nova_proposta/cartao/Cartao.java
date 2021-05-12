package br.com.zupacademy.caio.proposta.nova_proposta.cartao;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.biometria.Biometria;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Biometria> biometrias;

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public Set<Biometria> getBiometrias() {
        return biometrias;
    }

    public Cartao(String numero) {
        this.numero = numero;
    }

    public Cartao() {
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", biometrias=" + biometrias +
                '}';
    }
}
