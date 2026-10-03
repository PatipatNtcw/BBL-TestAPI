package com.bbl.testApi.configuretion;

import com.bbl.testApi.model.UserModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean("users")
    public List<UserModel> users() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (var input = new ClassPathResource("users.json").getInputStream()) {
            return objectMapper
                    .readerFor(new TypeReference<List<UserModel>>() {})
                    .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .readValue(input);
        }
    }
}
