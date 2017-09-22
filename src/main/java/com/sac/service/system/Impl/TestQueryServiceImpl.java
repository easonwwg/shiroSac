package com.sac.service.system.Impl;

import com.sac.dao.system.TestQueryDao;
import com.sac.service.system.Interface.ITestQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EAISON on 2017/9/22.
 */

@Service
public class TestQueryServiceImpl implements ITestQueryService {

    @Autowired
    private TestQueryDao queryDao;

    @Override
    public List<String> GetAllUserName() {
        return  queryDao.GetAllUserName();
    }
}
