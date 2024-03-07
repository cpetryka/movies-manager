package com.app.service.impl;

import com.app.service.HtmlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.MoviesTestData.MOVIE_1;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class HtmlServiceImplOneToHtmlTest {
    private static final HtmlService htmlService = new HtmlServiceImpl();

    @Test
    @DisplayName("when header is null")
    void test1() {
        assertThatThrownBy(() -> htmlService.oneToHtml(null, MOVIE_1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Header is null or empty");
    }

    @Test
    @DisplayName("when header is empty")
    void test2() {
        assertThatThrownBy(() -> htmlService.oneToHtml("", MOVIE_1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Header is null or empty");
    }

    @Test
    @DisplayName("when element is null")
    void test3() {
        assertThatThrownBy(() -> htmlService.oneToHtml("Movie", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Element is null");
    }

    @Test
    @DisplayName("when single object is converted to html correctly")
    void test4() {
        var expectedHtml = "<div class=\"box\"><h2 class=\"box-heading\">Movie</h2><div class=\"box-content\"><p>Movie(title=SPIDER MAN NO WAY HOME, genre=ACTION, director=JON WATTS, releaseDate=2021-12-17, cast=[TOM HOLLAND, ZENDAYA, BENEDICT CUMBERBATCH], duration=148, rating=8.5)</p></div></div>";

        assertThat(htmlService.oneToHtml("Movie", MOVIE_1))
                .isEqualTo(expectedHtml);
    }
}
