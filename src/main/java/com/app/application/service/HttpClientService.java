package com.app.application.service;

import com.google.gson.reflect.TypeToken;

public interface HttpClientService {
    <T> T get(String url, TypeToken<T> type);
}
