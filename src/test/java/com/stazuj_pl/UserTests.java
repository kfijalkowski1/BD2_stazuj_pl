package com.stazuj_pl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.stazuj_pl.User.*;
import org.springframework.http.HttpStatus;

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
        boolean result1 = user.getLogin().equals("TESTOWYL-5");
        boolean result2 = user.getMail().equals("TESTOWY-5");

        userController.deleteUser(-5);

        assert result1 && result2;
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
        boolean result = userController.deleteUser(-5).getStatusCode().equals(HttpStatus.OK);
        if(!result) {
            userController.deleteUser(-5);
            assert false;
        }
        else assert true;
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
        boolean result = userController.deleteUser(-55).getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE);
        userController.deleteUser(-5);

        assert result;
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

        boolean result = userController.createUser(testowy2).getStatusCode().equals(HttpStatus.BAD_REQUEST);
        userController.deleteUser(-5);

        assert result;
    }

    @Test
    void createUserMailTaken() {
        User testowy1 = new User();
        testowy1.setUser_id(-5);
        testowy1.setMail("TES");
        testowy1.setLogin("TESTOWYL-5");
        testowy1.setHash_password("TESTOWYH-5");
        testowy1.setName("a-5");
        testowy1.setSurname("b-5");

        userController.createUser(testowy1);

        User testowy2 = new User();
        testowy2.setUser_id(-50);
        testowy2.setMail("TES");
        testowy2.setLogin("T-5");
        testowy2.setHash_password("TWYH-5");
        testowy2.setName("a-g5");
        testowy2.setSurname("bddd-5");

        boolean result = userController.createUser(testowy2).getStatusCode().equals(HttpStatus.BAD_REQUEST);
        userController.deleteUser(-5);

        assert result;
    }

    @Test
    void getUserDataById() {
        User testowy1 = new User();
        testowy1.setUser_id(-5);
        testowy1.setMail("TES");
        testowy1.setLogin("TESTOWYL-5");
        testowy1.setHash_password("TESTOWYH-5");
        testowy1.setName("a-5");
        testowy1.setSurname("b-5");

        userController.createUser(testowy1);

        EntityObj result = userController.getUserById(-5);
        User user = (User) result;
        boolean getByIdResult = user.getLogin().equals("TESTOWYL-5");

        userController.deleteUser(-5);
        assert getByIdResult;
    }

    @Test
    void getUserDataByIdDoesntExist() {
        User testowy1 = new User();
        testowy1.setUser_id(-5);
        testowy1.setMail("TES");
        testowy1.setLogin("TESTOWYL-5");
        testowy1.setHash_password("TESTOWYH-5");
        testowy1.setName("a-5");
        testowy1.setSurname("b-5");

        userController.createUser(testowy1);

        EntityObj result = userController.getUserById(-50000);
        boolean queryResult = result == null;

        userController.deleteUser(-5);

        assert queryResult;
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
        boolean createdResult = testowy.getName().equals("a-5");

        Map<String, Object> data = new HashMap<>();
        data.put("user_id", -5);
        data.put("name", "aja");
        userController.editUser(data);

        EntityObj result = userController.getUserById(-5);
        User user = (User) result;
        boolean correctId = user.getUser_id() == -5;
        boolean editResult = user.getName().equals("aja");

        userController.deleteUser(-5);

        assert createdResult && correctId && editResult;
    }

    @Test
    void editUserMoreFields() {
        User testowy = new User();
        testowy.setUser_id(-5);
        testowy.setMail("TESTOWY-5");
        testowy.setLogin("TESTOWYL-5");
        testowy.setHash_password("TESTOWYH-5");
        testowy.setName("a-5");
        testowy.setSurname("b-5");

        userController.createUser(testowy);

        Map<String, Object> data = new HashMap<>();
        data.put("user_id", -5);
        data.put("name", "aaa");
        data.put("surname", "bbb");
        data.put("mail", "ccc");
        data.put("photo_path", "ddd");
        userController.editUser(data);

        EntityObj result = userController.getUserById(-5);
        User user = (User) result;
        boolean idResult = user.getUser_id() == -5;
        boolean nameResult = user.getName().equals("aaa");
        boolean surnameResult = user.getSurname().equals("bbb");
        boolean mailResult = user.getMail().equals("ccc");
        boolean photopathResult = user.getPhoto_path().equals("ddd");

        userController.deleteUser(-5);
        assert idResult && nameResult && surnameResult && mailResult && photopathResult;
    }

    @Test
    void editUserDoesntExist() {
        User testowy = new User();
        testowy.setUser_id(-5);
        testowy.setMail("TESTOWY-5");
        testowy.setLogin("TESTOWYL-5");
        testowy.setHash_password("TESTOWYH-5");
        testowy.setName("a-5");
        testowy.setSurname("b-5");

        userController.createUser(testowy);

        Map<String, Object> data = new HashMap<>();
        data.put("user_id", -50);
        data.put("name", "aja");
        boolean result = userController.editUser(data).getStatusCode().equals(HttpStatus.BAD_REQUEST);

        userController.deleteUser(-5);

        assert result;
    }
}

