package com.mkalaimalai.repository;

import com.mkalaimalai.domain.Address;
import com.mkalaimalai.domain.AddressType;
import com.mkalaimalai.domain.User;
import org.assertj.core.api.Java6Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;




/**
 * Created by kalaimam on 9/19/17.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnUser() {
        // given
        User user = new User();
        user.setEmail("Sincere@april.biz");
        user.setUserName("Bret");
        user.setFirstName("Leanne");
        user.setLastName("Graham");
        Address address = new Address();
        address.setLine1("Kulas Light");
        address.setLine2("Apt. 556");
        address.setCity("Gwenborough");
        address.setCountry("US");
        address.setType(AddressType.HOME);
        user.addAddress(address);

        entityManager.persist(user);
        entityManager.flush();

        // when
        User userFound = userRepository.findByUserName("Bret");

        // then
        logger.debug("user found {}",userFound);

        Java6Assertions.assertThat(userFound.getUserName()).isEqualTo(user.getUserName());
    }

}
