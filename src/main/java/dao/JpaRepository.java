package dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import paging.Pageable;

import java.sql.SQLException;
import java.util.List;

public interface JpaRepository <T,ID>{
    <S extends T> S insert(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    <S extends T> S findByID(ID id);

    <S extends T> List<S> findAll();

    long count();

    <S extends T> List<S> findAll(Pageable pageable);
}
