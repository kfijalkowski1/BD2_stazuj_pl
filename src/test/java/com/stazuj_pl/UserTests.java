package com.stazuj_pl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.stazuj_pl.user.*;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UserTests {
    @Autowired
    UserController userController;
    @Test
    void getAllUsersTest() {
        List<EntityObj> result = userController.getAllUsers();
        assert result.size() != 0;
    }

    @Test
    void getUserDataById() {
        User testowy = new User();
        testowy.setUser_id(1);
        EntityObj result = userController.getUserById(testowy);
        User user = (User) result;
        assert user.getName().equals("jan");
    }

    @Test
    void createAndDeleteUser() {
        User testowy = new User();
        testowy.setUser_id(-5);
        testowy.setMail("TESTOWY-5");
        testowy.setLogin("TESTOWYL-5");
        testowy.setHash_password("TESTOWYH-5");
        testowy.setName("a-5");
        testowy.setSurname("b-5");

        userController.createUser(testowy);

        EntityObj result = userController.getUserById(testowy);
        User user = (User) result;
        assert user.getName().equals("a-5");
        assert user.getMail().equals("TESTOWY-5");

        Map<String, Object> data = new HashMap<>();
        data.put("user_id", -5);
        data.put("name", "aja");
        userController.editUser(data);

        EntityObj result2 = userController.getUserById(testowy);
        User user2 = (User) result2;
        assert user2.getName().equals("aja");
        assert user2.getMail().equals("TESTOWY-5");

        userController.deleteUser(-5);

        assert userController.getUserById(testowy) == null;

    }

}

