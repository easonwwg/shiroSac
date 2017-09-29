import com.sac.dao.system.RoleDao;
import com.sac.dao.system.UserDao;
import com.sac.pojo.system.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

/**
 * Created by EAISON on 2017/9/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml",
        "classpath:spring/spring-mybatis.xml"})
public class restTest {

  /*  @Autowired
    ITestQueryService service;*/
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public  void  GetNames(){
       // System.out.print(service.GetAllUserName());
      //  System.out.print(queryDao.GetAllUserName());
    }

    @Test
    public  void  getAllUsers(){
     /*   List<User> users= userDao.userLists();
        users.stream().forEach(s->System.out.print(s));*/
      /* System.out.print(userDao.getByWhere("eason"));*/
        /*System.out.print(userDao.listRoleByUserId(1));*/
       /* System.out.print(roleDao.GetResourcesByRoleId(1));*/
       /* User u= userDao.login("eason");
        System.out.print(u);*/

        String sta=  new Md5Hash("1qazXSW@","2017-06-16 11:15:33").toString();
        System.out.print(sta);
    }

    @Test
    public  void  patten(){
        PatternMatcher patternMatcher
                = new AntPathMatcher();
        String adminUrl="/user/**";
        String thisUrl="/user/login";
        boolean macth = patternMatcher.matches(adminUrl, thisUrl);
        System.out.print(macth);
    }

}
