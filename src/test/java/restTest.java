import com.sac.cache.RedisDao;
import com.sac.cache.RedisManager;
import com.sac.commons.SerializerUtil;
import com.sac.dao.system.MenuDao;
import com.sac.dao.system.RoleDao;
import com.sac.dao.system.UserDao;
import com.sac.pojo.system.Menu;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EAISON on 2017/9/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml",
        "classpath:spring/spring-mybatis.xml","classpath:redisconf.xml"})
public class restTest {

    /*  @Autowired
      ITestQueryService service;*/
    @Autowired
    UserDao userDao;

    @Autowired
    MenuDao menuDao;

    @Autowired
    RoleDao roleDao;

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void GetNames() {
        // System.out.print(service.GetAllUserName());
        //  System.out.print(queryDao.GetAllUserName());
    }

    @Test
    public void getAllUsers() {
     /*   List<User> users= userDao.userLists();
        users.stream().forEach(s->System.out.print(s));*/
      /* System.out.print(userDao.getByWhere("eason"));*/
        /*System.out.print(userDao.listRoleByUserId(1));*/
       /* System.out.print(roleDao.GetResourcesByRoleId(1));*/
       /* User u= userDao.login("eason");
        System.out.print(u);*/

        String sta = new Md5Hash("1qazXSW@", "2017-06-16 11:15:33").toString();
        System.out.print(sta);
    }

    @Test
    public void patten() {
        PatternMatcher patternMatcher
                = new AntPathMatcher();
        String adminUrl = "/user/**";
        String thisUrl = "/user/login";
        boolean macth = patternMatcher.matches(adminUrl, thisUrl);
        System.out.print(macth);
    }

    StringBuilder sb = new StringBuilder();
    @Test
    public void MenuTest() {
        List<Menu> rootMenus = menuDao.GetMenuList();
        //找到父节点
        Menu menu = rootMenus.stream().filter(s -> s.getParentCode().equals("0")).findFirst().get();
        //开始递归遍历
        menu.setChildMenus(getChild(menu.getCode(), rootMenus));
        //生成html页面标签
        List<Menu> menus=  menu.getChildMenus();
        GetHtml(menus);
        System.out.print(sb.toString());
    }

    private void GetHtml( List<Menu> menus) {
        if (menus.size()==0){
            return;
        }
        for (int i=0;i<menus.size();i++){
            Menu menu=menus.get(i);
            //说明还有子节点
            if (menu.getMenuUrl()==null){
                if (menu.getChildMenus()!=null){
                    sb.append("<li>");
                    sb.append(GetATag(menu));
                    sb.append(" <ul class=\"sub-menu\">");
                    List<Menu> itemMenus=menu.getChildMenus();
                    GetHtml(itemMenus);
                    sb.append("</ul>");
                    sb.append("</li>");
                }
                else {
                    sb.append("<li>");
                   // sb.append(GetLiStr(menu));
                    sb.append(GetATag(menu));
                    sb.append("</li>");
                }
            }
            else {
                sb.append("<li>");
                sb.append(GetATag(menu));
                //sb.append(GetLiStr(menu));
                sb.append("</li>");
            }
        }

    }

    String GetATag(Menu menu){
        StringBuilder sb=new StringBuilder();
        if (menu.getMenuUrl()==null){
            sb.append(" <a href=\"javascript:;\"> ");
        }
        else {
            sb.append(" <a href="+menu.getMenuUrl()+"> ");
        }
        sb.append("  <i class=\""+menu.getIcon()+"\"></i>");
        sb.append("<span class=\"title\"> "+menu.getMenuName()+" </span>");
       sb.append("<span  class=\"arrow \"> </span>");
        sb.append("  </a>");
        return sb.toString();
    }

    /**
     * 获取所有的菜单层级关系
     *
     * @param code
     * @param rootMenu
     * @return
     */
    private List<Menu> getChild(String code, List<Menu> rootMenu) {
        //子菜单
        List<Menu> childList = new ArrayList<>();
        // 遍历所有节点，将每个节点父菜单code与传过来的code比较
        for (Menu menu : rootMenu) {
            if (menu.getParentCode().equals(code)) {
                childList.add(menu);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {
            // 如果子菜单没有url，说明该子菜单下还有子菜单
            if (menu.getMenuUrl() == null) {
                // 递归
                menu.setChildMenus(getChild(menu.getCode(), rootMenu));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Resource(name = "redisDao")
    private  RedisDao redisDao;

@Resource(name = "redisManager")
private  RedisManager redisManager;

    @Test
    public  void  redisTest(){
    String test="test";
    String testValue="ssss";
      redisManager.set(test,testValue);
        System.out.print((String) redisManager.get(test));
        redisManager.del(test);
        System.out.print("删除后"+(String) redisManager.get(test));

      /*    SessionTestImpl sessionTest=new SessionTestImpl(redisTemplate);
        sessionTest.put("zhao","2222");
        System.out.print(sessionTest.get("zhao"));*/
   /*  RedisBase<String,String> redisDao=new RedisDao();
        redisDao.set("sac","qiong",1000000);
        System.out.print(redisDao.get("sac"));*/
       /*  redisTemplate.opsForValue().set("wwg","26");
        System.out.print(redisTemplate.opsForValue().get("wwg"));*/
       /* redisDao.add("h1w","w1w");
        System.out.println(redisDao.Get("h1w"));*/
    }

}




