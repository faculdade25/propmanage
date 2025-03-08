package app.Repository;

import app.entity.Pagamentos;
import app.entity.StatusPagamento;
import app.entity.User;
import app.entity.dto.PagamentoResumoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentosRepository extends JpaRepository<Pagamentos, Long> {

    Optional<Pagamentos> findTopByContrato_InquilinoAndStatusOrderByVencimentoAsc(User inquilino, StatusPagamento status);

    List<Pagamentos> findTop6ByContrato_InquilinoOrderByVencimentoDesc(User inquilino);

    List<Pagamentos> findByContrato_Inquilino(User inquilino);

    @Query("SELECT COALESCE(SUM(p.valor), 0) FROM Pagamentos p WHERE p.contrato.apartamento.predio.email = :email   AND p.status = 'PAGO' AND p.vencimento BETWEEN :inicioMes AND :fimMes")
    BigDecimal getTotalPagamentosPagosNoMes(@Param("email") String email, @Param("inicioMes") LocalDate inicioMes, @Param("fimMes") LocalDate fimMes);

    @Query("SELECT COALESCE(SUM(p.valor), 0) FROM Pagamentos p WHERE p.contrato.apartamento.predio.email = :adminId AND p.status IN ('PENDENTE', 'VENCIDO') AND p.vencimento BETWEEN :inicioMes AND :fimMes")
    BigDecimal getTotalPagamentosPendentesNoMes(@Param("adminId") String adminId, @Param("inicioMes") LocalDate inicioMes, @Param("fimMes") LocalDate fimMes);

    @Query("SELECT new app.entity.dto.PagamentoResumoDTO(p.contrato.apartamento.id, p.vencimento, p.valor, p.status) FROM Pagamentos p WHERE p.contrato.apartamento.predio.email = :adminId AND p.status IN ('PENDENTE', 'VENCIDO')")
    List<PagamentoResumoDTO> getPagamentosPendentes(@Param("adminId") String adminId);

    @Query("SELECT p FROM Pagamentos p WHERE p.contrato.apartamento.predio.email = :adminId")
    List<Pagamentos> getTodosPagamentosDoPredio(@Param("adminId") String adminId);

}
