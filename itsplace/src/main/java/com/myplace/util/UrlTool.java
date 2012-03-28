package com.myplace.util;

public class UrlTool {
    public static String addParamToURL(String url, String param, String value,
            boolean replace) {
        if (replace == true)
            url = removeParamFromURL(url, param);
        return url + ((url.indexOf("?") == -1) ? "?" : "&") + param + "="
                + value;
    }
 
    public static String removeParamFromURL(String url, String param) {
        String sep = "&";
        int startIndex = url.indexOf(sep + param + "=");
 
        if (startIndex == -1) {
            startIndex = url.indexOf("?" + param + "=");
            sep = "?";
        }
 
        if (startIndex != -1) {
            int endIndex = url.indexOf(sep, startIndex + 1);
            return url.substring(0, startIndex)
                    + (endIndex > 0 ? (sep == "?" ? "?"
                            + url.substring(endIndex + 1) : url
                            .substring(endIndex)) : "");
        }
 
        return url;
    }
}
