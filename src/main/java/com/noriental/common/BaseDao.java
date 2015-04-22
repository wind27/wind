package com.noriental.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.sumory.mybatis.pagination.result.PageResult;

/**
 * Created by wangbeichen on 14-2-25.
 */
public interface BaseDao<T, PK> {

    public boolean insert(T entry);

    public T insertGetId(T entry);

    public boolean update(T entry);

    public boolean update(String sqlId, Object param);

    public boolean delete(Class<T> clazz, PK pk);

    public T findById(Class<T> clazz, PK pk);

    public T find(String sqlId, Object param);

    public T find(String sqlId, Map<String, Object> params);

    public List<T> findList(String sqlId, Map<String, Object> params);

    public List<T> findList(Class<T> clazz, Map<String, Object> params);

    public List<T> findList(String sqlId, Object object);

    public List<T> findPagedList(String sqlId, Object object, RowBounds rowBounds);

    public PageResult<T> findPageResult(String sqlId, Object object, RowBounds rowBounds);

    public boolean insert(String sqlId, Object param);

    public boolean delete(String sqlId, Object param);

    public List<Map> findMapList(String sqlId, Map<String, Object> params);

    public boolean updateByMap(String sqlId, Map params);

    public Object findout(String sqlId, Object param);
}
