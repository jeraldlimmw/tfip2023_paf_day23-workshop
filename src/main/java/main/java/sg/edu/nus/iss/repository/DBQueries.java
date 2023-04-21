package main.java.sg.edu.nus.iss.repository;

public class DBQueries {

    // sum total_price, total discout, discounted_price, and cost price for each order
    public static final String ORDER_DETAILS_WITH_DISCOUNT = """
            select o.id as order_id, DATE_FORMAT(o.order_date, \"%d/%m/%Y\") as order_date, o.customer_id,
            sum(od.quantity * od.unit_price) as total_price,
            sum(od.quantity * od.unit_price * od.discount) as discount,
            sum(od.quantity * od.unit_price) - sum(od.quantity * od.unit_price * od.discount) as discounted_price,
            sum(od.quantity * p.standard_cost) as cost_price
            from orders o
            left join order_details od on o.id = od.order_id
            left join products p on od.product_id = p.id
            where o.id = ?
            """;
}
