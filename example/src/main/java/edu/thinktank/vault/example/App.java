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
    public void run(Config config, Environment environment) throws Exception {

    }

    public static void main(String[] args) {

    }

}
