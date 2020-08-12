package com.etournament.proj.security.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.cred.filename}")
    private String firebaseCredFilename;

    @PostConstruct
    public void setupFirebase() throws IOException {
        InputStream serviceAccount =
                new ClassPathResource(firebaseCredFilename).getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://test-85a01.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
