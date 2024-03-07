package com.app.service;

import java.util.List;

public interface HtmlService {
    <T> String oneToHtml(String header, T element);
    <T> String manyToHtml(String header, List<T> items);
}
