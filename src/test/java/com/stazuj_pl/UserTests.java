package com.stazuj_pl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.stazuj_pl.user.*;

import java.io.Console;
import java.util.List;

@SpringBootTest
class UserTests {
    @Autowired
    UserController userController;
    @Test
    void getAllUsersTest() {
        List<User> result = userController.getAllUsers();
        assert result.size() != 0;
    }

}

