package cn.zyy.login.impl;

import cn.zyy.login.Login;
import cn.zyy.login.utils.LoadProperties;

public class LoginImpl implements Login {


    public boolean handleLogin(String username, String password) {

        /**
         * 判断用户名是否正确
         */
        if(! username.equals(LoadProperties.getUserName())){
            //throw new RuntimeException(username+ "和配置文件的用户名不相同");
            return false;
        }

        /**
         * 判断密码是否正确
         */
        if(! password.equals(LoadProperties.getPassword())){

            //throw new RuntimeException(password + "和配置文件密码不相同");
            return false;
        }

        return true;
    }
}
