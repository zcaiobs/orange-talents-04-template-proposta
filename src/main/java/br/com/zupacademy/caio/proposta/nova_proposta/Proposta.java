package br.com.zupacademy.caio.proposta.nova_proposta;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    public Long getId() {
        return id;
    }

    public Proposta(String documento, String email, String nome,
                    String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta() {
    }
}
