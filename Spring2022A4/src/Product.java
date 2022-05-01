package Spring2022A4;

import java.util.ArrayList;
import java.util.Objects;

public class Product {
    private static int cnt = 0;
    private int id;
    private String name;
    private float price;
    private ArrayList<Integer> ratings;

    private Store purchasedStore;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
        this.ratings = new ArrayList<>();
        this.id = ++cnt;
        this.purchasedStore = null;
    }

    public boolean setRating(int rating) {
        if (1 <= rating && rating <= 5) {
            this.ratings.add(rating);
            return true;
        }

        return false;
    }

    public float getAvgRating() {
        if (ratings.isEmpty()) {
            return 0f;
        }

        return ratings.stream().mapToInt(e -> e).sum() * 1.0f / ratings.size();
    }

    public void purchased(Store store){
        this.purchasedStore = store;
    }

    public void refunded() {
        this.purchasedStore = null;
    }

    public Store getPurchasedStore() {
        return purchasedStore;
    }

    @Override
    public String toString() {
        return String.format("Product ID %d, %s, RMB %.2f, Rating %.1f", id, name, price, getAvgRating());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public float getPrice() {
        return price;
    }
}

