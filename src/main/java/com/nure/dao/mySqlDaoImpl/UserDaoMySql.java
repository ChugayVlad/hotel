package com.nure.dao.mySqlDaoImpl;


import com.nure.dao.UserDao;
import com.nure.entity.User;
import com.nure.exception.DAOException;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMySql implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoMySql.class);
    private static final String SQL_UPDATE_USER = "UPDATE users SET email = ?, first_name = ?, last_name = ? WHERE id=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM users";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String SQL_INSERT_USER = "INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (DEFAULT, ?, ?, ?, ?, ?)";

    private Connection con;

    public UserDaoMySql(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(User user) throws DAOException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT_USER);
            int k = 0;
            stmt.setString(++k, user.getFirstName());
            stmt.setString(++k, user.getLastName());
            stmt.setString(++k, user.getEmail());
            stmt.setString(++k, user.getPassword());
            stmt.setInt(++k, user.getRoleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Cannot insert user", e);
            throw new DAOException("Cannot insert user", e);
        } finally {
            closeStatement(stmt);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SQL_UPDATE_USER);
            int k = 0;
            pstmt.setString(++k, user.getEmail());
            pstmt.setString(++k, user.getFirstName());
            pstmt.setString(++k, user.getLastName());
            pstmt.setLong(++k, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Cannot update user", e);
            throw new DAOException("Cannot update user", e);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                users.add(extractUser(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get all users", e);
            throw new DAOException("Cannot get all users", e);
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) throws DAOException{
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            LOG.error("Cannot get user by email", e);
            throw new DAOException("Cannot get user by email", e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
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
