package ua.lviv.iot.transformer;

import ua.lviv.iot.model.Annotation.Column;
import ua.lviv.iot.model.Annotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Transformer<T> {
    private final Class<T> clazz;

    public Transformer(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public Object fromResultSetToEntity(final ResultSet rs)
            throws SQLException {
        //create new object
        Object entity = null;
        try {
            entity = clazz.getConstructor().newInstance();
            if (clazz.isAnnotationPresent(Table.class)) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    // if field is annotated with @Column
                    if (field.isAnnotationPresent(Column.class)) {
                        Column column = (Column) field.getAnnotation(Column.class);
                        String name = column.name();
                        field.setAccessible(true);
                        Class fieldType = field.getType();
                        if (fieldType == String.class) {
                            field.set(entity, rs.getString(name));
                        } else if (fieldType == Integer.class) {
                            field.set(entity, rs.getInt(name));
                        } else if (fieldType == Timestamp.class) {
                            field.set(entity, rs.getTimestamp(name));
                        } else if (fieldType == Double.class) {
                            field.set(entity, rs.getDouble(name));
                        }
                    }
                }
            }
        } catch (InstantiationException | InvocationTargetException
                | IllegalAccessException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }
}
