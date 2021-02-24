package com.nure.service;

import com.nure.entity.Room;
import com.nure.exception.DAOException;
import com.nure.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface RoomService {
    /**
     * Finds all rooms
     *
     * @param page - a number from which entries will be returned
     * @param pageSize -amount of entries
     * @param sort - parameter of sort
     * @param order - in which order will sort
     * @param status - status of room
     * @return a {@link List} of rooms
     * @throws ServiceException if issues in dao layer
     */
    List<Room> getAllRooms(int page, int pageSize, String sort, String order, String status) throws ServiceException;

    /**
     * Finds all rooms by parameters
     *
     * @param places - room places
     * @param type - id type of room
     * @param dateIn - arrival date
     * @param dateOut - departure date
     * @return a {@link List} of rooms
     * @throws ServiceException if issues in dao layer
     */
    List<Room> getAllRoomsByParameters(Integer places, String type, Date dateIn, Date dateOut) throws ServiceException;

    /**
     * Finds room by id
     *
     * @param id - room id
     * @return room
     * @throws ServiceException if issues in dao layer
     */
    Room getRoomById(Long id) throws ServiceException;

    /**
     * Finds rooms number
     *
     * @return count of rooms
     * @throws ServiceException if issues in dao layer
     */
    int getRoomsCount() throws ServiceException;

    /**
     * Finds rooms number by status
     *
     * @param status - room status
     * @return count of rooms
     * @throws ServiceException if issues in dao layer
     */
    int getRoomsNumberByStatus(String status) throws ServiceException;
}
