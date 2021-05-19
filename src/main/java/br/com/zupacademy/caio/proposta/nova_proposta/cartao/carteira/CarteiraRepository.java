package br.com.zupacademy.caio.proposta.nova_proposta.cartao.carteira;

import br.com.zupacademy.caio.proposta.nova_proposta.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    @Query("select c from Carteira c where c.nome = :nome and  c.cartao = :cartao")
    List<Carteira> existsCarteira(@Param("nome") String nome, @Param("cartao") Cartao cartao);
}
