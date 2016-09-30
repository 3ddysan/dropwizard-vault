package edu.thinktank.vault.example;

import edu.thinktank.vault.VaultSecretBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<Config> {

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        bootstrap.addBundle(new VaultSecretBundle());
    }

    @Override
    public void run(Config config, Environment environment) {
        System.out.println("Secrets:");
        config.secrets.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    /**
     * Prepare vault secrets before running with shell command:
     *     vault write secret/path key1="secretKeyValue" value="secretValue"
     */
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

}
