package com.app.service;

import java.util.List;
import java.util.Map;

public interface HtmlService {
    <T> String oneToHtml(String header, T element);
    <T> String manyToHtml(String header, List<T> items);
    <K, V> String pairsToHtml(String header, Map<K, V> pairs);
}
