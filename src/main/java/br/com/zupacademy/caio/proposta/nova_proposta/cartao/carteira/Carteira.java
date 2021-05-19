package br.com.zupacademy.caio.proposta.nova_proposta.cartao.carteira;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.Cartao;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String nome;
    private String solicitante;
    private LocalDateTime created;
    @ManyToOne
    private Cartao cartao;

    public Long getId() {
        return id;
    }

    public Carteira(String numero, String nome, String solicitante,
                    LocalDateTime created, Cartao cartao) {
        this.numero = numero;
        this.nome = nome;
        this.solicitante = solicitante;
        this.created = created;
        this.cartao = cartao;
    }

    public Carteira() {

    }
}
