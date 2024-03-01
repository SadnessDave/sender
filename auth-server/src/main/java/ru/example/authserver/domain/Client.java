package ru.example.authserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String clientId;
    private String clientSecret;

    private String clientName;
    private String redirectUri;

    private String scope;

    public Client(String clientId, String clientSecret, String clientName, String redirectUri, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.redirectUri = redirectUri;
        this.scope = scope;
    }
}
