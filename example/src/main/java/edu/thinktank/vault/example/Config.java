package edu.thinktank.vault.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import java.util.Map;

public class Config extends Configuration {
    @JsonProperty("secrets")
    public Map<String, String> secrets;
}
