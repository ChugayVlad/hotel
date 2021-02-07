package com.my.dao.mySqlDaoImpl;

import com.my.dao.DatabaseAbstractDao;
import com.my.dao.RoomDao;
import com.my.entity.Room;
import com.my.entity.RoomStatus;
import com.my.entity.RoomType;
import com.my.exception.DBException;
import com.my.exception.Messages;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoMySql extends DatabaseAbstractDao implements RoomDao {
    private static final Logger LOG = Logger.getLogger(RoomDaoMySql.class);
    private static final String SQL_SELECT_ALL = "SELECT * FROM rooms LEFT JOIN room_types ON rooms.type_id = room_types.id";

    public RoomDaoMySql(DataSource ds) {
        super(ds);
    }

    @Override
    public void insert(Room entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Room get(long id) {
        return null;
    }

    @Override
    public List<Room> listAll() throws DBException {
        List<Room> rooms = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();

            con.setAutoCommit(false);

            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()){
                rooms.add(extractRoom(rs));
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMS);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMS, e);
        } finally {
            close(con, stmt, rs);
        }
        return rooms;
    }

    private Room extractRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setPlaces(rs.getInt("places"));
        room.setPrice(rs.getDouble("price"));

        room.setStatus(RoomStatus.valueOf(rs.getString("status")));

        RoomType type = new RoomType();
        type.setId(rs.getLong("type_id"));
        type.setName(rs.getString("room_types.name"));
        room.setType(type);
        return room;
    }
}
