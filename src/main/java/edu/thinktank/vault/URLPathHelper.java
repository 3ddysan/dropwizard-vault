package edu.thinktank.vault;

public final class URLPathHelper {

    private URLPathHelper() {}

    public static String startsWithSlash(String url) {
        return url.startsWith("/") ? url : "/" + url;
    }

    public static  String endsWithSlash(String url) {
        return url.endsWith("/") ? url : url + "/";
    }

    public static  String endsWithoutSlash(String url) {
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }

}
