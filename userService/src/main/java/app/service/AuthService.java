package app.service;

import app.dto.user.request.UserRequestDTO;
import app.dto.user.response.UserResponseDTO;
import app.model.Account;
import app.model.User;
import app.repository.AccountRepository;
import app.repository.AuthorityRepository;
import app.repository.UserRepository;
import app.service.exceptions.EnumUserException;
import app.service.exceptions.NotFoundException;
import app.service.exceptions.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDTO createUser(UserRequestDTO request) {
        if (this.userRepository.existsUsersByEmailIgnoreCase(request.getEmail()))
            throw new UserException(EnumUserException.already_exist, String.format("Ya existe un usuario con email %s", request.getEmail()));

        Set<Long> accountIds = request.getAccounts();
        if (accountIds == null || accountIds.contains(null)) {
            throw new IllegalArgumentException("La lista de IDs de cuentas no debe contener elementos nulos");
        }

        final List<Account> accounts = this.accountRepository.findAllById(accountIds);

        if (accounts.isEmpty())
            throw new UserException(EnumUserException.invalid_account, String.format("No se encontró ninguna cuenta con id %s", accountIds.toString()));

        final var authorities = request.getAuthorities()
                .stream()
                .map(string -> this.authorityRepository.findById(string).orElseThrow(() -> new NotFoundException("Authority", string)))
                .toList();

        if (authorities.isEmpty())
            throw new UserException(EnumUserException.invalid_authorities,
                    String.format("No se encontró ninguna autoridad con id %s", request.getAuthorities().toString()));

        final var user = new User(request);
        user.setAccount(accounts);
        user.setAuthorities(authorities);

        final var encryptedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encryptedPassword);

        final var createdUser = this.userRepository.save(user);
        return new UserResponseDTO(createdUser);
    }

}
