package app.service;

import java.time.LocalDateTime;
import java.util.List;

import app.Repository.UserRepository;
import app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import app.Repository.ClienteRepository;
import app.Repository.LogRepository;
import app.entity.Cliente;
import app.entity.Log;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


	@Autowired
	private UserRepository crepo;
	@Autowired
	private LogRepository lrepo;

	public void salvarLog(String action, String tabela, long entityid) {
		Log log = new Log();
		log.setAction(action);
		log.setEntityId(entityid);
		log.setTabela(tabela);
		log.setTimestamp(LocalDateTime.now());
		lrepo.save(log);
	}

	public String save (User cliente) {
		this.crepo.save(cliente);
		salvarLog("save", "Cliente", cliente.getId());
		return cliente.getId()+ "cliente salvo";
	}
	
	public String update (Long id, User cliente) {
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
	public List<User> listAll(){
		return this.crepo.findAll();
	}


}
