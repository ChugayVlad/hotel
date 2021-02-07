package com.my.dao.mySqlDaoImpl;

import com.my.dao.DatabaseAbstractDao;
import com.my.dao.RoomTypeDao;
import com.my.entity.RoomType;
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

public class RoomTypeDaoMySql extends DatabaseAbstractDao implements RoomTypeDao {
    private static final Logger LOG = Logger.getLogger(DatabaseAbstractDao.class);
    private static final String SQL_SELECT_ALL = "SELECT * FROM room_types";
    private static final String SQL_GET_BY_ID = "SELECT * FROM room_types WHERE id=?";

    public RoomTypeDaoMySql(DataSource ds) {
        super(ds);
    }

    @Override
    public void insert(RoomType entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public RoomType get(long id) throws DBException {
        RoomType roomType = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            statement = con.prepareStatement(SQL_GET_BY_ID);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()){
                roomType = extractRoomType(rs);
            }
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_GET_ROOM_TYPE_BY_ID);
            throw new DBException(Messages.ERR_CANNOT_GET_ROOM_TYPE_BY_ID, e);
        }
        return roomType;
    }

    @Override
    public List<RoomType> listAll() throws DBException {
        List<RoomType> types = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();

            con.setAutoCommit(false);

            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()){

                types.add(extractRoomType(rs));
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOM_TYPES);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOM_TYPES, e);
        } finally {
            close(con, stmt, rs);
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
