package br.com.zupacademy.caio.proposta.nova_proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    @Transactional
    @Modifying
    @Query("update Proposta p set p.status = :status where p.id = :id")
    void atualizarStatusById(@Param("id") Long id, @Param("status") PropostaStatus status);

    List<Proposta> findAllByCartao_NumeroIsNullAndStatusLike(PropostaStatus status);

}
