package br.com.zupacademy.caio.proposta.nova_proposta.cartao.carteira;

public class CarteiraResponse {
    private final String resultado;
    private final String id;

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    public CarteiraResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }
}
