package com.sac.dao.generic;

import java.util.List;

/**
 * mybatis的通用接口
 *
 * @param <Model> 表对应的实体
 * @param <Key>  查询对应的字段
 * Created by EAISON on 2017/11/3.
 */

public interface GenericDao<Model, Key> {

    /**
     * 插入记录到表中
     * @param model
     * @return
     */
    int insertModel(Model model);

    /**
     * 更新表记录
     * @param model
     * @return
     */
    int updateModel(Model model);

    /**
     * 根据条件删除记录
     * @param key
     * @return
     */
    int deleteByKey(Key key);

    /**
     * 根据条件查询记录
     * @param key
     * @return
     */
    List<Model> getByKey(Key key);
}
