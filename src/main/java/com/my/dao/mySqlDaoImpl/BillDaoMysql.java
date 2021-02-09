package com.my.dao.mySqlDaoImpl;

import com.my.dao.BillDao;
import com.my.entity.Bill;
import com.my.entity.BillStatus;
import com.my.entity.Room;
import com.my.entity.RoomStatus;
import com.my.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillDaoMysql implements BillDao {
    private static final Logger LOG = Logger.getLogger(BillDaoMysql.class);
    private static final String SQL_INSERT_BILL = "INSERT INTO bills (id, sum, user_id, room_id, status) VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_BY_USER = "SELECT * FROM bills WHERE user_id=?";
    private Connection con;

    public BillDaoMysql(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(Bill bill) throws DAOException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT_BILL);
            int k = 0;
            stmt.setDouble(++k, bill.getSum());
            stmt.setLong(++k, bill.getUserId());
            stmt.setLong(++k, bill.getRoomId());
            stmt.setString(++k, String.valueOf(bill.getStatus()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Cannot insert bill", e);
            throw new DAOException("Cannot insert bill", e);
        } finally {
            closeStatement(stmt);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Bill get(Long id) {
        return null;
    }

    @Override
    public void update(Bill entity) {

    }

    @Override
    public List<Bill> getAll() throws DAOException {
/*        List<Bill> bills = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                bills.add(extractBill(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get bills", e);
            throw new DAOException("Cannot get bills", e);
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
        }
        return bills;*/
        return null;
    }


    public List<Bill> getAllByUserId(Long id) throws DAOException {
        List<Bill> bills = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_SELECT_ALL_BY_USER);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bills.add(extractBill(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get bills by user id", e);
            throw new DAOException("Cannot get bills by user id", e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        return bills;
    }

    private Bill extractBill(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getLong("id"));
        bill.setSum(rs.getDouble("sum"));
        bill.setUserId(rs.getLong("user_id"));
        bill.setRoomId(rs.getLong("room_id"));
        bill.setStatus(BillStatus.valueOf(rs.getString("status")));
        return bill;
    }
}
