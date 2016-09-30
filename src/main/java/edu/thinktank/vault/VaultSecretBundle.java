package edu.thinktank.vault;

import io.dropwizard.Bundle;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.function.Function;

public class VaultSecretBundle implements Bundle {

    private final OkHttpClient client;
    private final Function<String, String> env;

    public VaultSecretBundle() {
        this(new OkHttpClient(), System::getenv);
    }

    public VaultSecretBundle(final OkHttpClient client, final Function<String, String> env) {
        this.client = client;
        this.env = env;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        final VaultConfig config = new VaultConfig(env);
        if(config.isEnabled()) {
            bootstrap.setConfigurationSourceProvider(
                    new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                            new StrSubstitutor(new VaultSecretLookup(new VaultClient(client, config)))
                    )
            );
        }
    }

    @Override
    public void run(Environment environment) {

    }

}
