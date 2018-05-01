package com.qf.model;

/**
 * Created by Administrator on 2018/5/1.
 */
public class Shop {
    private Long id;
    private String goods;
    private Double price;
    private int num;
    private int hot;

    public Shop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", goods='" + goods + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", hot=" + hot +
                '}';
    }
}
