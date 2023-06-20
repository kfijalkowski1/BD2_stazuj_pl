package com.stazuj_pl.Statistics;

import com.stazuj_pl.EntityObj;
import com.stazuj_pl.Posts.Posts;
import com.stazuj_pl.Posts.Statistics;
import com.stazuj_pl.User.User;
import com.stazuj_pl.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
public class StatisticsHandler {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserHandler userHandler;

    private BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper<>(Statistics.class);
    public List<Map<String, Object>> getUsers() {

        List<Map<String, Object>> result = new ArrayList<>();
        List<EntityObj> en_users = userHandler.getAll();
        for (EntityObj en_user : en_users) {
            Map<String, Object> curValues = new HashMap<>();
            User user = (User) en_user;
            int userId = user.getUser_id();
            curValues.put("user_id", userId);
            curValues.put("pending transactions", funcCall("TransactionData", userId, "pending"));
            curValues.put("delivered transactions", funcCall("TransactionData", userId, "delivered"));
            curValues.put("rejected transactions", funcCall("TransactionData", userId, "rejected"));
            curValues.put("accepted transactions", funcCall("TransactionData", userId, "accepted"));
            curValues.put("tagged candidates (if student, 0)", funcCall("TaggedCandidates", userId, ""));
            curValues.put("tagged offers (if employee, 0)", funcCall("TaggedOffers", userId, ""));
            curValues.put("sheared posts", funcCall("Posts", userId, ""));
            curValues.put("Messages sent", funcCall("Messages", userId, "sent"));
            curValues.put("Messages received", funcCall("Messages", userId, "received"));
            curValues.put("Ads posted", funcCall("InternshipAds", userId, ""));
            curValues.put("Number of user files", funcCall("Files", userId, ""));
            result.add(curValues);
        }
        return result;
    }
    private int funcCall(String tableName, int specificId, String specifier) {
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.INTEGER));
        CallableStatementCreator stm = new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("select getCount(?, ?, ?) as count");
                cs.setString(1, tableName);
                cs.setInt(2, specificId);
                cs.setString(3, specifier);
                return cs;
            }
        };
        ArrayList<Map<String, Object>> res = (ArrayList) jdbcTemplate.call(stm, parameters).get("#result-set-1");
        return (int) res.get(0).get("count");
    }
//    private Integer funcCall(String tableName, int specificId, String specifier) {
//            String sql = String.format("select getCount(?, ?, ?) as count", tableName, specificId, specifier);
//            List<Statistics> res = jdbcTemplate.query(sql, (BeanPropertyRowMapper) rowMapper, tableName, specificId, specifier);
//            return res.get(0).getCount();
//    }

}
