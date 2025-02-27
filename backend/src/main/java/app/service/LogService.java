package app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Repository.LogRepository;
import app.entity.Log;


@Service
public class LogService {

	@Autowired
	private LogRepository repo;

	public void logaction(String action, String tabela, long entityid, String nome, LocalDateTime timestamp ) {
		Log log = new Log();
		log.setAction(action);
		log.setEntityid(entityid);
		log.setNome(nome);
		log.setTabela(tabela);
		log.setTimestamp(timestamp);
		repo.save(log);
	}

	public List<Log> listAll(){
		return this.repo.findAll();
	}

}
