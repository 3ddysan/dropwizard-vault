package edu.thinktank.vault;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class URLPathHelperTest {

    @Test
    public void startsWithSlash() throws Exception {
        final String path = "path";

        final String result = URLPathHelper.startsWithSlash(path);

        assertThat(result).isEqualTo("/" + path);
    }

    @Test
    public void startsWithExistingSlash() throws Exception {
        final String path = "/path";

        final String result = URLPathHelper.startsWithSlash(path);

        assertThat(result).isEqualTo(path);
    }

    @Test
    public void endsWithSlash() throws Exception {
        final String path = "path";

        final String result = URLPathHelper.endsWithSlash(path);

        assertThat(result).isEqualTo(path + "/");
    }

    @Test
    public void endsWithExistingSlash() throws Exception {
        final String path = "path/";

        final String result = URLPathHelper.endsWithSlash(path);

        assertThat(result).isEqualTo(path);
    }

    @Test
    public void endsWithoutSlash() throws Exception {
        final String path = "path";

        final String result = URLPathHelper.endsWithoutSlash(path);

        assertThat(result).isEqualTo(path);
    }

    @Test
    public void endsWithoutExistingSlash() throws Exception {
        final String path = "path/";

        final String result = URLPathHelper.endsWithoutSlash(path);

        assertThat(result).isEqualTo("path");
    }

}