package com.sac.web.business;

import com.sac.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author:eason
 * @Description
 * @Date: 20:27,2018/4/17
 * @ModifiedBy
 */
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @Autowired
    private ExceptionService exceptionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Model model) {
        System.out.println("xxx");
        exceptionService.test();
    }

    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public void list1(Model model)  {
        System.out.println("list1");
        exceptionService.test1();
    }

}
