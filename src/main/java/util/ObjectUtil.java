package util;

import annotation.Id;
import model.Trip;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Arrays;

public class ObjectUtil {
    public static Object getMethod(Object object, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String fieldName = field.getName();
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        String getMethodName = "get" + fieldName;
        Method method = object.getClass().getMethod(getMethodName);

        return method.invoke(object);
    }

    public static void setMethod(Object object, Field field, Object fieldData) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(object, fieldData);
    }

    public static Field getFieldByName(Class clazz, String fieldName) throws NoSuchFieldException {
        return clazz.getDeclaredField(fieldName);
    }

    public static void copyProperties(Object source, Object destinaion) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object fieldData = getMethod(source, field);
            setMethod(destinaion, field, fieldData);
        }
    }

    public static Object map(Class<?> tClass,ResultSet resultSet) throws IllegalAccessException, InstantiationException, NoSuchFieldException, SQLException {
        Field[] fields=tClass.getDeclaredFields();
        Object object=tClass.newInstance();
        for (int i = 0; i <fields.length ; i++) {
            if (fields[i].isAnnotationPresent(Id.class)){
                setMethod(object,fields[i],resultSet.getObject(AnnotationUtil.getPrimaryKey(tClass,fields[i].getName())));
            }else {
                if (fields[i].getType().getName().equals("java.time.LocalTime")){

                    setMethod(object,fields[i],resultSet.getTime(AnnotationUtil.getFieldName(tClass,fields[i].getName())).toLocalTime());
                    continue;
                }

                    setMethod(object,fields[i],resultSet.getObject(AnnotationUtil.getFieldName(tClass,fields[i].getName())));

            }
        }
        return object;
    }



}
