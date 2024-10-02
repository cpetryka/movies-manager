package com.app.service.impl;

import com.app.application.service.HtmlService;
import com.app.infrastructure.service.impl.HtmlServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.app.MoviesTestData.MOVIE_1;
import static com.app.MoviesTestData.MOVIE_2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class HtmlServiceManyToHtmlTest {
    private static final HtmlService htmlService = new HtmlServiceImpl();

    @Test
    @DisplayName("when header is null")
    void test1() {
        assertThatThrownBy(() -> htmlService.manyToHtml(null, List.of(MOVIE_1, MOVIE_2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Header is null or empty");
    }

    @Test
    @DisplayName("when header is empty")
    void test2() {
        assertThatThrownBy(() -> htmlService.manyToHtml("", List.of(MOVIE_1, MOVIE_2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Header is null or empty");
    }

    @Test
    @DisplayName("when items list is null")
    void test3() {
        assertThatThrownBy(() -> htmlService.manyToHtml("Movies", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Items list is null");
    }

    @Test
    @DisplayName("when list is converted to html correctly")
    void test4() {
        var expectedHtml = "<div class=\"box\"><h2 class=\"box-heading\">Movies</h2><div class=\"box-content\"><ol><li>Movie(title=SPIDER MAN NO WAY HOME, genre=ACTION, director=JON WATTS, releaseDate=2021-12-17, cast=[TOM HOLLAND, ZENDAYA, BENEDICT CUMBERBATCH], duration=148, rating=8.5)</li><li>Movie(title=AFTER, genre=ROMANCE, director=JENNY GAGE, releaseDate=2019-04-12, cast=[JOSEPHINE LANGFORD, HERO FIENNES TIFFIN], duration=105, rating=5.4)</li></ol></div></div>";

        assertThat(htmlService.manyToHtml("Movies", List.of(MOVIE_1, MOVIE_2)))
                .isEqualTo(expectedHtml);
    }
}
