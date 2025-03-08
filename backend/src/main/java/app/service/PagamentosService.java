package app.service;

import app.Repository.ContratoRepository;
import app.Repository.PagamentosRepository;
import app.entity.Contrato;
import app.entity.Pagamentos;
import app.entity.StatusPagamento;
import app.entity.User;
import app.entity.dto.PagamentoDTO;
import app.entity.dto.PagamentoResumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentosService {

    private final PagamentosRepository pagamentosRepository;
    private final UserService userService;
    private final ContratoRepository contratoRepository;

    public PagamentosService(PagamentosRepository pagamentosRepository, UserService userService, ContratoRepository contratoRepository){
        this.pagamentosRepository = pagamentosRepository;
        this.userService = userService;
        this.contratoRepository = contratoRepository;
    }

    public String save (Pagamentos pagamentos) {

        this.pagamentosRepository.save(pagamentos);
        return pagamentos + "Pagamento realizado";

    }

    public String update (Long id, Pagamentos pagamentos) {

        pagamentos.setId(id);
        this.pagamentosRepository.save(pagamentos);
        return pagamentos + "Editado com sucesso";

    }

    public String delete (Long id) {

        this.pagamentosRepository.deleteById(id);
        return "Registro deletado";

    }

    public PagamentoDTO getProximoPagamento(String email) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return pagamentosRepository.findTopByContrato_InquilinoAndStatusOrderByVencimentoAsc(user, StatusPagamento.A_VENCER)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Nenhum pagamento 'A VENCER' encontrado"));
    }

    public List<PagamentoDTO> getUltimosPagamentos(String email) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return pagamentosRepository.findTop6ByContrato_InquilinoOrderByVencimentoDesc(user)
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PagamentoDTO> getTodosPagamentos(String email) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return pagamentosRepository.findByContrato_Inquilino(user)
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Pagamentos> listAll() {
        return this.pagamentosRepository.findAll();
    }

    private PagamentoDTO toDTO(Pagamentos pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getTitular(),
                pagamento.getIdApartamento(),
                pagamento.getValor(),
                pagamento.getVencimento(),
                pagamento.getStatus()
        );
    }

    public List<PagamentoResumoDTO> getPagamentosDoPredio(String email) {
        List<Contrato> contratos = contratoRepository.findByAdministradorId(email);
        List<PagamentoResumoDTO> pagamentos = contratos.stream()
                .flatMap(contrato -> contrato.getPagamentos().stream()) // Obtém todos os pagamentos dos contratos
                .map(this::toResumoDTO) // Converte para DTO
                .collect(Collectors.toList());

        return pagamentos;
    }

    public BigDecimal getTotalPagamentosPagosNoMes(String email) {
        LocalDate inicioMes = LocalDate.now().withDayOfMonth(1);
        LocalDate fimMes = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        return pagamentosRepository.getTotalPagamentosPagosNoMes(email, inicioMes, fimMes);
    }

    public BigDecimal getTotalPagamentosPendentesNoMes(String adminId) {
        LocalDate inicioMes = LocalDate.now().withDayOfMonth(1);
        LocalDate fimMes = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        return pagamentosRepository.getTotalPagamentosPendentesNoMes(adminId, inicioMes, fimMes);
    }

    public List<PagamentoResumoDTO> getPagamentosPendentes(String adminId) {
        return pagamentosRepository.getPagamentosPendentes(adminId);
    }

    public List<Pagamentos> getTodosPagamentosDoPredio(String adminId) {
        return pagamentosRepository.getTodosPagamentosDoPredio(adminId);
    }

    private PagamentoResumoDTO toResumoDTO(Pagamentos pagamento) {
        return new PagamentoResumoDTO(
                pagamento.getIdApartamento(),
                pagamento.getVencimento(),
                pagamento.getValor(),
                pagamento.getStatus()
        );
    }

    public void gerarPagamentosParaContrato(Contrato contrato) {
        LocalDate dataInicio = contrato.getEntrada();
        int anoFinal = contrato.getReferenteAno();
        LocalDate dataFim = LocalDate.of(anoFinal, 12, 10);

        List<Pagamentos> pagamentos = new ArrayList<>();

        // Percorre os meses do contrato gerando pagamentos
        while (!dataInicio.isAfter(dataFim)) {
            LocalDate vencimento = LocalDate.of(dataInicio.getYear(), dataInicio.getMonth(), 10); // Sempre dia 10

            Pagamentos pagamento = new Pagamentos();
            pagamento.setContrato(contrato);
            pagamento.setVencimento(vencimento);
            pagamento.setValor(contrato.getValorAluguel().add(contrato.getValorCondominio()).add(contrato.getValorIptu()).add(contrato.getValorInternet()));
            pagamento.setStatus(StatusPagamento.FUTURO);

            pagamentos.add(pagamento);
            dataInicio = dataInicio.plusMonths(1);
        }

        pagamentosRepository.saveAll(pagamentos);
    }
}
