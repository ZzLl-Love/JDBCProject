package buildApp;

import cn.zyy.ExportGoods;
import cn.zyy.login.Login;
import cn.zyy.login.impl.LoginImpl;
import cn.zyy.login.utils.LoadProperties;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * 组装不同模块的功能，完成javaSE系统练习
 * 【用户管理模块】
 * 【商品维护模块】
 * 【统计报表模块】
 *
 */
public class GoodsApplication {

    //用户登陆态,默认为false
    boolean isLogin = false;

    /**
     * 获取登陆对象
     */
    Login login = new LoginImpl();

    /**
     * 获取商品操作对象
     */
    ExportGoods exportGoods = new ExportGoods();

    /**
     * 构建商品应用程序， 组装不同模块
     */
    public void  buildGoodsApplication() throws SQLException {

        //默认未登录
        while(!isLogin){

            System.out.println("==================登陆商品系统=======================");
            //获取用户输入的username 和 password
            HashMap<String, String> userScanner = LoadProperties.getUserScanner();
            String username = userScanner.get("u");
            String password = userScanner.get("p");
            //判断登陆
            boolean loginFlag = this.login.handleLogin(username, password);
            if(loginFlag){
                //登陆成功
                System.out.println("================success进入到商品系统success=========");
                isLogin = true;
                //允许使用商品列表功能
                exportGoods.selGoodsMethod();
            }else{
                System.out.println("==================error登陆失败error=================");
                System.out.println();
            }
        }
    }

}
