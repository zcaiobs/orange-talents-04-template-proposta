package br.com.zupacademy.caio.proposta.nova_proposta.cartao.bloqueio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip;
    private String userAgent;
    private Date data;

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Date getData() {
        return data;
    }

    public Bloqueio(String ip, String userAgent, Date data) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.data = data;
    }

    public Bloqueio() {

    }
}
