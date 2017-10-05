package com.mushan.network.environment;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public class DnsAppApi {
    private static final String HTTPS = "https://";
    private static final String TCP = "tcp://";
    private static final String PORT = ":9018";

    private static Environment sEnvironment;

    public static void init() {
        sEnvironment = Environment.isOnlineEnvironment() ? new OnlineEnvironment() : new PreEnvironment();
    }

    public static String getBaseUrl(Environment.ApiType apiType) {
        String baseUlr = sEnvironment.getServiceBaseUrl(apiType.getType());
        if (Environment.ApiType.PUSH.getType().equalsIgnoreCase(apiType.getType())) {
            if (!baseUlr.startsWith(TCP)) {
                baseUlr = TCP + baseUlr;
            }
            if (!baseUlr.endsWith(PORT)) {
                baseUlr = baseUlr + PORT;
            }
            return baseUlr;
        }

        if (!baseUlr.startsWith(HTTPS)) {
            baseUlr = HTTPS + baseUlr;
        }

        if (!baseUlr.endsWith("/")) {
            baseUlr = baseUlr + "/";
        }
        return baseUlr;
    }
}
