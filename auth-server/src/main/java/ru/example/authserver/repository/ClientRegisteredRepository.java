package ru.example.authserver.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Component;
import ru.example.authserver.domain.Client;

@Component
@RequiredArgsConstructor
public class ClientRegisteredRepository implements RegisteredClientRepository {

    private final ClientRepository repository;

    @Override
    public void save(RegisteredClient registeredClient) {
        repository.save(new Client(
                registeredClient.getClientId(),
                registeredClient.getClientSecret(),
                registeredClient.getClientName(),
                registeredClient.getRedirectUris().stream().findFirst().get(),
                registeredClient.getScopes().stream().findFirst().get()
        ));
    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id));

        return mapToRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = repository.findByClientId(clientId)
                .orElseThrow(() -> new IllegalArgumentException(clientId));

        return mapToRegisteredClient(client);
    }

    private RegisteredClient mapToRegisteredClient(Client client) {
        return RegisteredClient.withId(client.getId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientName(client.getClientName())
                .redirectUri(client.getRedirectUri())
                .scope(client.getScope())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .build();
    }
}
