package com.autowebinar.core.confluence;

import static com.autowebinar.core.data.ConstantVariables.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by VMoskalenko on 06.01.2017.
 * Session to create blog post
 */
public class ConfluenceRestSession {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();
    private final HttpHeaders headers;

    public ConfluenceRestSession() throws IOException {
        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        // create request body
        // set headers
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String encodedAuth = Base64.getEncoder().encodeToString(AUTH.getBytes(Charset.forName("US-ASCII")));
        headers.set("Authorization", "Basic " + encodedAuth);
    }

    public String writeBlogPost(BlogPost blogPost) throws IOException {
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(blogPost), headers);
        // send request and parse result
        ResponseEntity<String> response = restTemplate.exchange(SENTINEL_REST_API_URL, HttpMethod.POST, entity, String.class);
        HashMap<String,Object> result = new ObjectMapper().readValue(response.getBody(), HashMap.class);

        return (String)result.get("id");
    }


    public static class Space
    {
        public Space(String key) {
            this.key = key;
        }

        public String key;
    }

    public static class Storage
    {
        public Storage(String value, String representation) {
            this.value = value;
            this.representation = representation;
        }

        public String value;
        public String representation;
    }

    public static class Body
    {
        public Body(Storage storage) {
            this.storage = storage;
        }

        public Storage storage;

    }

    public static class BlogPost
    {
        public BlogPost(String type, String title, Space space, Body body) {
            this.type = type;
            this.title = title;
            this.space = space;
            this.body = body;
        }

        public String type;
        public String title;
        public Space space;
        public Body body;
    }

}
