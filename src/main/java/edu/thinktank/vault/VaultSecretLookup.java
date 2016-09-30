package edu.thinktank.vault;

import org.apache.commons.lang3.text.StrLookup;

public class VaultSecretLookup extends StrLookup {

    private final VaultClient client;

    public VaultSecretLookup(final VaultClient client) {
        this.client = client;
    }

    @Override
    public String lookup(String key) {
        if(hasFieldname(key)) {
            String[] values = key.split("@");
            return client.read(values[0]).get(values[1]);
        } else {
            return client.read(key).get("value");
        }
    }

    private boolean hasFieldname(final String key) {
        return key.contains("@");
    }

}
