package app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import app.Repository.AnuncioRepository;
import app.Repository.LogRepository;
import app.entity.Anuncio;
import app.entity.Log;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository arepo;

	@Autowired
	private LogRepository lrepo;

	public void salvarLog(String action, String tabela, long entityid) {
		Log log = new Log();
		log.setAction(action);
		log.setEntityId(entityid);
		//log.setNome(nome);
		log.setTabela(tabela);
		log.setTimestamp(LocalDateTime.now());
		lrepo.save(log);
	}
		
	public String save (Anuncio anuncio) {
		this.arepo.save(anuncio);
		salvarLog ("save", "anuncio", anuncio.getId());
		return anuncio.getNome()+ "anuncio enviado";
	}

	public String update (Long id, Anuncio anuncio) {
		anuncio.setId(id);
		this.arepo.save(anuncio);
		salvarLog ("update", "anuncio", anuncio.getId());
		return "editado com sucesso";
	}
	
	public String delete (Long id) {
		this.arepo.deleteById(id);
		salvarLog ("delete","anuncio", id);
		return "anuncio deletado";
	}
	
	public List<Anuncio> listAll(){
		return this.arepo.findAll();
	}
	
}
