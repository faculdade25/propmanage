package app.mensal.controller;

import app.controller.PagamentosController;
import app.entity.Pagamentos;
import app.entity.dto.PagamentoDTO;
import app.entity.dto.PagamentoResumoDTO;
import app.service.PagamentosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PagamentosControllerTest {

    @InjectMocks
    private PagamentosController pagamentosController;

    @Mock
    private PagamentosService pagamentosService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(userDetails.getUsername()).thenReturn("admin@email.com");
    }

    @Test
    void testGetProximoPagamento() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        when(pagamentosService.getProximoPagamento("admin@email.com")).thenReturn(pagamentoDTO);

        ResponseEntity<PagamentoDTO> response = pagamentosController.getProximoPagamento(userDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}