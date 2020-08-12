package com.etournament.proj.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegistrationPlayer {

    @Id
    private String playerUsername;

    public RegistrationPlayer() {
    }

    public RegistrationPlayer(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrationPlayer that = (RegistrationPlayer) o;

        return playerUsername.equals(that.playerUsername);
    }

    @Override
    public int hashCode() {
        return playerUsername.hashCode();
    }
}
