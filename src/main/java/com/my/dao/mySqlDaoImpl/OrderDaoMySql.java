package com.my.dao.mySqlDaoImpl;

import com.my.dao.DatabaseAbstractDao;
import com.my.dao.OrderDao;
import com.my.entity.Order;
import com.my.exception.DBException;
import com.my.exception.Messages;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoMySql extends DatabaseAbstractDao implements OrderDao {
    private static final Logger LOG = Logger.getLogger(OrderDaoMySql.class);
    private static final String SQL_INSERT_ORDER = "INSERT INTO orders (id, places, type_id, date_in, date_out, user_id) VALUES (DEFAULT, ?, ?, ?, ?, ?)";

    public OrderDaoMySql(DataSource ds) {
        super(ds);
    }

    @Override
    public void insert(Order order) throws DBException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();

            con.setAutoCommit(false);

            stmt = con.prepareStatement(SQL_INSERT_ORDER);
            int k = 0;
            stmt.setInt(++k, order.getPlaces());
            stmt.setLong(++k, order.getType().getId());
            stmt.setObject(++k, order.getDateIn());
            stmt.setObject(++k, order.getDateOut());
            stmt.setLong(++k, order.getUser().getId());


            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_CREATE_ORDER);
            throw new DBException(Messages.ERR_CANNOT_CREATE_ORDER, e);
        } finally {
            close(stmt);
            close(con);
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Order get(long id) {
        return null;
    }

    @Override
    public List<Order> listAll() throws DBException {
        return null;
    }
}
