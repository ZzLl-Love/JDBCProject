package cn.zyy.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * 商品类
 */
public class Goods implements Serializable {

    private static final long serialVersionUID = 5398522993768807172L;
    /**
     * 编码
     */
    private int id;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private double price;

    /**
     * 数量
     */
    private int number;

    public Goods() {
    }

    public Goods(int id) {
        this.id = id;
    }

    public Goods(int id, String name, double price, int number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Goods={" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }

    /**
     * 重写id的hashCode和equals 来确保商品编码唯一
     * @param
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return id == goods.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
