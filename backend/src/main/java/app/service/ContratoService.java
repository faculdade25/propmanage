package app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.Repository.ContratoRepository;
import app.Repository.LogRepository;
import app.entity.Contrato;
import app.entity.Log;

public class ContratoService {

	@Autowired
	private ContratoRepository crepo;
	@Autowired
	private LogRepository lrepo;

	public void salvarLog(String action, String tabela, long entityid) {
		Log log = new Log();
		log.setAction(action);
		log.setEntityid(entityid);
		//log.setNome(nome);
		log.setTabela(tabela);
		log.setTimestamp(LocalDateTime.now());
		lrepo.save(log);
	}
	public String save (Contrato con) {
		this.crepo.save(con);
		salvarLog ("save", "contrato", con.getId());
		return con.getIdentificador()+ "contrato criado";
	}
	
	public String update (Long id, Contrato con) {
		con.setId(id);
		this.crepo.save(con);
		salvarLog ("update", "contrato", con.getId());
		return "editado com sucesso";
	}
	
	public String delete (Long id) {
		this.crepo.deleteById(id);
		salvarLog ("delete","contrato", id);
		return "contrato deletado";
	}
	
	public List<Contrato> listAll(){
		return this.crepo.findAll();
	}

}
