package app.mensal.service;
import app.Repository.ClienteRepository;
import app.Repository.UserRepository;
import app.entity.Cliente;
import app.entity.Predio;
import app.entity.Role;
import app.entity.User;
import app.entity.dto.InquilinoDTO;
import app.entity.dto.InquilinoRequestDTO;
import app.security.JwtServiceGenerator;
import app.security.Login;
import app.service.PredioService;
import app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private JwtServiceGenerator jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PredioService predioService;

    @InjectMocks
    private UserService userService;

    private User user;
    private Cliente cliente;
    private Login login;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("admin@email.com");
        user.setPassword("senha123");
        user.setNome("Admin");
        user.setRole(Role.ADMIN);

        cliente = new Cliente();
        cliente.setId(2L);
        cliente.setEmail("cliente@email.com");
        cliente.setPassword("senha123");

        login = new Login();
        login.setEmail("admin@email.com");
        login.setPassword("senha123");
    }
}