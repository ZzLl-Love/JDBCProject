package cn.zyy.login.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public class LoadProperties {

     /**
      * 存储用户名和密码的容器
      */
     private static Properties properties = new Properties();

     /**
      * 配置文件名
      */
     private final static String fileName = "../../../../userInfo.properties";


    static Scanner scanner = new Scanner(System.in);

     /**
      * 调用静态方法----》类的加载--执行--》static代码块
      *
      * ==>得出 ==
      * 将获取用户名和密码设置为一个静态方法
      */
     static {
         try {
             InputStream inputStream = LoadProperties.class.getResourceAsStream(fileName);
             properties.load(inputStream);
         } catch (IOException e) {
             e.printStackTrace();
         }
         if (properties.isEmpty()) {
             throw new RuntimeException(fileName + "中的数据为空....");
         }
     }


     /**
      * 返回配置文件fileName中的用户名userName
      *
      * @return
      */
     public static String getUserName() {
         return (String) properties.get("userName");
     }

     /**
      * 返回配置文件fileName中的用户名passWord
      *
      * @return
      */
     public static String getPassword() {
         return (String) properties.get("passWord");
     }

     /**
      *获取用户输入的用户名和密码
      * @return
      */
     public static HashMap<String, String> getUserScanner(){
         HashMap<String,String> map = new HashMap<>();
         System.out.print("请输入你的用户名:");
         String username = scanner.nextLine();
         System.out.print("请输入你的密码：");
         String password = scanner.nextLine();
         map.put("u", username);
         map.put("p", password);
         return map;
     }

 }

