package com.etournament.proj.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "registrations",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "orderId")
        }
)
@IdClass(RegistrationId.class)
public class Registration {

    @Id
    private String matchId;

    @Id
    private String userId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "orderId", columnDefinition = "VARCHAR(255)", insertable = true, updatable = false, nullable = false)
    private UUID orderId = UUID.randomUUID();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "registration_registration_players",
            joinColumns = {
                    @JoinColumn(name = "registration_match_id"),
                    @JoinColumn(name = "registration_user_id"),
            },
            inverseJoinColumns = @JoinColumn(name = "registration_player_player_username"))
    private Set<RegistrationPlayer> players = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private RegistrationStatus status;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<RegistrationPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(Set<RegistrationPlayer> players) {
        this.players = players;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}
