package com.my.dao.mySqlDaoImpl;


import com.my.dao.RoomDao;
import com.my.entity.Room;
import com.my.entity.RoomStatus;
import com.my.entity.RoomType;
import com.my.exception.DAOException;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoMySql implements RoomDao {
    private static final Logger LOG = Logger.getLogger(RoomDaoMySql.class);

    private static final String SQL_SELECT_ALL = "SELECT * FROM rooms LEFT JOIN room_types ON rooms.type_id = room_types.id ORDER BY %s LIMIT ? OFFSET ?";
    private static final String SQL_UPDATE_STATUS = "UPDATE rooms SET status = ? WHERE id=?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM rooms LEFT JOIN room_types ON rooms.type_id = room_types.id WHERE rooms.id=?";
    private static final String SQL_SELECT_ALL_BY_PARAMETERS = "select * from rooms r LEFT JOIN room_types on r.type_id = room_types.id where r.type_id=? AND r.places=? AND r.id not in (select b.room_id from bills b where ? BETWEEN b.date_in AND b.date_out OR ? BETWEEN b.date_in AND b.date_out)";
    private static final String SQL_ROOMS_COUNT = "SELECT COUNT(*) FROM rooms";


    private Connection con;

    public RoomDaoMySql(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(Room entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Room entity) throws DAOException {

    }

    @Override
    public Room get(Long id) throws DAOException {
        Room room = new Room();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                room = extractRoom(rs);
            }
        } catch (SQLException e) {
            LOG.error("Cannot get room by id", e);
            throw new DAOException("Cannot get room by id", e);
        }
        return room;
    }

    @Override
    public List<Room> getAll() throws DAOException {
        return null;
    }

    @Override
    public List<Room> getAll(int page, int pageSize, String order) throws DAOException {
        List<Room> rooms = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = String.format(SQL_SELECT_ALL, order);

            pstmt = con.prepareStatement(sql);
            LOG.trace("Order by -->> " + order);
            pstmt.setInt(1, pageSize);
            pstmt.setInt(2, pageSize * (page - 1));

            rs = pstmt.executeQuery();
            while (rs.next()) {
                rooms.add(extractRoom(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get room all rooms", e);
            throw new DAOException("Cannot get all rooms", e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        return rooms;
    }

    @Override
    public void updateStatus(RoomStatus status, Long id) throws DAOException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_UPDATE_STATUS);
            stmt.setString(1, String.valueOf(status));
            stmt.setString(2, String.valueOf(id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Cannot update room status", e);
            throw new DAOException("Cannot update room status", e);
        } finally {
            closeStatement(stmt);
        }
    }

    @Override
    public List<Room> findRoomsByParameters(Integer places, Long typeId, Date dateIn, Date dateOut) throws DAOException {
        List<Room> rooms = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_SELECT_ALL_BY_PARAMETERS);
            pstmt.setLong(1, typeId);
            pstmt.setInt(2, places);
            pstmt.setDate(3, dateIn);
            pstmt.setDate(4, dateOut);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                rooms.add(extractRoom(rs));
            }
        } catch (SQLException e) {
            LOG.error("Cannot get rooms by parameters", e);
            throw new DAOException("Cannot get rooms by parameters", e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
        return rooms;
    }

    @Override
    public int getRoomsNumber() throws DAOException {
        int roomsNumber = 0;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_ROOMS_COUNT);
            if (rs.next()){
                roomsNumber = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("Cannot get rooms number", e);
            throw new DAOException("Cannot get rooms number", e);
        }
        return roomsNumber;
    }

    private Room extractRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setPlaces(rs.getInt("places"));
        room.setPrice(rs.getDouble("price"));
        room.setDescription(rs.getString("description"));
        room.setStatus(RoomStatus.valueOf(rs.getString("status")));

        RoomType type = new RoomType();
        type.setId(rs.getLong("type_id"));
        type.setName(rs.getString("room_types.name"));
        room.setType(type);
        return room;
    }
}
