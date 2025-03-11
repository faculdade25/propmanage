package app.mensal.service;
import app.Repository.ApartamentoRepository;
import app.Repository.PredioRepository;
import app.Repository.UserRepository;
import app.entity.Apartamento;
import app.entity.Predio;
import app.entity.Role;
import app.entity.User;
import app.entity.dto.ApartamentoDTO;
import app.service.PredioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PredioServiceTest {

    @Mock
    private PredioRepository predioRepository;

    @Mock
    private ApartamentoRepository apartamentoRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PredioService predioService;

    private Predio predio;
    private Apartamento apartamento;
    private User user;

    @BeforeEach
    void setUp() {
        predio = new Predio();
        predio.setId(1L);
        predio.setEmail("admin@email.com");

        apartamento = new Apartamento();
        apartamento.setId(1L);
        apartamento.setApnum(101);
        apartamento.setPredio(predio);

        user = new User();
        user.setId(1L);
        user.setEmail("admin@email.com");
        user.setRole(Role.ADMIN);
    }

    @Test
    void testListarTodos() {
        when(predioRepository.findAll()).thenReturn(List.of(predio));
        List<Predio> result = predioService.listarTodos();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(predio.getId(), result.get(0).getId());
    }

    @Test
    void testBuscarPorId_Success() {
        when(predioRepository.findById(1L)).thenReturn(Optional.of(predio));
        Predio result = predioService.buscarPorId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testBuscarPorId_NotFound() {
        when(predioRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> predioService.buscarPorId(1L));
    }

    @Test
    void testGetApartamentosByEmailAdmin_Success() {
        when(predioRepository.findByEmail("admin@email.com")).thenReturn(Optional.of(predio));
        when(apartamentoRepository.findByPredio(predio)).thenReturn(List.of(apartamento));
        List<ApartamentoDTO> result = predioService.getApartamentosByEmailAdmin("admin@email.com");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getApnum());
    }

    @Test
    void testGetApartamentosByEmailAdmin_NotFound() {
        when(predioRepository.findByEmail("admin@email.com")).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> predioService.getApartamentosByEmailAdmin("admin@email.com"));
    }

    @Test
    void testIsAdmin_True() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertTrue(predioService.isAdmin(1L));
    }

    @Test
    void testIsAdmin_False() {
        user.setRole(Role.USER);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertFalse(predioService.isAdmin(1L));
    }
}
