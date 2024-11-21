package com.app;

import com.app.infrastructure.persistence.entity.ActorEntity;
import com.app.infrastructure.persistence.entity.MovieEntity;
import com.app.infrastructure.persistence.entity.MoviesEntity;
import com.app.domain.movies_management.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MoviesTestData {
    String MOVIES_FILENAME = "movies-test.json";

    MovieEntity MOVIE_1_ENTITY = new MovieEntity(
            "SPIDER MAN NO WAY HOME",
            List.of("ACTION"),
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
    );

    Movie MOVIE_1 = MOVIE_1_ENTITY.toMovie();

    MovieEntity MOVIE_2_ENTITY = new MovieEntity(
            "AFTER",
            List.of("ROMANCE"),
            "JENNY GAGE",
            LocalDate.of(2019, 4, 12),
            List.of(
                    new ActorEntity("Josephine Langford", "female", LocalDate.of(1997, 8, 18), null, "Josephine Langford (born 18 August 1997) is an Australian actress. She is known for her role as Tessa Young in the After film series, and as Nat in the film Wish Upon (2017)."),
                    new ActorEntity("Hero Fiennes Tiffin", "male", LocalDate.of(1997, 11, 6), null, "Hero Fiennes Tiffin (born 6 November 1997) is an English actor and model born in London, England. He is best known for his role as the 11-year-old Tom Riddle, the young version of Lord Voldemort (played in the films by his uncle, Ralph Fiennes) in Harry Potter and the Half Blood Prince (2009). He portrayed Hardin Scott in the movie After (2019).")
            ),
            105,
            List.of(4.0, 5.0, 6.0, 7.0, 5.0),
            "537915"
    );

    Movie MOVIE_2 = MOVIE_2_ENTITY.toMovie();

    MovieEntity MOVIE_3_ENTITY = new MovieEntity(
            "TRANSFORMERS RISE OF THE BEASTS",
            List.of("ACTION"),
            "STEVEN CAPLE JR",
            LocalDate.of(2023, 6, 6),
            List.of(
                    new ActorEntity("Anthony Ramos", "male", LocalDate.of(1991, 11, 1), null, "Anthony Ramos (born 1 November 1991) is an American actor and singer. He is known for his roles in the musicals In the Heights and Hamilton, as well as starring in films like A Star is Born (2018) and In the Heights (2021)."),
                    new ActorEntity("Dominique Fishback", "female", LocalDate.of(1991, 3, 22), null, "Dominique Fishback (born 22 March 1991) is an American actress and playwright. She is best known for her roles in HBO's The Deuce, as well as her role as Deborah Johnson in the film Judas and the Black Messiah (2021) and in the movie Transformers: Rise of the Beasts (2023)."),
                    new ActorEntity("Luna Lauren Velez", "female", LocalDate.of(1964, 11, 2), null, "Luna Lauren Velez (born 2 November 1964) is an American actress. She is well known for her roles as María LaGuerta on the television series Dexter and as Elena in the film Spider-Man: Into the Spider-Verse (2018). She also starred in New York Undercover and Oz.")
            ),
            128,
            List.of(6.0, 7.0, 8.0, 6.0, 5.0),
            "667538"
    );

    Movie MOVIE_3 = MOVIE_3_ENTITY.toMovie();

    MovieEntity MOVIE_4_ENTITY = new MovieEntity(
            "UNCHARTED",
            List.of("ACTION"),
            "RUBEN FLEISCHER",
            LocalDate.of(2022, 2, 10),
            List.of(
                    new ActorEntity("Tom Holland", "male", LocalDate.of(1996, 6, 1), null, "Thomas \"Tom\" Stanley Holland is an English actor and dancer. He is best known for playing Peter Parker / Spider-Man in the Marvel Cinematic Universe and has appeared as the character in six films: Captain America: Civil War (2016), Spider-Man: Homecoming (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), Spider-Man: Far From Home (2019), and Spider-Man: No Way Home (2021). He is also known for playing the title role in Billy Elliot the Musical at the Victoria Palace Theatre, London, as well as for starring in the 2012 film The Impossible."),
                    new ActorEntity("Mark Wahlberg", "male", LocalDate.of(1971, 6, 5), null, "Mark Wahlberg (born 5 June 1971) is an American actor, producer, and former rapper. Known for his roles in films like The Departed (2006), Transformers: Age of Extinction (2014), and Daddy's Home (2015), he first gained fame as a member of the music group Marky Mark and the Funky Bunch in the 1990s."),
                    new ActorEntity("Sophia Ali", "female", LocalDate.of(1995, 11, 7), null, "Sophia Ali (born 7 November 1995) is an American actress. She is best known for her roles as Dr. Dahlia Qadri on Grey's Anatomy and as Chloe Frazer in the film Uncharted (2022).")
            ),
            116,
            List.of(6.0, 7.0, 8.0, 6.0, 5.0),
            "335787"
    );

    Movie MOVIE_4 = MOVIE_4_ENTITY.toMovie();

    MovieEntity MOVIE_5_ENTITY = new MovieEntity(
            "THE LUCKY ONE",
            List.of("ROMANCE"),
            "SCOTT HICKS",
            LocalDate.of(2012, 4, 20),
            List.of(
                    new ActorEntity("Zac Efron", "male", LocalDate.of(1987, 10, 18), null, "Zac Efron (born 18 October 1987) is an American actor and singer. He rose to fame with his role as Troy Bolton in Disney's High School Musical series and has since starred in films like The Greatest Showman (2017), Baywatch (2017), and Extremely Wicked, Shockingly Evil and Vile (2019)."),
                    new ActorEntity("Taylor Schilling", "female", LocalDate.of(1984, 7, 27), null, "Taylor Schilling (born 27 July 1984) is an American actress, best known for her role as Piper Chapman on the Netflix series Orange Is the New Black. She has also appeared in films such as The Lucky One (2012) and The Prodigy (2019)."),
                    new ActorEntity("Blythe Danner", "female", LocalDate.of(1943, 2, 3), null, "Blythe Danner (born 3 February 1943) is an American actress known for her work in films such as Meet the Parents (2000) and its sequels, as well as for her performances in Will & Grace and the stage production of Butterflies Are Free. She is also the mother of actress Gwyneth Paltrow.")
            ),
            101,
            List.of(6.0, 8.0),
            "77877"
    );

    Movie MOVIE_5 = MOVIE_5_ENTITY.toMovie();

    MoviesEntity MOVIES_DATA = new MoviesEntity(List.of(MOVIE_1_ENTITY, MOVIE_2_ENTITY, MOVIE_3_ENTITY, MOVIE_4_ENTITY, MOVIE_5_ENTITY));

    List<Movie> MOVIES_LIST = List.of(MOVIE_1, MOVIE_2, MOVIE_3, MOVIE_4, MOVIE_5);
}
