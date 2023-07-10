package by.logonuk.repository.user;

import by.logonuk.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepositoryInterface repository;

    @BeforeEach
    public void setRepository(){
        repository = new UserRepository();
    }

    @Test
    void createAndFindById_success() {

        // given
        User testUser = new User();

        testUser.setUserName("NNN");
        testUser.setSurname("SSS");
        testUser.setLogin("LLL");
        testUser.setPassword("PPP");

        User user = repository.create(testUser);

        //when
        assertEquals(testUser.getLogin(), user.getLogin());
        assertEquals(Integer.class, user.getId().getClass());

        repository.delete(user.getId());
    }

    @Test
    void update_success() {

        //given
        User testUser = new User();

        testUser.setUserName("NNN");
        testUser.setSurname("SSS");
        testUser.setLogin("LLL");
        testUser.setPassword("PPP");

        User user = repository.create(testUser);

        testUser.setId(user.getId());
        testUser.setLogin("LLL1");
        testUser.setModificationDate(new Timestamp(new Date().getTime()));

        //when
        assertEquals(testUser.getLogin(), repository.update(testUser).getLogin());

        repository.delete(user.getId());
    }

    @Test
    void delete_success() {
        //given
        User testUser = new User();

        testUser.setUserName("NNN");
        testUser.setSurname("SSS");
        testUser.setLogin("LLL");
        testUser.setPassword("PPP");

        User user = repository.create(testUser);

        //when
        assertEquals(user.getId(), repository.delete(user.getId()));
    }
}