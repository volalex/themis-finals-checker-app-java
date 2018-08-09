package org.volgactf.checker;

import java.nio.charset.Charset;
import java.util.Base64;

public class AppProperties {
    private final String checkerUsername;
    private final String checkerPassword;
    private final String masterBasicAuth;


    public AppProperties() {
        this.checkerUsername = System.getenv("THEMIS_FINALS_AUTH_CHECKER_USERNAME");
        this.checkerPassword = System.getenv("THEMIS_FINALS_AUTH_CHECKER_PASSWORD");
        String masterUsername = System.getenv("THEMIS_FINALS_AUTH_MASTER_USERNAME");
        String masterPassword = System.getenv("THEMIS_FINALS_AUTH_MASTER_PASSWORD");
        this.masterBasicAuth = Base64.getUrlEncoder()
                .encodeToString((masterUsername+":"+masterPassword)
                        .getBytes(Charset.defaultCharset()));
    }

    public String getCheckerUsername() {
        return checkerUsername;
    }

    public String getCheckerPassword() {
        return checkerPassword;
    }

    public String getMasterBasicAuth() {
        return masterBasicAuth;
    }
}
