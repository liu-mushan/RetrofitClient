package com.mushan.network.environment;

import java.util.HashMap;

/**
 * Created by hp on 31/08/2017.
 */
public abstract class Environment {
    public enum ApiType {
        USER("user"),
        PASSPORT("passport"),
        ACTIVITY("activity"),
        SECURITIES("securities"),
        SEARCH("search"),
        INFORMATION("info"),
        PORTFOLIO("portfolio"),
        NEWS("news"),
        QUOTE("quote"),
        STOCKS("fmstock"),
        SUGGESTION("suggestion"),
        WATCHLIST("watchlist"),
        WLAS("wlas"),
        PUSH("push");


        private final String mType;

        ApiType(String type) {
            this.mType = type;
        }

        public String getType() {
            return mType;
        }
    }

    private static final int ONLINE_ENVIRONMENT_ID = 0;
    private static final String PREF_KEY_ENVIRONMENT_ID = "environment_id";

    static HashMap<String, String> sServiceToDefaultBaseUrlMap = new HashMap<>();

    String getServiceBaseUrl(String serviceName) {
        return sServiceToDefaultBaseUrlMap.get(serviceName);
    }

    /**
     * 获得当前网络环境 默认是生产
     * 只作为 内部测试使用
     *
     * @return 0-->正式 1-->预演
     */
    static boolean isOnlineEnvironment() {
        int tempEnvironmentValue = 0;
        //int tempEnvironmentValue = PreferencesUtil.getInstance().getInt(PREF_KEY_ENVIRONMENT_ID, ONLINE_ENVIRONMENT_ID);
        return tempEnvironmentValue == ONLINE_ENVIRONMENT_ID;
    }
}
