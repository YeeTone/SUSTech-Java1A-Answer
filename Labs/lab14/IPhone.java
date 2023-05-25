package labs.lab14;

public class IPhone {
    private String name;
    private int price;

    public IPhone(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "IPhone{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
