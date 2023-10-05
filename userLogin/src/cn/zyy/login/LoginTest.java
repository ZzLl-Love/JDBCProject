package cn.zyy.login;

import cn.zyy.login.impl.LoginImpl;
import cn.zyy.login.utils.LoadProperties;
import org.junit.Test;

public class LoginTest {

    /**
     * 测试是否能够拿到配置文件中的用户名和密码
     */
    @Test
    public void testLoadProperties(){

        String userName = LoadProperties.getUserName();
        System.out.println(userName);
        String password = LoadProperties.getPassword();
        System.out.println(password);
    }


    /**
     * 模拟登陆成功
     */
    @Test
    public void testLoginSuccess(){
        Login login = new LoginImpl();
        boolean loginFlag = login.handleLogin("root", "root");
        System.out.println(loginFlag==true? "登陆成功" : "登陆失败");

    }

    /**
     * 模拟登陆失败
     */
    @Test
    public void testLoginFail(){
        Login login = new LoginImpl();
        boolean loginFlag = login.handleLogin("root", "root1");
        System.out.println(loginFlag==true? "登陆成功" : "登陆失败");
    }
}
