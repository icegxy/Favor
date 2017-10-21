import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:Spring-mybatis.xml"})
public class JDBCTest {

    @Test
    public void getConnection(){
        Connection connection = null;
        String url = "jdbc:postgresql://127.0.0.1:5432/favor";
        String user = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("是否成功连接pg数据库"+connection);
    }
}
