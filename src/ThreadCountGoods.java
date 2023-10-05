import cn.zyy.count.CountGoods;

/**
 * 线程统计商品类
 */
public class ThreadCountGoods {
    public static void main(String[] args) {
        CountGoods countGoods = new CountGoods();
        Thread thread = new Thread(countGoods);
        thread.start();
    }
}
