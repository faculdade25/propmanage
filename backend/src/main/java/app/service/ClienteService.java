package app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.Repository.ClienteRepository;
import app.Repository.LogRepository;
import app.entity.Cliente;
import app.entity.Log;

public class ClienteService {


	@Autowired
	private ClienteRepository crepo;
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

	public String save (Cliente cliente) {
		this.crepo.save(cliente);
		salvarLog("save", "Cliente", cliente.getId());
		
		return cliente.getId()+ "cliente salvo";
	}
	
	public String update (Long id, Cliente cliente) {
		cliente.setId(id);
		this.crepo.save(cliente);
		salvarLog("Update", "Cliente", cliente.getId());
		return "editado com sucesso";
	}
	
	public String delete (Long id) {
		this.crepo.deleteById(id);
		salvarLog("delete", "Cliente", id);
		return "cliente deletado";
	}
	public List<Cliente> listAll(){
		return this.crepo.findAll();
	}


}
