import buildApp.GoodsApplication;

import java.sql.SQLException;

/**
 * 商品程序启动类
 */
public class ApplicationStart {

    public static void main(String[] args) throws SQLException {
        GoodsApplication goodsApplication = new GoodsApplication();
        /**
         * Start
         */
        goodsApplication.buildGoodsApplication();
    }
}
