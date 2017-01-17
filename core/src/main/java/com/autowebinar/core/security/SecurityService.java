package com.autowebinar.core.security;

import com.autowebinar.core.data.User;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by vmoskalenko on 16.01.2017.
 *
 */
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    MongoOperations mongoOperations;

    public UserDetails loadUserByUsername(String authCode) throws UsernameNotFoundException {

        GoogleIdToken.Payload payload = getPayload(authCode);
        final String userId = payload.getSubject();  // Use this value as a key to identify a user.

        Query searchUserQuery = new Query(Criteria.where("googleId").is(userId));
        User user = mongoOperations.findOne(searchUserQuery, User.class);

        if (user == null) {
            String email = payload.getEmail();
            final String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            user = new User(name, email, pictureUrl, userId);
            mongoOperations.save(user);
        }

        return new UserAccount(authCode, userId);
    }

    private GoogleIdToken.Payload getPayload(String authCode) {
        String CLIENT_SECRET_FILE = "client_secret.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CLIENT_SECRET_FILE).getFile());

        GoogleClientSecrets clientSecrets;
        GoogleTokenResponse tokenResponse;
        GoogleIdToken idToken;

        try {
            clientSecrets = GoogleClientSecrets.load(
                    JacksonFactory.getDefaultInstance(), new FileReader(file));
            tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    "https://www.googleapis.com/oauth2/v4/token",
                    clientSecrets.getDetails().getClientId(),
                    clientSecrets.getDetails().getClientSecret(),
                    authCode,
                    "postmessage")
                    .execute();

            String accessToken = tokenResponse.getAccessToken();

            // Use access token to call API
            GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

            // Get profile info from ID token
            idToken = tokenResponse.parseIdToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return idToken.getPayload();
    }
}
