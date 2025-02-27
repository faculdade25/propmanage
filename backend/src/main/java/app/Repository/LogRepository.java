package app.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

	List<Log> listAll();
	
	List<Log> findByTabela(String tabela);
	
	List<Log> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	
}
