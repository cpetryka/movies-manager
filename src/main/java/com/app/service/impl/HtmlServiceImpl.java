package com.app.service.impl;

import com.app.service.HtmlService;

import static j2html.TagCreator.*;

public class HtmlServiceImpl implements HtmlService {
    private static final String BOX_ATTRS = ".box";
    private static final String BOX_HEADING_ATTRS = ".box-heading";
    private static final String BOX_CONTENT_ATTRS = ".box-content";

    @Override
    public <T> String oneToHtml(String header, T element) {
        if (header == null || header.isEmpty()) {
            throw new IllegalArgumentException("Header is null or empty");
        }

        if (element == null) {
            throw new IllegalArgumentException("Element is null");
        }

        return div(
                attrs(BOX_ATTRS),
                h2(
                        attrs(BOX_HEADING_ATTRS),
                        header
                ),
                div(
                        attrs(BOX_CONTENT_ATTRS),
                        p(element.toString())
                )
        ).render();
    }
}
