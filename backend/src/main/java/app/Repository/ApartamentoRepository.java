package app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Apartamento;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long>{

}
