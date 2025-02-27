package app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Repository.ApartamentoRepository;
import app.Repository.LogRepository;
import app.entity.Apartamento;
import app.entity.Contrato;
import app.entity.Log;

@Service
public class ApartamentoService {

	@Autowired
	private ApartamentoRepository arepo;
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
	
	public String verificastatus(Apartamento ap) {
		List<Contrato> contratos = ap.getContratos();

		if (contratos == null || contratos.isEmpty()) {
			ap.setStatus("Livre");
		} else {// Encontrar o contrato ativo
			Contrato contratoAtivo = contratos.stream().filter(Contrato::isStatus).findFirst().orElse(null);

			if (contratoAtivo != null && contratoAtivo.getCliente() != null) {
				ap.setStatus(contratoAtivo.getCliente().getNome());
			} else {
				ap.setStatus("Livre");
			}
		}
		return ap.getStatus();
	}
	
	public String save (Apartamento ap) {
		this.arepo.save(ap);
		salvarLog("save", "apartamento", ap.getApnum());
		
		return ap.getApnum()+ "ap salvo";
	}
	
	public String update (Long apnum, Apartamento ap) {
		ap.setApnum(apnum);
		this.arepo.save(ap);
		salvarLog("Update", "apartamento", ap.getApnum());
		return "editado com sucesso";
	}
	
	public String delete (Long apnum) {
		this.arepo.deleteById(apnum);
		salvarLog("delete", "apartamento", apnum);
		return "ap deletado";
	}
	public List<Apartamento> listAll(){
		return this.arepo.findAll();
	}

}
