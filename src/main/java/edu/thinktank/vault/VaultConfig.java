package edu.thinktank.vault;

import java.util.function.Function;

import static edu.thinktank.vault.URLPathHelper.*;
import static java.util.Objects.isNull;

public class VaultConfig {

    public static final String VERSION = "v1";
    private final Function<String, String> envProvider;

    public VaultConfig(Function<String, String> env) {
        envProvider = env;
    }

    public String getBaseURL() {
        final String address = envProvider.apply("VAULT_ADDR");
        return address == null ? null : endsWithSlash(address);
    }

    public String getPrefixPath() {
        final String prefix = envProvider.apply("VAULT_PREFIXPATH");
        return prefix == null ? "" : startsWithSlash(endsWithoutSlash(prefix));
    }

    public String getURL(final String secretPath) {
        return getBaseURL() + VERSION + getPrefixPath() + startsWithSlash(secretPath);
    }

    public String getToken() {
        return envProvider.apply("VAULT_TOKEN");
    }

    public Boolean isEnabled() {
        return notNull(envProvider.apply("VAULT_ADDR")) && notNull(envProvider.apply("VAULT_TOKEN"));
    }

    private boolean notNull(final Object value) {
        return !isNull(value);
    }

}
