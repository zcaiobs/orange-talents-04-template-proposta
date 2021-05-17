package br.com.zupacademy.caio.proposta.nova_proposta.cartao.aviso;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/avisos")
public class ViagemAvisoController {

    Logger log = LoggerFactory.getLogger(ViagemAvisoController.class);

    ViagemRepository viagemRepository;
    CartaoRepository cartaoRepository;
    EnviarAvisoViagem avisoViagem;

    ViagemAvisoController(ViagemRepository viagemRepository, CartaoRepository cartaoRepository, EnviarAvisoViagem avisoViagem) {
        this.viagemRepository = viagemRepository;
        this.cartaoRepository = cartaoRepository;
        this.avisoViagem = avisoViagem;
    }

    @PostMapping("/viagem/{idCartao}")
    public ResponseEntity<?> avisar(@PathVariable Long idCartao,
                                    @Valid @RequestBody ViagemRequest viagemRequest,
                                    @RequestHeader HttpHeaders headers) {
        var cartao = cartaoRepository.findById(idCartao);
        if (cartao.isPresent()) {
            try{
                var id = cartao.get().getId();
                var ip = Objects.requireNonNull(headers.getHost()).getHostString();
                var userAgent = Objects.requireNonNull(headers.get("User-Agent")).get(0);

                if (sincronizarAviso(cartao.get().getNumero(), viagemRequest)) {
                    var aviso = viagemRepository.save(viagemRequest.toViagem(id, ip, userAgent));
                    log.info("Aviso criado: {}", aviso);
                    return ResponseEntity.ok().build();
                }
            }catch (Exception ex) {
                log.warn(ex.getMessage(), ex.getCause());
                return ResponseEntity.badRequest().build();
            }
        }
        log.error("Não foi possível criar o aviso: {}", cartao);
        return ResponseEntity.notFound().build();
    }

    private boolean sincronizarAviso(String numeroCard, ViagemRequest viagemRequest) {
        var result = avisoViagem.enviar(numeroCard, viagemRequest);
        return Objects.requireNonNull(result.getBody()).getResultado().equals("CRIADO");
    }
}
