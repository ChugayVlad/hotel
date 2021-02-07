package com.my.dao.mySqlDaoImpl;


import com.my.dao.DatabaseAbstractDao;
import com.my.dao.UserDao;
import com.my.entity.User;
import com.my.exception.DBException;
import com.my.exception.Messages;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMySql extends DatabaseAbstractDao implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoMySql.class);
    private static final String SQL_SELECT_ALL = "SELECT * FROM users";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String SQL_INSERT_USER = "INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (DEFAULT, ?, ?, ?, ?, ?)";

    public UserDaoMySql(DataSource ds) {
        super(ds);
    }

    @Override
    public void insert(User user) throws DBException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = getConnection();

            con.setAutoCommit(false);

            stmt = con.prepareStatement(SQL_INSERT_USER);
            int k = 0;
            stmt.setString(++k, user.getFirstName());
            stmt.setString(++k, user.getLastName());
            stmt.setString(++k, user.getEmail());
            stmt.setString(++k, user.getPassword());
            stmt.setInt(++k, user.getRoleId());

            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_CREATE_USER);
            throw new DBException(Messages.ERR_CANNOT_CREATE_USER, e);
        } finally {
            close(stmt);
            close(con);
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public List<User> listAll() throws DBException{
        List<User> users = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()){
                users.add(extractUser(rs));
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USERS);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS, e);
        } finally {
            close(con, stmt, rs);
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) throws DBException{
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();

            con.setAutoCommit(false);

            pstmt = con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        user.setRoleId(rs.getInt("role_id"));
        return user;
    }
}
