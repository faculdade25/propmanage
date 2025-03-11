package app.Repository;

import app.entity.Apartamento;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Contrato;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findByApartamentoIn(List<Apartamento> apartamentos);

    Optional<Contrato> findTopByInquilinoOrderByDataAceiteDesc(User usuario);

    List<Contrato> findByInquilino(User inquilino);

    @Query("SELECT c FROM Contrato c WHERE c.apartamento.predio.email = :email")
    List<Contrato> findByAdministradorId(@Param("email") String email);

}