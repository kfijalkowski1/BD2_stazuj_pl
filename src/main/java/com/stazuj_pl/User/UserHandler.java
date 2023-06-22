package com.stazuj_pl.User;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import com.stazuj_pl.Messages.Messages;
import com.stazuj_pl.Messages.MessagesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    MessagesHandler messagesHandler;


    UserHandler() {
        this.tableName = "Users";
        this.tableMainKey = "user_id";
        this.rowMapper = new BeanPropertyRowMapper<>(User.class);
        this.modifiableKeys = Arrays.asList("name", "surname", "mail", "login", "photo_path", "about_me");
    }

    public int getIdByUniqueField(String uniqueFieldValue) {
        Boolean exists = false;
        List<EntityObj> userObj = getAll();
        for(EntityObj obj : userObj) {
            User user = (User) obj;
            if(user.getLogin().equals(uniqueFieldValue)) {
                exists = true;
                break;
            }
        }
        if(exists) {
            String sql = String.format("select %s from %s where login = ?", tableMainKey, tableName);
            List<User> user = jdbcTemplate.query(sql, (BeanPropertyRowMapper) rowMapper, uniqueFieldValue);
            return user.get(0).getUser_id();
        }
        else {
            return -1;
        }
    }

    public List<Integer> getConversation(Map<String, Integer> data) {
        List<EntityObj> listObj = messagesHandler.getAll();
        List<Integer> listOfMessagesId = new ArrayList<>(List.of());

        List<Integer> listOfParticipants = Arrays.asList(data.get("author_id"), data.get("receiver_id"));
        for(EntityObj obj : listObj) {
            Messages message = (Messages) obj;
            if(listOfParticipants.contains(Integer.parseInt(message.getAuthor_id())) && listOfParticipants.contains(Integer.parseInt(message.getReceiver_id()))) {
                listOfMessagesId.add(message.getMessage_id());
            }
        }

        return listOfMessagesId;
    }


    public int login(Map<String, String> data) {
        int id = getIdByUniqueField(data.get("login"));
        if(id != -1) {
            EntityObj userObj = getById(id);
            User user = (User) userObj;
            return user.getHash_password().equals(data.get("password")) ? id : -1;
        }
        else {
            return -1;
        }
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        User user = (User) e;
        String sql = "INSERT INTO Users (user_id, mail, hash_password, name, surname, login, photo_path, about_me) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        List<EntityObj> listUsers = getAll();
        for(EntityObj obj : listUsers)
        {
            User query_user = (User) obj;
            if(query_user.getMail().equals(user.getMail()) || query_user.getLogin().equals(user.getLogin()) || query_user.getUser_id() == user.getUser_id()) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }
        }
        int changedRows = jdbcTemplate.update(sql, user.getUser_id(), user.getMail(), user.getHash_password(), user.getName(), user.getSurname(), user.getLogin(), user.getPhoto_path(), user.getAbout_me());

        return (changedRows == 1) ? new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
