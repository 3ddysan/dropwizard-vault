package edu.thinktank.vault;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class VaultDataExtractor {

    private final ObjectMapper objectMapper;

    public VaultDataExtractor() {
        this(new ObjectMapper());
    }

    public VaultDataExtractor(final ObjectMapper mapper) {
        objectMapper = mapper;
    }

    public Map<String, String> extractData(final String json) {
        try {
            final JsonNode data = objectMapper.readTree(json).get("data");
            if(data == null) {
                throw new IllegalStateException("data property missing in json: " + json);
            }
            return objectMapper.convertValue(data, Map.class);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
