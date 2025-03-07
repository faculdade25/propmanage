package app.Repository;

import app.entity.Predio;
import app.entity.StatusApartamento;
import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Apartamento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long>{
    List<Apartamento> findByPredio(Predio predio);
    List<Apartamento> findByPredioAndStatusNot(Predio predio, StatusApartamento status);
}
