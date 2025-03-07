package app.service;

import app.Repository.PagamentosRepository;
import app.entity.Pagamentos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PagamentosService {

    private final PagamentosRepository pagamentosRepository;
    public PagamentosService(PagamentosRepository pagamentosRepository){
        this.pagamentosRepository = pagamentosRepository;
    }

    public String save (Pagamentos pagamentos) {

        this.pagamentosRepository.save(pagamentos);
        return pagamentos + "Pagamento realizado";

    }

    public String update (Long id, Pagamentos pagamentos) {

        pagamentos.setd(id);
        this.pagamentosRepository.save(pagamentos);
        return pagamentos + "Editado com sucesso";

    }

    public String delete (Long id) {

        this.pagamentosRepository.deleteById(id);
        return "Registro deletado";

    }

    public List<Pagamentos> listAll() {
        return this.pagamentosRepository.findAll();
    }

}
