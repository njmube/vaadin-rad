package demo.data.jpa;

import demo.domain.Theater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheaterRepositoryIT {
    @Autowired
    TheaterRepository repository;

    @Test
    public void shouldReadTheatreFromDatabase_WhenDatabaseHasTheatres() {
        Theater theater = repository.findOne(1L);
        assertThat(theater.getCity(), equalTo("Shanghai"));
    }

}