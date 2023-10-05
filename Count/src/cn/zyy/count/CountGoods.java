package cn.zyy.count;

import cn.zyy.pojo.Goods;
import cn.zyy.utils.JDBCUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
  *统计商品类
  *  */
public class CountGoods implements Runnable {



    // 定义为全局变量  统计次数 得累加
    private static int chartNumber = 0;


    public synchronized static void chartGoods() {

        /**
         * 将totalCount, totalPrice,avgPrice定义为成员变量
         * 确保上一次的统计数据不会影响到下一次的统计数据
         */
        //商品总数
        int totalCount = 0;

        //商品总金额
         double totalPrice = 0;

        //商品平均价格
        double avgPrice = 0;

        //定义Sql
        String sql = "SELECT * FROM goods";
        //获取全部商品的信息
        Set<Goods> goods = JDBCUtils.selAllGoods(sql, Goods.class);
        for (Goods good : goods) {

            //计算商品总数
            totalCount += good.getNumber();
            //计算总金额
            totalPrice += good.getPrice() * good.getNumber();
        }
        //计算商品平均价格
        avgPrice=totalPrice/totalCount;
        System.out.println(" --[当前物品总数: " + totalCount + "]  " +"["+"物品总金额: "
                + totalPrice +"]  " + "[物品平均价格: " + avgPrice+"]  " );


    }

    @Override
    public void run() {
        while(true){
            System.out.println("==================================库存统计"+chartNumber+"=================================");
            chartGoods();
            System.out.println();
            chartNumber++;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
