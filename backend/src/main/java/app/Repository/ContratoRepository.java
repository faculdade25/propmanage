package app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Contrato;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long>{

}
