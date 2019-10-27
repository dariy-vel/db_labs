package ua.lviv.iot.model.metadata;


public class ColumnMetaData {
    private String columnName;
    private String dataType;
    private String columnSize;
    private String decimalDigits;
    private boolean isNullable;
    private boolean isAutoIncrement;
    private boolean isPrimaryKey;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(final String dataType) {
        this.dataType = dataType;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(final String columnSize) {
        this.columnSize = columnSize;
    }

    public String getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(final String decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(final boolean nullable) {
        isNullable = nullable;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(final boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(final boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    @Override
    public String toString() {
        String isNullableString = "NOT NULL";
        if (isNullable){
            isNullableString = "NULL";
        }
        String isPrimaryKeyString = "";
        if (isPrimaryKey){
            isPrimaryKeyString = "PK";
        }
        String isAutoIncrementString = "";
        if (isAutoIncrement){
            isAutoIncrementString = "  AutoIncrement";
        }
        String str = String.format("%-15s  %-12s  %-8s  %s  %s",
                columnName,
                dataType + "(" + columnSize + ")",
                isNullableString,
                isPrimaryKeyString,
                isAutoIncrementString);
        return str;
    }
}
