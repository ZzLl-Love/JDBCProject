package cn.zyy.handle;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface HandleGoods {
    /**
     * 商品添加
     * @param gId 商品编码
     * @param gName 名称
     * @param gPrice 价格
     * @param gCount 数量
     */
    public void  addGoods(int gId, String gName, double gPrice , int gCount) throws SQLException;


    /**
     * 根据商品编号来删除商品
     * @param gId
     */
    public void delGoods(int gId) throws SQLException;

    /**
     * 根据商品编号来修改商品价格
     * @param gId
     */
    public void updateGoodsPrice(int gId, double newPrice) throws SQLException;

    /**
     * 展示商品列表信息
     */
    public void showGoods();

    /**
     * 商品入库
     * @param gId
     */
    public void inGoods(int gId, int inCount) throws SQLException;

    /**
     * 商品出库
     * @param gId
     */
    public void outGoods(int gId, int outCount) throws SQLException;

}
