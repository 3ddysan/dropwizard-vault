package edu.thinktank.vault;

import edu.thinktank.vault.util.MockClient;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class VaultClientTest {

    @Test
    public void read() throws Exception {
        final VaultClient vaultClient = createVaultClient("{ \"data\" : { \"value\":\"geheim\" } }");

        final Map<String, String> result = vaultClient.read("ignored");

        assertThat(result).containsExactly(entry("value", "geheim"));
    }

    @Test
    public void readEmptyData() throws Exception {
        final VaultClient vaultClient = createVaultClient("{ \"data\" : {} }");

        final Map<String, String> result = vaultClient.read("ignored");

        assertThat(result).isEmpty();
    }

    private VaultClient createVaultClient(String json) {
        final MockClient interceptor = new MockClient();
        interceptor.enqueue(new Response.Builder().code(200).body(ResponseBody.create(MediaType.parse("application/json"), json)));
        final OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new VaultClient(httpClient, new VaultConfig(k -> {
            if("VAULT_ADDR".equals(k)) {
                return "http://localhost/";
            }
            return "";
        }));
    }

}