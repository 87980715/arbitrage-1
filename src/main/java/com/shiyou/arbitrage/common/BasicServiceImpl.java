package com.shiyou.arbitrage.common;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * FileName: BasicServiceImpl
 * Description: 基础接口实现
 * Author: ZhangYX
 * Date:  2017/11/13
 */
public class BasicServiceImpl<T> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasicMapper<T> mapper;

    @Autowired
    protected SnowflakeIdWorker snowflakeIdWorker;

    protected String idMethod = "setId";
    protected String getIdMethod = "getId";
    protected String flagMethod = "setFlag";
    protected String modifyTimeMethod = "setModifyTime";
    protected String createTimeMethod = "setCreateTime";
    protected String createTimeField = "createTime";

    protected Result<Long> insert(@Nonnull T t) {
        long id = snowflakeIdWorker.nextId();
        fill(t, this.idMethod, id);
        fill(t, this.flagMethod, 1);
        fill(t, this.createTimeMethod, Calendar.getInstance().getTime());
        int i = mapper.insert(t);
        if (i == 1) {
            return new Result<>(id);
        }
        return new Result<Long>(500);
    }

   /* protected T setBasicData(@Nonnull T t) {
        long id = snowflakeIdWorker.nextId();
        fill(t, this.idMethod, id);
        fill(t, this.flagMethod, 1);
        fill(t, this.createTimeMethod, Calendar.getInstance().getTime());
        return t;
    }*/

    protected Result<Long> insertSample(@Nonnull T t) {
        int i = mapper.insert(t);
        if (i == 1)
            return new Result<>();
        return new Result<Long>(500);
    }


    protected Result update(@Nonnull T t) {
        fill(t, this.modifyTimeMethod, Calendar.getInstance().getTime());
        int i = mapper.updateByPrimaryKeySelective(t);
        if (i == 1)
            return new Result();
        return new Result(500);
    }

    //protected Result updateBatch(@Nonnull List<T> list) {
    //    int i = mapper.updateBatchByPrimaryKeySelective(list);
    //    if (i >= 1)
    //        return new Result();
    //    return new Result(500);
    //}

    protected Result delete(@Nonnull T t) {
        fill(t, this.flagMethod, 0);
        int i = mapper.updateByPrimaryKeySelective(t);
        if (i == 1) {
            return new Result();
        }
        return new Result(500);
    }

    protected Result<T> selectByPrimaryKey(@Nonnull Long id) {
        return new Result<>(mapper.selectByPrimaryKey(id));
    }

    protected Result<Integer> selectCount(T query) {
        fill(query, this.flagMethod, 1);
        int count = mapper.selectCount(query);
        return new Result<>(200, count);
    }

    protected Result<Integer> selectCountByExample(Weekend<T> weekend) {
        int count = mapper.selectCountByExample(weekend);
        return new Result<>(200, count);
    }

    /**
     * Description: 单个查询，所有查询默认查询flag=1的数据
     * Author: ZhangYX
     * Date: 2017/11/13
     */
    protected Result<T> selectOne(@Nonnull T query) {
        fill(query, this.flagMethod, 1);
        return new Result<>(mapper.selectOne(query));
    }

    /**
     * Description: 列表查询，默认按照createTime字段倒序查询
     * Author: ZhangYX
     * Date: 2017/11/13
     */
    protected Result<List<T>> select(@Nonnull T query) {
        fill(query, this.flagMethod, 1);
        return select(query, this.createTimeField);
    }

    /**
     * Description: 列表查询，根据指定的字段倒序排序
     * Author: ZhangYX
     * Date: 2017/11/13
     */
    protected Result<List<T>> select(@Nonnull T query, @Nonnull String orderField) {
        fill(query, this.flagMethod, 1);
        Weekend<T> weekend = getWeekend(query, orderField);
        if (weekend == null) return new Result<List<T>>(500);
        List<T> result = mapper.selectByExample(weekend);
        return new Result<>(result);
    }

    protected Result<List<T>> selectByExample(Example example) {
        return new Result<>(mapper.selectByExample(example));
    }

    /**
     * Description: 分页查询，默认按照createTime字段倒序查询
     * Author: ZhangYX
     * Date: 2017/11/13
     */
    protected Result<PageInfo<T>> queryForPage(@Nonnull T query, @Nonnull int pageNum, @Nonnull int pageSize) {
        fill(query, this.flagMethod, 1);
        return queryForPage(query, pageNum, pageSize, this.createTimeField);
    }

    /**
     * Description: 分页查询，根据指定的字段倒序排序
     * Author: ZhangYX
     * Date: 2017/11/13
     */
    protected Result<PageInfo<T>> queryForPage(@Nonnull T query, @Nonnull int pageNum, @Nonnull int pageSize, @Nonnull String orderField) {
        fill(query, this.flagMethod, 1);
        if (pageNum < 1) pageNum = 1;
        if (pageSize < 1) pageSize = 1;
        Weekend<T> weekend = getWeekend(query, orderField);
        if (weekend == null) return new Result<PageInfo<T>>(500);
        PageRowBounds pageRowBounds = new PageRowBounds((pageNum - 1) * pageSize, pageSize);
        List<T> result = mapper.selectByExampleAndRowBounds(weekend, pageRowBounds);
        PageInfo<T> pageInfo = new PageInfo<>(result);
        return new Result<>(pageInfo);
    }

    /**
     * Description: 分页查询，默认按照createTime字段倒序查询
     * Author: ZhangYX
     * Date: 2018/06/29
     */
    protected Result<PageInfo<T>> queryForPage(@Nonnull Weekend<T> weekend, @Nonnull int pageNum, @Nonnull int pageSize) {
        return queryForPage(weekend, pageNum, pageSize, this.createTimeField, true);
    }

    /**
     * Description: 分页查询，根据指定的字段排序
     * Author: ZhangYX
     * Date: 2018/06/29
     */
    protected Result<PageInfo<T>> queryForPage(@Nonnull Weekend<T> weekend, @Nonnull int pageNum, @Nonnull int pageSize, @Nonnull String orderField, @Nonnull boolean isDesc) {
        if (pageNum < 1) pageNum = 1;
        if (pageSize < 1) pageSize = 1;
        if (isDesc) {
            weekend.orderBy(orderField).desc();
        } else {
            weekend.orderBy(orderField).asc();
        }
        PageRowBounds pageRowBounds = new PageRowBounds((pageNum - 1) * pageSize, pageSize);
        List<T> result = mapper.selectByExampleAndRowBounds(weekend, pageRowBounds);
        PageInfo<T> pageInfo = new PageInfo<>(result);
        return new Result<>(pageInfo);
    }

    protected void fill(T obj, String fieldName, Object value) {
        Class<?> clazz = obj.getClass();
        try {
            Method method = clazz.getMethod(fieldName, value.getClass());
            if (method == null) return;
            if (fieldName.equals(idMethod)) {
                Method getMethod = clazz.getMethod(getIdMethod);
                Object id = getMethod.invoke(obj);
                if (id == null) {
                    method.invoke(obj, value);
                }
            } else {
                method.invoke(obj, value);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected Weekend<T> getWeekend(T query, String orderField) {
        Weekend<T> weekend = new Weekend<>((Class<T>) query.getClass(), Boolean.TRUE.booleanValue());
        weekend.orderBy(orderField).desc();
        WeekendCriteria<?, Object> criteria = weekend.weekendCriteria();
        try {
            Map<String, Object> describe = PropertyUtils.describe(query);
            for (Map.Entry<String, Object> entry : describe.entrySet()) {
                Object value = entry.getValue();
                if (value == null || StringUtil.isEmpty(value.toString())) {
                    continue;
                }
                if (value instanceof Class) {
                    continue;
                }
                if (entry.getKey().equals("pageNum")) {
                    continue;
                }
                if (entry.getKey().equals("pageSize")){
                    continue;
                }
                criteria.andEqualTo(entry.getKey(), value);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return weekend;
    }

}
