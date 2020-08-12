package com.etournament.proj.security.providers;

import com.etournament.proj.security.service.impl.UserDetailsImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

    private final Logger LOG = LoggerFactory.getLogger(FirebaseAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String idToken = authentication.getCredentials().toString();

        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            UserDetailsImpl principal = new UserDetailsImpl(
                    decodedToken.getTenantId(),
                    decodedToken.getUid(),
                    decodedToken.getEmail(),
                    idToken, authorities);

            return new UsernamePasswordAuthenticationToken(principal, idToken);
        } catch (FirebaseAuthException e) {
            LOG.error("Error occured while verifying IdToken: {} => {}", idToken, e.getMessage());
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isInstance(UsernamePasswordAuthenticationToken.class);
    }
}
