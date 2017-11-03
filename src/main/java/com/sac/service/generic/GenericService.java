package com.sac.service.generic;

import java.util.List;

/**
 * 业务的通用接口
 * Created by EAISON on 2017/11/3.
 */
public interface GenericService<Model,Key> {

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
