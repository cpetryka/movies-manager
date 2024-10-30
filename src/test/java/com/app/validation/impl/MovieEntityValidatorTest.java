package com.app.validation.impl;

import com.app.config.AppTestConfig;
import com.app.infrastructure.persistence.entity.ActorEntity;
import com.app.infrastructure.persistence.entity.MovieEntity;
import com.app.application.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:application-test.properties")
class MovieEntityValidatorTest {
    @Autowired
    Validator<MovieEntity> validator;

    static Stream<Arguments> validationData() {
        return Stream.of(
                Arguments.of(
                        new MovieEntity(
                                "SPIDER MAN NO WAY HOME!",
                                "ACTION",
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        new ActorEntity("Tom Holland", "male", LocalDate.of(1996, 6, 1), null, "Thomas \"Tom\" Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible."),
                                        new ActorEntity("Zendaya", "female", LocalDate.of(1996, 9, 1), null, "Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm & Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021)."),
                                        new ActorEntity("Benedict Cumberbatch", "male", LocalDate.of(1976, 7, 19), null, "Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch's breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).")
                                ),
                                148,
                                List.of(8.0, 9.0),
                                "634649"
                        ),
                        Map.of("title", "string does not match regex: SPIDER MAN NO WAY HOME!")
                ),
                Arguments.of(
                        new MovieEntity(
                                "SPIDER MAN NO WAY HOME",
                                "ACTION",
                                "JON WATTS.",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        new ActorEntity("Tom Holland", "male", LocalDate.of(1996, 6, 1), null, "Thomas \"Tom\" Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible."),
                                        new ActorEntity("Zendaya", "female", LocalDate.of(1996, 9, 1), null, "Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm & Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021)."),
                                        new ActorEntity("Benedict Cumberbatch", "male", LocalDate.of(1976, 7, 19), null, "Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch's breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).")
                                ),
                                148,
                                List.of(8.0, 9.0),
                                "634649"
                        ),
                        Map.of("director", "string does not match regex: JON WATTS.")
                ),
                Arguments.of(
                        new MovieEntity(
                                "SPIDER MAN NO WAY HOME",
                                "ACTION",
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        new ActorEntity("Tom Holland!", "male", LocalDate.of(1996, 6, 1), null, "Thomas \"Tom\" Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible."),
                                        new ActorEntity("Zendaya...", "female", LocalDate.of(1996, 9, 1), null, "Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm & Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021)."),
                                        new ActorEntity("Benedict Cumberbatch", "male", LocalDate.of(1976, 7, 19), null, "Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch's breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).")
                                ),
                                148,
                                List.of(8.0, 9.0),
                                "634649"
                        ),
                        Map.of("cast", "not all items match regex: [ActorEntity[name=Tom Holland!, gender=male, birthDate=1996-06-01, deathDate=null, biography=Thomas \"Tom\" Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible.], ActorEntity[name=Zendaya..., gender=female, birthDate=1996-09-01, deathDate=null, biography=Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm & Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021).], ActorEntity[name=Benedict Cumberbatch, gender=male, birthDate=1976-07-19, deathDate=null, biography=Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch's breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).]]")
                ),
                Arguments.of(
                        new MovieEntity(
                                "SPIDER MAN NO WAY HOME",
                                "ACTION",
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        new ActorEntity("Tom Holland", "male", LocalDate.of(1996, 6, 1), null, "Thomas \"Tom\" Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible."),
                                        new ActorEntity("Zendaya", "female", LocalDate.of(1996, 9, 1), null, "Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm & Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021)."),
                                        new ActorEntity("Benedict Cumberbatch", "male", LocalDate.of(1976, 7, 19), null, "Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch's breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).")
                                ),
                                0,
                                List.of(8.0, 9.0),
                                "634649"
                        ),
                        Map.of("duration", "value must be positive: 0")
                ),
                Arguments.of(
                        new MovieEntity(
                                "SPIDER MAN NO WAY HOME",
                                "ACTION",
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        new ActorEntity("Tom Holland", "male", LocalDate.of(1996, 6, 1), null, "Thomas \"Tom\" Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible."),
                                        new ActorEntity("Zendaya", "female", LocalDate.of(1996, 9, 1), null, "Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm & Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021)."),
                                        new ActorEntity("Benedict Cumberbatch", "male", LocalDate.of(1976, 7, 19), null, "Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch's breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).")
                                ),
                                148,
                                List.of(10.0, 11.0),
                                "634649"
                        ),
                        Map.of("rating", "value should be in range [0.0; 10.0]: 10.5")
                ),
                Arguments.of(
                        new MovieEntity(
                                "SPIDER MAN NO WAY HOME!",
                                "ACTION",
                                "JON WATTS",
                                LocalDate.of(2021, 12, 17),
                                List.of(
                                        new ActorEntity("Tom Holland", "male", LocalDate.of(1996, 6, 1), null, "Thomas \"Tom\" Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible."),
                                        new ActorEntity("Zendaya", "female", LocalDate.of(1996, 9, 1), null, "Zendaya Maree Stoermer Coleman (born September 1, 1996) is an American actress and singer. She began her career as a child model and backup dancer. She rose to mainstream prominence for her role as Rocky Blue on the Disney Channel sitcom Shake It Up (2010–2013). In 2013, Zendaya was a contestant on the 16th season of the dance competition series Dancing with the Stars. She produced and starred as the titular spy, K.C. Cooper, in the sitcom K.C. Undercover (2015–2018). Her film roles include the musical drama The Greatest Showman (2017), the superhero film Spider-Man: Homecoming (2017) and its sequels, the computer-animated musical comedy Smallfoot (2018), the romantic drama Malcolm & Marie (2021), the live-action/animation hybrid sports comedy Space Jam: A New Legacy (2021), and the science fiction epic Dune (2021)."),
                                        new ActorEntity("Benedict Cumberbatch", "male", LocalDate.of(1976, 7, 19), null, "Benedict Timothy Carlton Cumberbatch CBE (born 19 July 1976) is an English actor. Known for his work in film, television, and theatre, he has received various accolades throughout his career, including a Laurence Olivier Award, a Golden Globe Award, and a British Academy Television Award. Cumberbatch's breakthrough role came in 2004 when he portrayed Stephen Hawking in the television film Hawking. In 2010, he became a household name for playing Sherlock Holmes in the British television series Sherlock, which earned him the British Academy Television Award for Best Actor. In 2011, Cumberbatch made his feature film debut in the historical drama War Horse. He rose to international prominence for his performance as Julian Assange in the biographical film The Fifth Estate (2013) and as Khan Noonien Singh in the science fiction film Star Trek Into Darkness (2013).")
                                ),
                                -10,
                                List.of(-5.5),
                                "634649"
                        ),
                        Map.of(
                                "title", "string does not match regex: SPIDER MAN NO WAY HOME!",
                                "duration", "value must be positive: -10",
                                "rating", "value should be in range [0.0; 10.0]: -5.5"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("validationData")
    @DisplayName("when validation result is not correct")
    void test1(MovieEntity movieData, Map<String, String> expectedErrors) {
        assertThat(validator.validate(movieData))
                .isEqualTo(expectedErrors);
    }
}
