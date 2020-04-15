package com.example.chucknorrisquotes;

public class YoutubeConfig {

    //gets API key for youtube API module
    public YoutubeConfig() {
    }

    private static final String API_KEY = "AIzaSyDowJUtHhW-P_icoS8WHstBWPbvk1g175k";

    public static String getApiKey() {
        return API_KEY;
    }
}
