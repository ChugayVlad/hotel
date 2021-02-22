package com.nure.dao.mySqlDaoImpl;

import com.nure.dao.RoomTypeDao;
import com.nure.entity.RoomType;
import com.nure.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDaoMySql implements RoomTypeDao {
    private static final Logger LOG = Logger.getLogger(RoomTypeDaoMySql.class);
    private Connection con;
    private static final String SQL_SELECT_ALL = "SELECT * FROM room_types";
    private static final String SQL_GET_BY_ID = "SELECT * FROM room_types WHERE id=?";

    public RoomTypeDaoMySql(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(RoomType entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(RoomType entity) throws DAOException {

    }

    @Override
    public RoomType get(Long id) throws DAOException {
        RoomType roomType = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = con.prepareStatement(SQL_GET_BY_ID);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()){
                roomType = extractRoomType(rs);
            }
        } catch (SQLException e) {
            LOG.error("Cannot get room type", e);
            throw new DAOException("Cannot get room type", e);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return roomType;
    }

    @Override
    public List<RoomType> getAll() throws DAOException {
        List<RoomType> types = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()){
                types.add(extractRoomType(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get all room types", e);
            throw new DAOException("Cannot get all room types", e);
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
        }
        return types;
    }

    private RoomType extractRoomType(ResultSet rs) throws SQLException {
        RoomType roomType = new RoomType();
        roomType.setId(rs.getLong("id"));
        roomType.setName(rs.getString("name"));
        return roomType;
    }
}
