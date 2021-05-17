package br.com.zupacademy.caio.proposta.nova_proposta.cartao.aviso;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCartao;
    private String destino;
    private LocalDate termino;
    private LocalDateTime created;
    private String ip;
    private String userAgent;

    public Viagem(Long idCartao, String destino, LocalDate termino,
                  LocalDateTime created, String ip, String userAgent) {
        this.idCartao = idCartao;
        this.destino = destino;
        this.termino = termino;
        this.created = created;
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public Viagem() {

    }

    @Override
    public String toString() {
        return "Viagem{" +
                "id=" + id +
                ", idCartao=" + idCartao +
                ", destino='" + destino + '\'' +
                ", termino=" + termino +
                ", created=" + created +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
