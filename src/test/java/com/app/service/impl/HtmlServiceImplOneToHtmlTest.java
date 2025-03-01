package com.app.service.impl;

import com.app.application.service.HtmlService;
import com.app.infrastructure.service.impl.HtmlServiceImpl;
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
        var expectedHtml = "<div class=\"box\"><h2 class=\"box-heading\">Movie</h2><div class=\"box-content\"><p>Movie(title=SPIDER MAN NO WAY HOME, genres=[ACTION], director=JON WATTS, releaseDate=2021-12-17, cast=[Actor(name=Tom Holland, gender=MALE, birthDate=1996-06-01, deathDate=null, biography=Thomas &quot;Tom&quot; Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible.), Actor(name=Zendaya, gender=FEMALE, birthDate=1996-09-01, deathDate=null, biography=Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm &amp; Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021).), Actor(name=Benedict Cumberbatch, gender=MALE, birthDate=1976-07-19, deathDate=null, biography=Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch&#x27;s breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).)], duration=148, rating=8.5)</p></div></div>";

        assertThat(htmlService.oneToHtml("Movie", MOVIE_1))
                .isEqualTo(expectedHtml);
    }
}
