package com.app.service.impl;

import com.app.application.service.HtmlService;
import com.app.application.service.impl.HtmlServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.app.MoviesTestData.MOVIE_1;
import static com.app.MoviesTestData.MOVIE_2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class HtmlServiceImplPairsToHtmlTest {
    private static final HtmlService htmlService = new HtmlServiceImpl();

    @Test
    @DisplayName("when header is null")
    void test1() {
        assertThatThrownBy(() -> htmlService.pairsToHtml(null, Map.of("P1", List.of(MOVIE_1))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Header is null or empty");
    }

    @Test
    @DisplayName("when header is empty")
    void test2() {
        assertThatThrownBy(() -> htmlService.pairsToHtml("", Map.of("P1", List.of(MOVIE_1))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Header is null or empty");
    }

    @Test
    @DisplayName("when map is null")
    void test3() {
        assertThatThrownBy(() -> htmlService.pairsToHtml("Movies map", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Map is null");
    }

    @Test
    @DisplayName("when map is converted to html correctly")
    void test4() {
        var beginning = "<div class=\"box\">";
        var header = "<h2 class=\"box-heading\">Movies map</h2>";
        var firstItem = "<li>P1, [Movie(title=SPIDER MAN NO WAY HOME, genre=ACTION, director=JON WATTS, releaseDate=2021-12-17, cast=[TOM HOLLAND, ZENDAYA, BENEDICT CUMBERBATCH], duration=148, rating=8.5)]</li>";
        var secondItem = "<li>P2, [Movie(title=SPIDER MAN NO WAY HOME, genre=ACTION, director=JON WATTS, releaseDate=2021-12-17, cast=[TOM HOLLAND, ZENDAYA, BENEDICT CUMBERBATCH], duration=148, rating=8.5), Movie(title=AFTER, genre=ROMANCE, director=JENNY GAGE, releaseDate=2019-04-12, cast=[JOSEPHINE LANGFORD, HERO FIENNES TIFFIN], duration=105, rating=5.4)]</li>";
        var ending = "</div>";

        assertThat(htmlService.pairsToHtml("Movies map", Map.of(
                "P1", List.of(MOVIE_1),
                "P2", List.of(MOVIE_1, MOVIE_2)
        )))
                .startsWith(beginning)
                .contains(header)
                .contains(firstItem)
                .contains(secondItem)
                .endsWith(ending);
    }
}
