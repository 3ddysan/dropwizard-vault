package edu.thinktank.vault;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Map;

public class VaultClient {

    private final OkHttpClient client;
    private final VaultConfig config;
    private final VaultDataExtractor vaultDataExtractor = new VaultDataExtractor();

    public VaultClient(final OkHttpClient client, final VaultConfig config) {
        this.client = client;
        this.config = config;
    }

    public Map<String, String> read(final String key) {
            return vaultDataExtractor.extractData(readVaultJSON(key));
    }

    private String readVaultJSON(String key) {
        try(ResponseBody response = client.newCall(buildRequest(key)).execute().body()) {
            return response.string();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Request buildRequest(String key) {
        return new Request.Builder()
                .header("X-Vault-Token", config.getToken())
                .url(config.getURL(key))
                .build();
    }

}
