package com.app.service.impl;

import com.app.application.service.HtmlService;
import com.app.infrastructure.service.impl.HtmlServiceImpl;
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
        var firstItem = "<li>P1, [Movie(title=SPIDER MAN NO WAY HOME, genre=ACTION, director=JON WATTS, releaseDate=2021-12-17, cast=[Actor(name=Tom Holland, gender=MALE, birthDate=1996-06-01, deathDate=null, biography=Thomas &quot;Tom&quot; Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible.), Actor(name=Zendaya, gender=FEMALE, birthDate=1996-09-01, deathDate=null, biography=Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm &amp; Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021).), Actor(name=Benedict Cumberbatch, gender=MALE, birthDate=1976-07-19, deathDate=null, biography=Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch&#x27;s breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).)], duration=148, rating=8.5)]</li>";
        var secondItem = "<li>P2, [Movie(title=SPIDER MAN NO WAY HOME, genre=ACTION, director=JON WATTS, releaseDate=2021-12-17, cast=[Actor(name=Tom Holland, gender=MALE, birthDate=1996-06-01, deathDate=null, biography=Thomas &quot;Tom&quot; Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible.), Actor(name=Zendaya, gender=FEMALE, birthDate=1996-09-01, deathDate=null, biography=Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm &amp; Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021).), Actor(name=Benedict Cumberbatch, gender=MALE, birthDate=1976-07-19, deathDate=null, biography=Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch&#x27;s breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).)], duration=148, rating=8.5), Movie(title=AFTER, genre=ROMANCE, director=JENNY GAGE, releaseDate=2019-04-12, cast=[Actor(name=Josephine Langford, gender=FEMALE, birthDate=1997-08-18, deathDate=null, biography=Josephine Langford (born 18 August 1997) is an Australian actress. She is known for her role as Tessa Young in the After film series, and as Nat in the film Wish Upon (2017).), Actor(name=Hero Fiennes Tiffin, gender=MALE, birthDate=1997-11-06, deathDate=null, biography=Hero Fiennes Tiffin (born 6 November 1997) is an English actor and model born in London, England. He is best known for his role as the 11-year-old Tom Riddle, the young version of Lord Voldemort (played in the films by his uncle, Ralph Fiennes) in Harry Potter and the Half Blood Prince (2009). He portrayed Hardin Scott in the movie After (2019).)], duration=105, rating=5.4)]</li>";
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
