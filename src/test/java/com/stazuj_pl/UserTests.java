package com.stazuj_pl;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.stazuj_pl.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    void createUser() {
        User testowy = new User();
        testowy.setUser_id(-5);
        testowy.setMail("TESTOWY-5");
        testowy.setLogin("TESTOWYL-5");
        testowy.setHash_password("TESTOWYH-5");
        testowy.setName("a-5");
        testowy.setSurname("b-5");

        userController.createUser(testowy);

        EntityObj result = userController.getUserById(-5);
        User user = (User) result;
        assert user.getLogin().equals("TESTOWYL-5");
        assert user.getMail().equals("TESTOWY-5");

        userController.deleteUser(-5);
    }

    @Test
    void deleteUser() {
        User testowy = new User();
        testowy.setUser_id(-5);
        testowy.setMail("TESTOWY-5");
        testowy.setLogin("TESTOWYL-5");
        testowy.setHash_password("TESTOWYH-5");
        testowy.setName("a-5");
        testowy.setSurname("b-5");

        userController.createUser(testowy);
        assert userController.deleteUser(-5).equals(ResponseEntity.status(HttpStatus.OK));
    }

    @Test
    void deleteUserDoesntExist() {
        User testowy = new User();
        testowy.setUser_id(-5);
        testowy.setMail("TESTOWY-5");
        testowy.setLogin("TESTOWYL-5");
        testowy.setHash_password("TESTOWYH-5");
        testowy.setName("a-5");
        testowy.setSurname("b-5");

        userController.createUser(testowy);
        assert userController.deleteUser(-55).equals(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE));
        userController.deleteUser(-5);
    }
    @Test
    void createUserLoginTaken() {
        User testowy1 = new User();
        testowy1.setUser_id(-5);
        testowy1.setMail("TESTOWY-5");
        testowy1.setLogin("TESTOWYL-5");
        testowy1.setHash_password("TESTOWYH-5");
        testowy1.setName("a-5");
        testowy1.setSurname("b-5");

        userController.createUser(testowy1);

        User testowy2 = new User();
        testowy2.setUser_id(-50);
        testowy2.setMail("TEST");
        testowy2.setLogin("TESTOWYL-5");
        testowy2.setHash_password("TWYH-5");
        testowy2.setName("a-g5");
        testowy2.setSurname("bddd-5");

        assert userController.createUser(testowy2).equals(ResponseEntity.status(HttpStatus.BAD_REQUEST));
        userController.deleteUser(-5);
    }

    @Test
    void createUserMailTaken() {
        User testowy1 = new User();
        testowy1.setUser_id(-5);
        testowy1.setMail("TEST");
        testowy1.setLogin("TESTOWYL-5");
        testowy1.setHash_password("TESTOWYH-5");
        testowy1.setName("a-5");
        testowy1.setSurname("b-5");

        userController.createUser(testowy1);

        User testowy2 = new User();
        testowy2.setUser_id(-5);
        testowy2.setMail("TEST");
        testowy2.setLogin("T-5");
        testowy2.setHash_password("TWYH-5");
        testowy2.setName("a-g5");
        testowy2.setSurname("bddd-5");

        assert userController.createUser(testowy2).equals(ResponseEntity.status(HttpStatus.BAD_REQUEST));
        userController.deleteUser(-5);
    }

    @Test
    void getUserDataById() {
        User testowy1 = new User();
        testowy1.setUser_id(-5);
        testowy1.setMail("TEST");
        testowy1.setLogin("TESTOWYL-5");
        testowy1.setHash_password("TESTOWYH-5");
        testowy1.setName("a-5");
        testowy1.setSurname("b-5");

        userController.createUser(testowy1);

        EntityObj result = userController.getUserById(-5);
        User user = (User) result;
        assert user.getName().equals("a-5");

        userController.deleteUser(-5);
    }

    @Test
    void getUserDataByIdDoesntExist() {
        User testowy1 = new User();
        testowy1.setUser_id(-5);
        testowy1.setMail("TEST");
        testowy1.setLogin("TESTOWYL-5");
        testowy1.setHash_password("TESTOWYH-5");
        testowy1.setName("a-5");
        testowy1.setSurname("b-5");

        userController.createUser(testowy1);

        EntityObj result = userController.getUserById(-50000);
        assert result == null;

        userController.deleteUser(-5);
    }
    @Test
    void editUser() {
        User testowy = new User();
        testowy.setUser_id(-5);
        testowy.setMail("TESTOWY-5");
        testowy.setLogin("TESTOWYL-5");
        testowy.setHash_password("TESTOWYH-5");
        testowy.setName("a-5");
        testowy.setSurname("b-5");

        userController.createUser(testowy);
        assert testowy.getName().equals("a-5");

        Map<String, Object> data = new HashMap<>();
        data.put("user_id", -5);
        data.put("name", "aja");
        userController.editUser(data);

        EntityObj result = userController.getUserById(-5);
        User user = (User) result;
        assert user.getUser_id() == -5;
        assert user.getName().equals("aja");
    }
}

