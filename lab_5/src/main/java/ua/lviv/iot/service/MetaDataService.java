package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.MetaDataDaoImpl;
import ua.lviv.iot.model.metadata.TableMetaData;

import java.sql.SQLException;
import java.util.List;

public class MetaDataService {
    public List<String> findAllTableName() throws SQLException {
        return new MetaDataDaoImpl().findAllTableName();
    }

    public List<TableMetaData> getTablesStructure() throws SQLException {
        return new MetaDataDaoImpl().getTablesStructure();
    }

}
