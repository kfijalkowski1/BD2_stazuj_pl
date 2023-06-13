package com.stazuj_pl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.stazuj_pl.user.*;

import java.util.List;

@SpringBootTest
class UserTests {

    @Test
    void getAllUsers() {
        UserHandler UHandle = new UserHandler();
        List<User> result = UHandle.getAllUsers();
        assert result.size() != 0;
    }

}

