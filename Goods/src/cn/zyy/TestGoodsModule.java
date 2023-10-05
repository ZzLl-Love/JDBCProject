package cn.zyy;

import cn.zyy.handle.HandleGoods;
import cn.zyy.handle.impl.HandleGoodsImpl;
import cn.zyy.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Zz
 * 测试使用jdbc对 mysql中goods表 进行crud的方法
 */
public class TestGoodsModule {



    /**=========================================JDBCUtils==========================================*/
    @Test
    public void testSelGoodsCount(){
        int goodsCount = JDBCUtils.selGoodsCount(1);
        System.out.println(goodsCount);
    }

    /**
     * 测试能否获取连接
     * @throws SQLException
     */
    @Test
    public void testGetConn() throws SQLException {
        Connection conn = JDBCUtils.getConn();
        System.out.println(conn);
    }

    /**
     * 测试根据id查询商品是否存在
     */
    @Test
    public void testSelGoodSById() throws SQLException {
        boolean b = JDBCUtils.selGoodsById(1);
        System.out.println(b);
    }

    /**=========================================JDBCUtils==========================================*/






    /** =========================================HandleGoodsImpl==========================================*/

    //商品处理实现类
    HandleGoods handleGoods = new HandleGoodsImpl();


    /**
     * 测试添加商品
     */
    @Test
    public void testAddGoods() throws SQLException {
        handleGoods.addGoods(4, "商品4", 800 ,700);
    }

    /**
     * 测试指定id的商品
     */
    @Test
    public void testDelGoods() throws SQLException {
        handleGoods.delGoods(5);
    }

    /**
     * 测试根据id修改商品价格
     */
    @Test
    public void testUpdateGoodsPrice() throws SQLException {
        handleGoods.updateGoodsPrice(1,600);
    }


    /**
     * 测试入库
     */
    @Test
    public void testInGoods() throws SQLException {
        handleGoods.inGoods(2, 500);
    }

    /**
     * 测试出库
     */
    @Test
    public void testOutGoods() throws SQLException {
        handleGoods.outGoods(2, 300);
    }

    /**
     * 商品列表展示
     */
    @Test
    public void testShowGoods(){
        handleGoods.showGoods();
    }
    /**=========================================HandleGoodsImpl==========================================*/


    /**
     * 测试组装Goods模块的功能
     */
    public static void main(String[] args) throws SQLException {
        ExportGoods exportGoods = new ExportGoods();
        exportGoods.selGoodsMethod();
    }
}
