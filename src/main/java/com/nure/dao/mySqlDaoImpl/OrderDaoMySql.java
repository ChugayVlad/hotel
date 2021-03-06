package com.nure.dao.mySqlDaoImpl;

import com.nure.dao.OrderDao;
import com.nure.entity.Order;
import com.nure.entity.RoomType;
import com.nure.entity.User;
import com.nure.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDaoMySql implements OrderDao {
    private static final Logger LOG = Logger.getLogger(OrderDaoMySql.class);
    private static final String SQL_SELECT_ALL = "SELECT  * FROM orders LEFT JOIN users u on orders.user_id = u.id WHERE room_id IS NULL LIMIT ? OFFSET ?";
    private static final String SQL_INSERT_ORDER = "INSERT INTO orders (id, places, type, date_in, date_out, user_id) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ROOM = "UPDATE orders SET room_id=?, sum=? WHERE id=?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM orders LEFT JOIN users u on orders.user_id = u.id WHERE orders.id=?";
    private static final String SQL_SELECT_ALL_BY_USER = "SELECT * FROM orders LEFT JOIN users u on orders.user_id = u.id WHERE user_id=?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM orders WHERE id=?";
    private static final String SQL_ORDERS_COUNT = "SELECT COUNT(*) FROM orders";

    private Connection con;

    public OrderDaoMySql(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(Order order) throws DAOException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT_ORDER);
            int k = 0;
            stmt.setInt(++k, order.getPlaces());
            stmt.setString(++k, order.getType().name());
            stmt.setDate(++k, order.getDateIn(), Calendar.getInstance());
            stmt.setDate(++k, order.getDateOut(), Calendar.getInstance());
            stmt.setLong(++k, order.getUser().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Cannot insert order", e);
            throw new DAOException("Cannot insert order", e);
        } finally {
            closeStatement(stmt);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SQL_DELETE_BY_ID);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Cannot delete order", e);
            throw new DAOException("Cannot delete order", e);
        } finally {
            closeStatement(pstmt);
        }
    }

    @Override
    public void update(Order order) throws DAOException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SQL_UPDATE_ROOM);
            pstmt.setLong(1, order.getRoomId());
            pstmt.setDouble(2, order.getSum());
            pstmt.setLong(3, order.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Cannot update order", e);
            throw new DAOException("Cannot update order", e);
        } finally {
            closeStatement(pstmt);
        }
    }

    @Override
    public Order get(Long id) throws DAOException {
        Order order = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_GET_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                order = extractOrder(rs);
            }
        } catch (SQLException e) {
            LOG.error("Cannot get order by id", e);
            throw new DAOException("Cannot get order by id", e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        return order;
    }

    @Override
    public List<Order> getAll(int page, int pageSize) throws DAOException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_SELECT_ALL);
            pstmt.setInt(1, pageSize);
            pstmt.setInt(2, pageSize * (page - 1));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get all orders", e);
            throw new DAOException("Cannot get all orders", e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        return orders;
    }

    @Override
    public List<Order> getAll() throws DAOException {
        return null;
    }

    @Override
    public List<Order> getAllByUser(Long id) throws DAOException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_SELECT_ALL_BY_USER);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                orders.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get all orders by user", e);
            throw new DAOException("Cannot get all orders by user", e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        return orders;
    }

    @Override
    public int getOrdersCount() throws DAOException {
        int ordersNumber = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_ORDERS_COUNT);
            if (rs.next()){
                ordersNumber = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("Cannot get orders number", e);
            throw new DAOException("Cannot get orders number", e);
        }
        return ordersNumber;
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));

        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        order.setUser(user);
        order.setRoomId(rs.getLong("room_id"));


        order.setDateIn(rs.getDate("date_in"));
        order.setDateOut(rs.getDate("date_out"));
        order.setPlaces(rs.getInt("places"));
        order.setSum(rs.getDouble("sum"));
        order.setType(RoomType.valueOf(rs.getString("type")));
        return order;
    }
}
