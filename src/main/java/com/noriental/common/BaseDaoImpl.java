package com.noriental.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * Created by wangbeichen on 14-2-25.
 */
public class BaseDaoImpl<T, PK> implements BaseDao<T, PK> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    @Resource(name = "sqlSessionTemplate")
    private SqlSession session;

    @Override
    public boolean insert(T entry) {
        long t1 = System.currentTimeMillis();
        boolean result = session.insert(entry.getClass().getName() + Constants.OP.INSERT, entry) > 0;
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == "
                + entry.getClass().getName() + Constants.OP.INSERT);
        return result;
    }

    @Override
    public T insertGetId(T entry) {
        long t1 = System.currentTimeMillis();
        if (session.insert(entry.getClass().getName() + Constants.OP.INSERT, entry) > 0) {
            long t2 = System.currentTimeMillis();
            logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == "
                    + entry.getClass().getName() + Constants.OP.INSERT);
            return entry;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean update(T entry) {
        long t1 = System.currentTimeMillis();
        boolean result = session.update(entry.getClass().getName() + Constants.OP.UPDATE, entry) > 0;
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == "
                + entry.getClass().getName() + Constants.OP.UPDATE);
        return result;
    }

    @Override
    public boolean delete(Class<T> clazz, PK pk) {
        long t1 = System.currentTimeMillis();
        boolean result = session.delete(clazz.getName() + Constants.OP.DELETE, pk) > 0;
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == "
                + clazz.getName() + Constants.OP.DELETE);
        return result;
    }

    @Override
    public T findById(Class<T> clazz, PK pk) {
        long t1 = System.currentTimeMillis();
        T result = session.selectOne(clazz.getName() + Constants.OP.GET, pk);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == "
                + clazz.getName() + Constants.OP.GET);
        return result;
    }

    @Override
    public T find(String sqlId, Object param) {
        long t1 = System.currentTimeMillis();
        T result = session.selectOne(sqlId, param);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    @Override
    public T find(String sqlId, Map<String, Object> params) {
        long t1 = System.currentTimeMillis();
        T result = session.selectOne(sqlId, params);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    @Override
    public List<T> findList(String sqlId, Map<String, Object> params) {
        long t1 = System.currentTimeMillis();
        List<T> list = session.selectList(sqlId, params);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return list;
    }

    @Override
    public List<T> findList(Class<T> clazz, Map<String, Object> params) {
        long t1 = System.currentTimeMillis();
        List<T> list = this.findList(clazz.getName() + ".list", params);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == "
                + clazz.getName() + ".list");
        return list;
    }

    @Override
    public List<T> findList(String sqlId, Object object) {
        long t1 = System.currentTimeMillis();
        List<T> list = session.selectList(sqlId, object);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return list;
    }

    @Override
    public boolean update(String sqlId, Object param) {
        long t1 = System.currentTimeMillis();
        boolean result = session.update(sqlId, param) > 0;
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    /**
     * 返回结果不能通过dubbo默认的hessian序列化
     */
    @Override
    @Deprecated
    public List<T> findPagedList(String sqlId, Object object, RowBounds rowBounds) {
        long t1 = System.currentTimeMillis();
        List<T> result = session.selectList(sqlId, object, rowBounds);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    @Override
    public PageResult<T> findPageResult(String sqlId, Object object, RowBounds rowBounds) {
        long t1 = System.currentTimeMillis();
        PageList<T> list = (PageList<T>) session.selectList(sqlId, object, rowBounds);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);

        /**
         * 生成可序列化的返回结果，同时保留分页数据
         */
        PageResult<T> result = new PageResult<T>(list.subList(0, list.size()), list.getPaginator());
        return result;
    }

    @Override
    public boolean insert(String sqlId, Object param) {
        long t1 = System.currentTimeMillis();
        boolean result = session.insert(sqlId, param) > 0;
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    @Override
    public boolean delete(String sqlId, Object param) {
        long t1 = System.currentTimeMillis();
        boolean result = session.delete(sqlId, param) > 0;
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    public int count(String sqlId, Object param) {
        long t1 = System.currentTimeMillis();
        int result = session.selectOne(sqlId, param);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    @Override
    public List<Map> findMapList(String sqlId, Map<String, Object> params) {
        long t1 = System.currentTimeMillis();
        List<Map> result = session.selectList(sqlId, params);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    @Override
    public boolean updateByMap(String sqlId, Map params) {
        // TODO Auto-generated method stub
        long t1 = System.currentTimeMillis();
        boolean result = session.update(sqlId, params) > 0;
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }

    public Object findout(String sqlId, Object param) {
        long t1 = System.currentTimeMillis();
        Object result = session.selectOne(sqlId, param);
        long t2 = System.currentTimeMillis();
        logger.debug("+++++ SQL Time = " + Double.toString((t2 - t1) / 1000.0) + "s == " + sqlId);
        return result;
    }
}
