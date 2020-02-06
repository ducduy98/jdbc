package dao.impl;


import dao.JpaRepository;
import paging.Pageable;
import util.AnnotationUtil;
import util.MySQLConnectionUtil;
import util.ObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicQuery<T, ID> implements JpaRepository<T, ID> {
    private Connection connection;

    private Class<T> tClass;

    public BasicQuery() {
        connection = MySQLConnectionUtil.getConnetion();
        tClass = (Class<T>) ((ParameterizedType) (getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    @Override
    public <S extends T> S insert(T entity) throws SQLException {
        StringBuilder sql = new StringBuilder(Query.INSERT);
        sql.append(AnnotationUtil.getTableName(tClass)).append("(");
        Field[] fields = entity.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                if (field.getName().equals("id")) {
                    sql.append(AnnotationUtil.getPrimaryKey(tClass, field.getName())).append(",");
                } else {

                    sql.append(AnnotationUtil.getFieldName(tClass, field.getName())).append(",");
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        });

        sql.deleteCharAt(sql.length() - 1);
        sql.append(")").append(Query.VALUES).append("(");

        Arrays.stream(fields).forEach(field -> {

            sql.append("?").append(",");

        });
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        try {
            for (int i = 0; i < fields.length; i++) {
                preparedStatement.setObject(i + 1, ObjectUtil.getMethod(entity, fields[i]));
            }
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        }

        return (S) entity;

    }

    @Override
    public void update(T entity) throws SQLException {
        StringBuilder sql = new StringBuilder(Query.UPDATE).append(AnnotationUtil.getTableName(tClass)).append(Query.SET);
        Field[] fields = entity.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                if (!field.getName().equals("id")) {
                    sql.append(AnnotationUtil.getFieldName(tClass, field.getName())).append(" = ?,");
                }

            }catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
        sql.deleteCharAt(sql.length()-1);
        sql.append(Query.WHERE);

        Arrays.stream(fields).forEach(field -> {
            try {
                if (field.getName().equals("id")) {
                    sql.append(AnnotationUtil.getPrimaryKey(tClass, field.getName())).append("= ?");
                }

            }catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement(sql.toString());
            for (int i = 1; i <fields.length ; i++) {
                preparedStatement.setObject(i,ObjectUtil.getMethod(entity,fields[i]));
            }
            preparedStatement.setObject(fields.length,ObjectUtil.getMethod(entity,fields[0]));

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
           connection.rollback();
        }


    }

    @Override
    public <S extends T> S findByID(ID id) {
        try {
            StringBuilder sql=new StringBuilder(Query.SELECT)
                    .append(AnnotationUtil.getTableName(tClass))
                    .append(Query.WHERE)
                    .append(AnnotationUtil.getPrimaryKey(tClass,"id")).append(" = ?");
            PreparedStatement preparedStatement=connection.prepareStatement(sql.toString());
            preparedStatement.setObject(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            Object object=null;

            while (resultSet.next()){
                object=ObjectUtil.map(tClass,resultSet);
            }

            return (S) object;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public <S extends T> List<S> findAll() {
        StringBuilder sql=new StringBuilder(Query.SELECT).append(AnnotationUtil.getTableName(tClass));
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql.toString());
            ResultSet resultSet=preparedStatement.executeQuery();
            List<T> list=new ArrayList<>();
            while (resultSet.next()){
                T t= (T) ObjectUtil.map(tClass,resultSet);
                list.add(t);
            }
            return (List<S>) list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public long count() {
        StringBuilder sql=new StringBuilder(Query.COUNT).append(AnnotationUtil.getTableName(tClass));
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql.toString());
            ResultSet resultSet=preparedStatement.executeQuery();
            long kq=0;
            while (resultSet.next()){
                kq=resultSet.getInt(1);
            }
            return kq;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public <S extends T> List<S> findAll(Pageable pageable) {
        StringBuilder sql=new StringBuilder(Query.SELECT)
                .append(AnnotationUtil.getTableName(tClass))
                .append(Query.LIMIT).append(pageable.getSize()).append(Query.OFFSET).append(pageable.getOffset());
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql.toString());
            ResultSet resultSet=preparedStatement.executeQuery();
            List<T> list=new ArrayList<>();
            while (resultSet.next()){
                T t= (T) ObjectUtil.map(tClass,resultSet);
                list.add(t);
            }
            return (List<S>) list;
        } catch (Exception e) {
            return null;
        }
    }

    public interface Query {
        String SELECT = "SELECT * FROM ";
        String WHERE = " WHERE ";
        String AND = " AND ";
        String OR = " OR ";
        String LIKE = " LIKE ";
        String INSERT = " INSERT INTO ";
        String UPDATE = "UPDATE ";
        String DELETE = "DELETE FROM ";
        String SET = " SET ";
        String ORDER_BY = " ORDER BY ";
        String VALUES = " VALUES ";
        String COUNT = "SELECT COUNT(*) FROM ";
        String LIMIT = " LIMIT ";
        String OFFSET =" OFFSET ";

    }
}
