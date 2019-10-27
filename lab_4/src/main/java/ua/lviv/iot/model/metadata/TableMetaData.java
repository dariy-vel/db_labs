package ua.lviv.iot.model.metadata;

import java.util.ArrayList;
import java.util.List;

public class TableMetaData {
    private String dBName;
    private String tableName;
    private List<ColumnMetaData> columnMetaData = new ArrayList<>();
    private List<ForeignKeyMetaData> foreignKeyList = new ArrayList<>();

    public String getDBName() {
        return dBName;
    }

    public void setDBName(final String dBName) {
        this.dBName = dBName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnMetaData> getColumnMetaData() {
        return columnMetaData;
    }

    public void setColumnMetaData(final List<ColumnMetaData> columnMetaData) {
        this.columnMetaData = columnMetaData;
    }

    public List<ForeignKeyMetaData> getForeignKeyList() {
        return foreignKeyList;
    }

    public void setForeignKeyList(final List<ForeignKeyMetaData> foreignKeyList) {
        this.foreignKeyList = foreignKeyList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TABLE: ").append(tableName).append("\n");
        for (ColumnMetaData column : columnMetaData) {
            stringBuilder.append(column).append("\n");
        }
        for (ForeignKeyMetaData fk : foreignKeyList) {
            stringBuilder.append(fk).append("\n");
        }
        return stringBuilder.toString();
    }
}
