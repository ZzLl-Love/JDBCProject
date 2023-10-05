package cn.zyy.login;

/**
 * 定义登陆接口
 */
public interface Login {


    /**
     * 判断登陆是否合法
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean handleLogin(String username ,String password);
}
