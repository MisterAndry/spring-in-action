package tacos.data;

import org.springframework.jdbc.core.JdbcTemplate;
import tacos.Order;

public class JdbcOrderRepository implements OrderRepository {

    JdbcTemplate jdbc;

    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Order save(Order order) {
        jdbc.update("insert into Orders");
        return null;
    }
}
