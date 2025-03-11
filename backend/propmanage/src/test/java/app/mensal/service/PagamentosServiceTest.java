package app.mensal.service;
import app.Repository.ContratoRepository;
import app.Repository.PagamentosRepository;
import app.entity.*;
import app.entity.dto.PagamentoDTO;
import app.service.PagamentosService;
import app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentosServiceTest {

    @Mock
    private PagamentosRepository pagamentosRepository;

    @Mock
    private UserService userService;

    @Mock
    private ContratoRepository contratoRepository;

    @InjectMocks
    private PagamentosService pagamentosService;

    private Pagamentos pagamento;
    private User user;
    private Contrato contrato;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");

        contrato = new Contrato();
        contrato.setInquilino(user);

        pagamento = new Pagamentos();
        pagamento.setId(1L);
        pagamento.setContrato(contrato);
        pagamento.setValor(new BigDecimal("1000.00"));
        pagamento.setVencimento(LocalDate.now().plusDays(10));
        pagamento.setStatus(StatusPagamento.A_VENCER);
    }

    @Test
    void testSave() {
        when(pagamentosRepository.save(any(Pagamentos.class))).thenReturn(pagamento);

        String result = pagamentosService.save(pagamento);

        assertTrue(result.contains("Pagamento realizado"));
        verify(pagamentosRepository, times(1)).save(pagamento);
    }

    @Test
    void testUpdate() {
        when(pagamentosRepository.save(any(Pagamentos.class))).thenReturn(pagamento);

        String result = pagamentosService.update(1L, pagamento);

        assertTrue(result.contains("Editado com sucesso"));
        verify(pagamentosRepository, times(1)).save(pagamento);
    }

    @Test
    void testDelete() {
        doNothing().when(pagamentosRepository).deleteById(1L);

        String result = pagamentosService.delete(1L);

        assertEquals("Registro deletado", result);
        verify(pagamentosRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetProximoPagamento() {
        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(pagamentosRepository.findTopByContrato_InquilinoAndStatusOrderByVencimentoAsc(user, StatusPagamento.A_VENCER)).thenReturn(Optional.of(pagamento));

        PagamentoDTO dto = pagamentosService.getProximoPagamento("test@example.com");

        assertNotNull(dto);
        assertEquals(pagamento.getValor(), dto.getValor());
    }

    @Test
    void testGetUltimosPagamentos() {
        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(pagamentosRepository.findTop6ByContrato_InquilinoOrderByVencimentoDesc(user)).thenReturn(List.of(pagamento));

        List<PagamentoDTO> result = pagamentosService.getUltimosPagamentos("test@example.com");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
