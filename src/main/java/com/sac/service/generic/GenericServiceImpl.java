package com.sac.service.generic;

import com.sac.dao.generic.GenericDao;

import java.util.List;

/**
 * 通用接口的实现类
 * 抽象方法由子类来实现
 * Created by EAISON on 2017/11/3.
 */
public abstract class GenericServiceImpl<Model, Key> implements GenericService<Model, Key> {

    /**
     * 定义成抽象方法,由子类实现,完成dao的注入
     *
     * @return GenericDao实现类
     */
    public abstract GenericDao<Model, Key> GetDao();

    /**
     * 插入记录
     *
     * @param model
     * @return
     */
    @Override
    public int insertModel(Model model) {
        return GetDao().insertModel(model);
    }

    /**
     * 更新记录
     *
     * @param model
     * @return
     */
    @Override
    public int updateModel(Model model) {
        return GetDao().updateModel(model);
    }

    /**
     * 根据条件删除记录
     *
     * @param key
     * @return
     */
    @Override
    public int deleteByKey(Key key) {
        return GetDao().deleteByKey(key);
    }

    /**
     * 根据查询条件获取记录
     *
     * @param key
     * @return
     */
    @Override
    public List<Model> getByKey(Key key) {
        return GetDao().getByKey(key);
    }
}
