package com.etournament.proj.security.config;

import com.google.firebase.FirebaseApp;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void setupFirebase() {
        FirebaseApp.initializeApp();
    }
}
