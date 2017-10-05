package com.mushan.network.environment;

/**
 * Created by hp on 31/08/2017.
 */

public final class PreEnvironment extends Environment {
    // ************下面的域名请不要加https,末尾也请不要加/，域名解析的时候会自动加上*********/
    public static final String PRE_SECURITIES_API_URL_PREFIX = "pre-securitiesapi.stocks666.com";
    public static final String PRE_USER_API_URL_PREFIX = "pre-userapi.stocks666.com";
    public static final String PRE_PASSPORT_API_URL_PREFIX = "pre-passportapi.stocks666.com";
    public static final String PRE_INFORMATION_API_URL_PREFIX = "pre-infoapi.stocks666.com";
    public static final String PRE_WATCHLIST_API_URL_PREFIX = "pre-watchlistapi.stocks666.com";
    public static final String PRE_SEARCH_API_URL_PREFIX = "pre-searchapi.stocks666.com";
    public static final String PRE_QUOTE_API_URL_PREFIX = "pre-quoteapi.stocks666.com";
    public static final String PRE_PORTFOLIO_API_URL_PREFIX = "pre-portfolioapi.stocks666.com";
    public static final String PRE_STOCKS_API_URL_PREFIX = "pre-fmstockapi.stocks666.com";
    public static final String PRE_STOCKS_NEWS_URL_PREFIX = "pre-news.stocks666.com";
    public static final String PRE_ACTIVITY_API_URL_PREFIX = "pre-activityapi.stocks666.com";
    public static final String PRE_WLAS_API_URL_PREFIX = "pre-wlas.webull.com";
    public static final String PRE_SUGGESTION_API_URL_PREFIX = "pre-suggestionapi.webull.com";

    /********** push 服务器，域名解析的时候会加上tcp://，末尾会加上:9018 ********/
    public static final String PRE_MQTT_PUSH_ADDRESS = "pre-push.stocks666.com";

    static {
        sServiceToDefaultBaseUrlMap.put(ApiType.SECURITIES.getType(), PRE_SECURITIES_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.USER.getType(), PRE_USER_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.PASSPORT.getType(), PRE_PASSPORT_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.INFORMATION.getType(), PRE_INFORMATION_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.WATCHLIST.getType(), PRE_WATCHLIST_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.SEARCH.getType(), PRE_SEARCH_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.QUOTE.getType(), PRE_QUOTE_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.PORTFOLIO.getType(), PRE_PORTFOLIO_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.STOCKS.getType(), PRE_STOCKS_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.NEWS.getType(), PRE_STOCKS_NEWS_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.ACTIVITY.getType(), PRE_ACTIVITY_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.PUSH.getType(), PRE_MQTT_PUSH_ADDRESS);
        sServiceToDefaultBaseUrlMap.put(ApiType.WLAS.getType(), PRE_WLAS_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.SUGGESTION.getType(), PRE_SUGGESTION_API_URL_PREFIX);

    }
}
