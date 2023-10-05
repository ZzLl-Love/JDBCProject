package cn.zyy.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class JDBCUtils {

    private static DataSource dataSource;
    static{
        Properties properties = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        try {
            //加载配置文件资源
            properties.load(in);
            //获取连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池中的连接
     * @return
     */
    public static Connection getConn()  {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 根据id查询商品是否存在
     * @param gId
     * @return true-- 商品存在
     */
    public static boolean selGoodsById(int gId) throws SQLException {

        //存储查询结果
        boolean exists = false;
        Connection conn = getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selSql = "SELECT * FROM goods WHERE id = ?";
        try {
            statement = conn.prepareStatement(selSql);
            statement.setInt(1, gId);
            resultSet = statement.executeQuery();
            exists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            //关闭连接
            close(conn, statement, resultSet);
        }
        return exists;
    }

    /**
     * 根据id查询商品数量
     * @param gId
     * @return
     */
    public static int selGoodsCount(int gId){

        //商品数量
        int number = 0;
        Connection conn = getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selSql = "SELECT number FROM goods WHERE id = ?";
        try {
            statement = conn.prepareStatement(selSql);
            statement.setInt(1, gId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                number = resultSet.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, statement, resultSet);
        }
        return number;
    }

    /**
     * add（新增） del（删除） update（更新）
     * @param sql
     * @param args
     * @return
     */
    public static int update(String sql, Object... args)  {
        //受影响行数
        int count = 0;
        Connection conn = getConn();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i+1, args[i]);
            }
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * 获取全部的商品信息
     * @param sql
     * @param clz
     * @param <T>
     * @return
     */
    public static  <T> Set<T> selAllGoods(String sql, Class<T> clz){
        Connection conn = getConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Set<T> set = new HashSet<>();
        try {
             statement = conn.prepareStatement(sql);
             resultSet = statement.executeQuery();
             ResultSetMetaData metaData = resultSet.getMetaData();
             int columnCount = metaData.getColumnCount();

             //处理结果集
            while (resultSet.next()) {
                T t = clz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取列的值
                    Object object = resultSet.getObject(i + 1);
                    //列的名称
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = clz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                set.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn,statement, resultSet);
        }
        return set;
    }

    /**
     * 关闭连接的方法
     * @param conn  连接
     * @param statement 处理sql的对象
     * @param resultSet 结果集
     */
    public static void close(Connection conn,Statement statement,ResultSet resultSet){

        if(resultSet != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
