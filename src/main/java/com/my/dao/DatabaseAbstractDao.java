package com.my.dao;

import com.my.exception.Messages;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseAbstractDao {

    private static final Logger LOG = Logger.getLogger(DatabaseAbstractDao.class);

    protected DataSource ds;

    public DatabaseAbstractDao(DataSource ds){
        this.ds = ds;
    }

    protected Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * Closes a connection.
     *
     * @param con
     *            Connection to be closed.
     */
    protected void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    protected void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    protected void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    protected void rollback(Connection con){
        if (con != null){
            try {
                con.rollback();
            } catch (SQLException e) {
                LOG.error("Cannot rollback transaction", e);
            }
        }
    }
}
