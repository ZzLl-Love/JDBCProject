package cn.zyy.handle.impl;

import cn.zyy.handle.HandleGoods;
import cn.zyy.pojo.Goods;
import cn.zyy.utils.JDBCUtils;

import java.sql.*;
import java.util.Set;

public class HandleGoodsImpl implements HandleGoods {


    /**
     * 商品添加
     * @param gId 商品编码
     * @param gName 名称
     * @param gPrice 价格
     * @param gCount 数量
     */
    public void addGoods(int gId, String gName, double gPrice, int gCount) throws SQLException {

        //定义添加标志,默认为0 - 添加失败
        int addFlag = 0;

        //判断商品是否存在
        if(JDBCUtils.selGoodsById(gId)){
            //说明同类型的商品存在,增加数量
            int oldCount = JDBCUtils.selGoodsCount(gId);
            String sql = "UPDATE goods SET number = ? WHERE id = ?";
            addFlag = JDBCUtils.update(sql, (oldCount + gCount), gId);
        } else {
            //商品列表为null
            String  sql = "INSERT INTO goods(id, name, price, number) VALUES( ?, ?, ?, ?)";
            addFlag = JDBCUtils.update(sql,  gId, gName, gPrice, gCount);
        }

        System.out.println((addFlag > 0)?  "新增商品成功" :  "新增商品失败" );

    }

    /**
     * 删除商品
     * @param gId
     */
    public void delGoods(int gId) throws SQLException {

        //定义删除标志 默认为0 - 删除失败
        int delFlag = 0;
        if (JDBCUtils.selGoodsById(gId)) {
            String sql = "DELETE  FROM goods WHERE id = ?";
            delFlag = JDBCUtils.update(sql, gId);
        }
        System.out.println((delFlag > 0) ? "商品id: "+gId+"删除成功" : "商品为id: "+gId+"不存在" );
    }

    /**
     * 修改商品价格
     * @param gId 商品id
     * @param newPrice 新的价格
     */
    public void updateGoodsPrice(int gId, double newPrice) throws SQLException {

        //定义修改标志 默认为0 - 修改失败
        int updatePriceFlag = 0;

        if (JDBCUtils.selGoodsById(gId)) {
            String sql = "UPDATE goods SET price=? WHERE id = ?";
            updatePriceFlag = JDBCUtils.update(sql,  newPrice, gId);
        }
        System.out.println((updatePriceFlag >0) ? "修改商品id: "+gId+"的价格为" +newPrice +"成功" : "商品为id: "+gId+"不存在" );
    }

    /**
     * 商品列表展示
     */
    public void showGoods() {
        String sql = "SELECT * FROM goods";
        Set<Goods> goods = JDBCUtils.selAllGoods(sql, Goods.class);
        if (goods.isEmpty()) {
            throw new RuntimeException("商品列表为空");
        }
        //循环遍历商品列表
        for (Goods currentGoods : goods) {
            System.out.println(currentGoods);
        }
    }

    /**
     * 商品入库
     * @param gId
     * @param inCount
     */
    public void inGoods(int gId, int inCount) throws SQLException {

        //定义修改标志 默认为0 - 修改失败
        int inGoodsFlag = 0;
        if (JDBCUtils.selGoodsById(gId)) {
            //商品存在，入库
            int currentCount = JDBCUtils.selGoodsCount(gId);
            String sql = "UPDATE goods SET number = ? WHERE id = ?";
            inGoodsFlag = JDBCUtils.update(sql, (currentCount + inCount), gId);
        }
        System.out.println((inGoodsFlag >0) ? "商品id: "+gId+"入库成功" : "商品为id: "+gId+"不存在" );

    }

    /**
     * 商品出库
     * @param gId
     * @param outCount
     */
    public void outGoods(int gId, int outCount) throws SQLException {

        if (JDBCUtils.selGoodsById(gId)) {
            //商品存在
            int currentCount = JDBCUtils.selGoodsCount(gId);
            if(outCount> currentCount){
                String sql = "UPDATE goods SET number =? WHERE id = ?";
                int update = JDBCUtils.update(sql, 0, gId);
                System.out.println(update>0 ? "商品数量全部出库": "出库失败");
            }else{
                String sql = "UPDATE goods SET number =? WHERE id = ?";
                int update = JDBCUtils.update(sql, (currentCount - outCount), gId);
                System.out.println(update>0 ? "id为:"+gId+"的商品出库数量为"+outCount: "出库失败");
            }
        } else {
            System.out.println("商品编码: " + gId + "对应的商品不存在");
        }
    }
}
