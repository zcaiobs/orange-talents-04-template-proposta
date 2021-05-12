package br.com.zupacademy.caio.proposta.nova_proposta.proposta;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.Cartao;

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
    @Enumerated(EnumType.STRING)
    private PropostaStatus status;
    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

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

    public Proposta(String documento, String email, String nome,
                    String endereco, BigDecimal salario,
                    PropostaStatus status) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.status = status;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Proposta() {
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                ", status=" + status +
                ", cartao='" + cartao + '\'' +
                '}';
    }
}
