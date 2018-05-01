package com.qf.dao;

import com.qf.model.Shop;
import com.qf.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/1.
 */
public class ShopDao {

    public static void main(String[] args) throws SQLException {
        Long begin = new Date().getTime();
        Connection connection = JdbcUtils.getConnection();
        String prefix = "INSERT INTO goods_tab (goods,price,num,hot) VALUES";

        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            connection.setAutoCommit(false);
            // Statement st = conn.createStatement();
            // 比起st，pst会更好些
            PreparedStatement pst = connection.prepareStatement("");
            // 外层循环，总提交事务次数
            int n=0;
            for (int i = 1; i <= 100; i++) {
                // 第次提交步长
                for (int j = 1; j <= 20000; j++) {
                    // 构建sql后缀
                    n++;
                    Shop shop=new Shop();
                    shop.setGoods("iphone"+n);
                    shop.setHot(1);
                    shop.setNum(900);
                    shop.setPrice(8888.0);
                    suffix.append("('" + shop.getGoods() + "', " +shop.getPrice()+ ","+shop.getNum()+","+shop.getHot()+"),");
                }
                // 构建完整sql
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行sql
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                connection.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("cast : " + (end - begin) / 1000 + " ms");







//        Connection connection = JdbcUtils.getConnection();
//        Shop shop=new Shop();
//        shop.setGoods("iphone");
//        shop.setHot(1);
//        shop.setNum(900);
//        shop.setPrice(8888.0);
//        String sql="INSERT INTO goods_tab (goods,price,num,hot) VALUES(?,?,?,?)";
//        System.out.println(sql);
//        connection.setAutoCommit(false);
//        PreparedStatement preparedStatement=null;
//        preparedStatement = connection.prepareStatement(sql);
//        for(int i=0;i<65536;i++)
//        {
//            preparedStatement.setString(1,shop.getGoods());
//            preparedStatement.setDouble(2, shop.getPrice());
//            preparedStatement.setInt(3, shop.getNum());
//            preparedStatement.setInt(4, shop.getHot());
//            preparedStatement.addBatch();
//            if(i%10000==0){
//                preparedStatement.executeBatch();
//
//                //如果不想出错后，完全没保留数据，则可以没执行一次提交一次，但得保证数据不会重复
//
//                connection.commit();
//
//            }
//        }
//        preparedStatement.executeBatch();
//        connection.commit();
//        JdbcUtils.releaseResources(connection,preparedStatement);
//        System.out.println("OK");
    }
}
