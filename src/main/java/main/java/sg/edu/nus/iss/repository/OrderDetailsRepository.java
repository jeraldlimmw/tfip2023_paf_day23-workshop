package main.java.sg.edu.nus.iss.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import main.java.sg.edu.nus.iss.model.OrderDetails;
import static main.java.sg.edu.nus.iss.repository.DBQueries.*;

@Repository
public class OrderDetailsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public OrderDetails getOrderDetailsWithDiscount (Integer orderId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(ORDER_DETAILS_WITH_DISCOUNT, orderId);

        while(resultSet.next()) {
            orderDetailsList.add(OrderDetails.create(resultSet));
        }
        
        if(orderDetailsList.isEmpty()) {
            return null;
        }
        return orderDetailsList.get(0);
    }
}
