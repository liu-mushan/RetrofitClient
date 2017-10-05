package com.mushan.network.environment;

/**
 * Created by hp on 31/08/2017.
 */

public class OnlineEnvironment extends Environment {
    // ************下面的域名请不要加https,末尾也请不要加/，域名解析的时候会自动加上*********/
    public static final String SECURITIES_API_URL_PREFIX = "securitiesapi.stocks666.com";
    public static final String USER_API_URL_PREFIX = "userapi.stocks666.com";
    public static final String PASSPORT_API_URL_PREFIX = "passportapi.stocks666.com";
    public static final String INFORMATION_API_URL_PREFIX = "infoapi.stocks666.com";
    public static final String WATCHLIST_API_URL_PREFIX = "watchlistapi.stocks666.com";
    public static final String SEARCH_API_URL_PREFIX = "searchapi.stocks666.com";
    public static final String QUOTE_API_URL_PREFIX = "quoteapi.stocks666.com";
    public static final String PORTFOLIO_API_URL_PREFIX = "portfolioapi.stocks666.com";
    public static final String STOCKS_API_URL_PREFIX = "fmstockapi.stocks666.com";
    public static final String STOCKS_NEWS_URL_PREFIX = "news.stocks666.com";
    public static final String ACTIVITY_API_URL_PREFIX = "activityapi.stocks666.com";
    public static final String WALS_API_URL_PREFIX = "wlas.webull.com";
    public static final String SUGGESTION_API_URL_PREFIX = "suggestionapi.webull.com";


    /********** push 服务器，域名解析的时候会加上tcp://，末尾会加上:9018 ********/
    public static final String MQTT_PUSH_ADDRESS = "push.stocks666.com";

    static {
        sServiceToDefaultBaseUrlMap.put(ApiType.SECURITIES.getType(), SECURITIES_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.USER.getType(), USER_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.PASSPORT.getType(), PASSPORT_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.INFORMATION.getType(), INFORMATION_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.WATCHLIST.getType(), WATCHLIST_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.SEARCH.getType(), SEARCH_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.QUOTE.getType(), QUOTE_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.PORTFOLIO.getType(), PORTFOLIO_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.STOCKS.getType(), STOCKS_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.NEWS.getType(), STOCKS_NEWS_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.ACTIVITY.getType(), ACTIVITY_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.PUSH.getType(), MQTT_PUSH_ADDRESS);
        sServiceToDefaultBaseUrlMap.put(ApiType.WLAS.getType(), WALS_API_URL_PREFIX);
        sServiceToDefaultBaseUrlMap.put(ApiType.SUGGESTION.getType(), SUGGESTION_API_URL_PREFIX);

    }
}
