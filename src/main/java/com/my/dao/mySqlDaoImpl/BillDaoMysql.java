package com.my.dao.mySqlDaoImpl;

import com.my.dao.BillDao;
import com.my.entity.Bill;
import com.my.entity.BillStatus;
import com.my.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BillDaoMysql implements BillDao {
    private static final Logger LOG = Logger.getLogger(BillDaoMysql.class);
    private static final String SQL_INSERT_BILL = "INSERT INTO bills (id, sum, user_id, room_id, date_in, date_out, status) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_BY_USER = "SELECT * FROM bills WHERE user_id=?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM bills WHERE id=?";
    private static final String SQL_UPDATE_STATUS = "UPDATE bills SET status = ? WHERE id=?";
    private static final String SQL_SELECT_ALL_BY_DATE = "select * from bills a where a.room_id=? and a.room_id in (select b.room_id from bills b where ? BETWEEN b.date_in AND b.date_out OR ? BETWEEN b.date_in AND b.date_out)";
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
            stmt.setDate(++k, bill.getDateIn(), Calendar.getInstance());
            stmt.setDate(++k, bill.getDateOut(), Calendar.getInstance());
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
    public Bill get(Long id) throws DAOException {
        Bill bill = new Bill();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                bill = extractBill(rs);
            }
        } catch (SQLException e) {
            LOG.error("Cannot get bill by id", e);
            throw new DAOException("Cannot get bill by id", e);
        }
        return bill;
    }

    @Override
    public void update(Bill bill) throws DAOException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_UPDATE_STATUS);
            stmt.setString(1, String.valueOf(bill.getStatus()));
            stmt.setString(2, String.valueOf(bill.getId()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Cannot update bill status", e);
            throw new DAOException("Cannot update bill status", e);
        } finally {
            closeStatement(stmt);
        }
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

    @Override
    public List<Bill> getAllByDate(Long roomId, Date dateIn, Date dateOut) throws DAOException {
        List<Bill> bills = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_SELECT_ALL_BY_DATE);
            pstmt.setLong(1, roomId);
            pstmt.setDate(2, dateIn);
            pstmt.setDate(3, dateOut);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bills.add(extractBill(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get bills by date", e);
            throw new DAOException("Cannot get bills by date", e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        return bills;
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
        bill.setDateIn(rs.getDate("date_in"));
        bill.setDateOut(rs.getDate("date_out"));
        bill.setStatus(BillStatus.valueOf(rs.getString("status")));
        return bill;
    }
}
