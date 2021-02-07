package com.my.dao.mySqlDaoImpl;

import com.my.dao.BillDao;
import com.my.dao.DatabaseAbstractDao;
import com.my.entity.Bill;
import com.my.entity.BillStatus;
import com.my.exception.DBException;
import com.my.exception.Messages;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BillDaoMysql extends DatabaseAbstractDao implements BillDao {
    private static final Logger LOG = Logger.getLogger(BillDaoMysql.class);
    private static final String SQL_INSERT_BILL = "INSERT INTO bills (id, sum, user_id, room_id, status) VALUES (DEFAULT, ?, ?, ?, ?)";

    public BillDaoMysql(DataSource ds) {
        super(ds);
    }

    @Override
    public void insert(Bill bill) throws DBException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();

            con.setAutoCommit(false);

            stmt = con.prepareStatement(SQL_INSERT_BILL);
            int k = 0;
            stmt.setDouble(++k, bill.getSum());
            stmt.setLong(++k, bill.getUserId());
            stmt.setLong(++k, bill.getRoomId());
            stmt.setString(++k, String.valueOf(bill.getStatus()));

            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_CREATE_BILL);
            throw new DBException(Messages.ERR_CANNOT_CREATE_BILL, e);
        } finally {
            close(stmt);
            close(con);
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Bill get(long id) throws DBException {
        return null;
    }

    @Override
    public List<Bill> listAll() throws DBException {
        return null;
    }
}
