package util;

import annotation.Column;
import annotation.Entity;
import annotation.Id;
import model.Trip;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AnnotationUtil {
    public static String getTableName(Class<?> clazz) {
        return clazz.getDeclaredAnnotation(Entity.class).name();
    }

    public static String getPrimaryKey(Class<?> tClass, String fieldName) throws NoSuchFieldException {
        return tClass.getDeclaredField(fieldName).getDeclaredAnnotation(Id.class).value();
    }

    public static String getFieldName(Class<?> tClass,String fieldName) throws NoSuchFieldException {
        return tClass.getDeclaredField(fieldName).getDeclaredAnnotation(Column.class).value();
    }

    public static void main(String[] args) throws NoSuchFieldException {
        AnnotationUtil annotationUtil = new AnnotationUtil();
//        System.out.println(annotationUtil.getTableName(Trip.class));
        System.out.println(annotationUtil.getPrimaryKey(Trip.class, "id"));

        Field[] fields=Trip.class.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                if (!field.getName().equals("id"))
                System.out.println(annotationUtil.getFieldName(Trip.class,field.getName()));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
    }
}
