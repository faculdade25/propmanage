package app.service;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtServiceGenerator jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ClienteRepository clienteRepository;
    private final PredioService predioService;

    public UserService(UserRepository userRepository,
                       JwtServiceGenerator jwtService,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       ClienteRepository clienteRepository, PredioService predioService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.clienteRepository = clienteRepository;
        this.predioService = predioService;
    }

    public String logar(Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        return jwtService.generateToken(user);
    }

    public String logarUser(Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        Cliente user = clienteRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        return jwtService.generateTokenUser(user);
    }

    public String cadastro(User usuario) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(usuario.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Usuário já cadastrado com este email");
        }

        User user = new User();
        user.setEmail(usuario.getEmail());
        user.setPassword(passwordEncoder.encode(usuario.getPassword()));
        user.setNome(usuario.getNome());
        user.setSobrenome(usuario.getSobrenome());

        userRepository.save(user);

        Login login = new Login();
        login.setEmail(usuario.getEmail());
        login.setPassword(usuario.getPassword());

        return logar(login);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<InquilinoDTO> listarInquilinos(String email) {
        User admin = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        return userRepository.findInquilinosByPredio(admin.getId());
    }

    public User criarInquilino(Long adminId, InquilinoRequestDTO dto) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        Predio predio = predioService.findByEmail(admin.getEmail());

        if (predio == null) {
            throw new RuntimeException("Administrador não está vinculado a nenhum prédio.");
        }

        User inquilino = new User();
        inquilino.setNome(dto.getNome());
        inquilino.setSobrenome(dto.getSobrenome());
        inquilino.setEmail(dto.getEmail());
        inquilino.setTelefone(dto.getTelefone());
        inquilino.setCpf(dto.getCpf());
        inquilino.setPassword(passwordEncoder.encode(inquilino.getNome()));
        inquilino.setRg(dto.getRg());
        inquilino.setProfissao(dto.getProfissao());
        inquilino.setNascimento(dto.getNascimento());
        inquilino.setRole(Role.USER);

        userRepository.save(inquilino);
        return inquilino;
    }

    public User atualizarInquilino(Long adminId, Long inquilinoId, InquilinoRequestDTO dto) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        Predio predio = predioService.findByEmail(admin.getEmail());

        if (predio == null) {
            throw new RuntimeException("Administrador não está vinculado a nenhum prédio.");
        }

        User inquilino = userRepository.findById(inquilinoId)
                .orElseThrow(() -> new RuntimeException("Inquilino não encontrado"));

        boolean pertenceAoPredio = inquilino.getContratos().stream()
                .anyMatch(contrato -> contrato.getApartamento().getPredio().equals(predio));

        if (!pertenceAoPredio) {
            throw new RuntimeException("Você não tem permissão para editar este inquilino.");
        }

        inquilino.setNome(dto.getNome());
        inquilino.setSobrenome(dto.getSobrenome());
        inquilino.setEmail(dto.getEmail());
        inquilino.setTelefone(dto.getTelefone());
        inquilino.setCpf(dto.getCpf());
        inquilino.setRg(dto.getRg());
        inquilino.setProfissao(dto.getProfissao());
        inquilino.setNascimento(dto.getNascimento());

        return userRepository.save(inquilino);
    }
}