package edu.thinktank.vault;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VaultConfigTest {

    @Test
    public void shouldGetBaseURL() throws Exception {
        final String baseUrl = "base.url/";
        final VaultConfig vaultConfig = new VaultConfig(k -> "VAULT_ADDR".equals(k) ? baseUrl : null);

        final String baseURL = vaultConfig.getBaseURL();

        assertThat(baseURL).isEqualTo(baseUrl);
    }

    @Test
    public void shouldNotGetBaseURL() throws Exception {
        final VaultConfig vaultConfig = new VaultConfig(k -> null);

        final String baseURL = vaultConfig.getBaseURL();

        assertThat(baseURL).isNull();
    }

    @Test
    public void shouldGetPrefixPath() throws Exception {
        final String prefix = "/prefix";
        final VaultConfig vaultConfig = new VaultConfig(k -> "VAULT_PREFIXPATH".equals(k) ? prefix : null);

        final String prefixURL = vaultConfig.getPrefixPath();

        assertThat(prefixURL).isEqualTo(prefix);
    }

    @Test
    public void shouldNotGetPrefixPath() throws Exception {
        final VaultConfig vaultConfig = new VaultConfig(k -> null);

        final String prefixURL = vaultConfig.getPrefixPath();

        assertThat(prefixURL).isEqualTo("");
    }

    @Test
    public void shouldGetURLWithoutPrefix() throws Exception {
        final String baseUrl = "http://base.url/";
        final String secretPath = "secret";
        final VaultConfig vaultConfig = new VaultConfig(k -> "VAULT_ADDR".equals(k) ? baseUrl : null);

        final String url = vaultConfig.getURL(secretPath);

        assertThat(url).isEqualTo(baseUrl + "v1/" + secretPath);
    }

    @Test
    public void shouldGetURLWithPrefix() throws Exception {
        final String baseUrl = "http://base.url/";
        final String prefix = "/prefix/";
        final String secretPath = "secret";
        final VaultConfig vaultConfig = new VaultConfig(k -> {
            if ("VAULT_ADDR".equals(k))
                return baseUrl;
            if("VAULT_PREFIXPATH".equals(k))
                return prefix;
            return null;
        });

        final String url = vaultConfig.getURL(secretPath);

        assertThat(url).isEqualTo(baseUrl + "v1" + prefix + secretPath);
    }

    @Test
    public void shouldGetURLWithEmptyPath() throws Exception {
        final String baseUrl = "http://base.url/";
        final VaultConfig vaultConfig = new VaultConfig(k -> "VAULT_ADDR".equals(k) ? baseUrl : null);

        final String url = vaultConfig.getURL("");

        assertThat(url).isEqualTo(baseUrl + "v1/");
    }

    @Test
    public void shouldGetToken() throws Exception {
        final String token = "token";
        final VaultConfig vaultConfig = new VaultConfig(k -> "VAULT_TOKEN".equals(k) ? token : null);

        final String result = vaultConfig.getToken();

        assertThat(result).isEqualTo(token);
    }

    @Test
    public void shouldNotGetToken() throws Exception {
        final VaultConfig vaultConfig = new VaultConfig(k -> null);

        final String result = vaultConfig.getToken();

        assertThat(result).isNull();
    }

    @Test
    public void isEnabled() throws Exception {
        final VaultConfig vaultConfig = new VaultConfig(k -> {
            if("VAULT_ADDR".equals(k))
                return "base.url";
            if("VAULT_TOKEN".equals(k))
                return "token";
            return null;
        });

        final Boolean isEnabled = vaultConfig.isEnabled();

        assertThat(isEnabled).isTrue();
    }

    @Test
    public void isNotEnabledWithoutToken() throws Exception {
        final VaultConfig vaultConfig = new VaultConfig(k -> {
            if ("VAULT_ADDR".equals(k))
                return "base.url";
            return null;
        });

        final Boolean isEnabled = vaultConfig.isEnabled();

        assertThat(isEnabled).isFalse();
    }

    @Test
    public void isNotEnabledWithoutURL() throws Exception {
        final VaultConfig vaultConfig = new VaultConfig(k -> {
            if("VAULT_TOKEN".equals(k))
                return "token";
            return null;
        });

        final Boolean isEnabled = vaultConfig.isEnabled();

        assertThat(isEnabled).isFalse();
    }

    @Test
    public void isNotEnabledWithoutTokenAndURL() throws Exception {
        final VaultConfig vaultConfig = new VaultConfig(k -> null);

        final Boolean isEnabled = vaultConfig.isEnabled();

        assertThat(isEnabled).isFalse();
    }

}