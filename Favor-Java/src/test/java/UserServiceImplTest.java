import com.favor.dao.UserDao;
import com.favor.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:Spring-mybatis.xml"})
public class UserServiceImplTest {

    private User user;
    @Resource
    private UserDao userDao;

    @Test
    public void getUserByName(){
        String name = "icegxy";
        user = userDao.getUserByName(name);
        System.out.println(user.toString());
    }
}
