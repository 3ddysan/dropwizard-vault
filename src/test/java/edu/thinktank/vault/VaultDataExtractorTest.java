package edu.thinktank.vault;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class VaultDataExtractorTest {

    @Test
    public void extractData() throws Exception {
        VaultDataExtractor vaultDataExtractor = new VaultDataExtractor();

        final Map<String, String> data = vaultDataExtractor.extractData("{\"data\":{\"key\":\"secret\"}}");

        assertThat(data).containsExactly(entry("key", "secret"));
    }

    @Test(expected = IllegalStateException.class)
    public void extractMissingData() throws Exception {
        VaultDataExtractor vaultDataExtractor = new VaultDataExtractor();

        final Map<String, String> data = vaultDataExtractor.extractData("{}");
    }

    @Test
    public void extractEmptyData() throws Exception {
        VaultDataExtractor vaultDataExtractor = new VaultDataExtractor();

        final Map<String, String> data = vaultDataExtractor.extractData("{\"data\":{}}");

        assertThat(data).isEmpty();
    }

}