package com.my.dao.factory;

import com.my.dao.datasource.DataSourceFactory;
import com.my.dao.datasource.DatasourceType;
import com.my.dao.OrderDao;
import com.my.dao.mySqlDaoImpl.OrderDaoMySql;
import com.my.dao.RoomDao;
import com.my.dao.mySqlDaoImpl.RoomDaoMySql;
import com.my.dao.RoomTypeDao;
import com.my.dao.mySqlDaoImpl.RoomTypeDaoMySql;
import com.my.dao.UserDao;
import com.my.dao.mySqlDaoImpl.UserDaoMySql;
import com.my.exception.DBException;


public class DaoFactoryMySql{

    //TO DO ========
    private DaoFactoryMySql(){

    }

    public static UserDao getUserDao() throws DBException {
        return new UserDaoMySql(DataSourceFactory.getInstance(DatasourceType.MY_SQL).getDatasource());
    }

    public static RoomDao getRoomDao() throws DBException {
        return new RoomDaoMySql(DataSourceFactory.getInstance(DatasourceType.MY_SQL).getDatasource());
    }

    public static RoomTypeDao getRoomTypeDao() throws DBException {
        return new RoomTypeDaoMySql(DataSourceFactory.getInstance(DatasourceType.MY_SQL).getDatasource());
    }

    public static OrderDao getOrderDao() throws DBException {
        return new OrderDaoMySql(DataSourceFactory.getInstance(DatasourceType.MY_SQL).getDatasource());
    }
}
