package model;

public class Item {
    private String code;
    private String name;
    private int qty;
    private double price;

    public Item() {
    }

    public Item(String code, String name, int qty, double price) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
