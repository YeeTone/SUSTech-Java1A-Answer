import java.util.ArrayList;

public class ProductOfficial {
    public static int cnt = 0; // initialized to 0, and will increase by 1 when the constructor is called.
    public int id; // unique for each product and the value is set to cnt.
    public String name;
    public float price;
    public ArrayList<Integer> ratings; // ratings from different customers; default is empty.
    public StoreOfficial lastStore = null; //////

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public ProductOfficial(String name, float price) {
        cnt++;
        this.id = cnt;
        this.name = name;
        this.price = price;
        this.ratings = new ArrayList<>();
    }

    public boolean setRating(int rating) {
        if (rating < 1 || rating > 5)
            return false;
        ratings.add(rating);
        return true;
    }

    public float getAvgRating() {
        if(ratings.size() == 0)
            return 0;
        float total = 0;
        for (Integer rating : ratings)
            total += rating;
        return total / ratings.size();
    }

    @Override
    public String toString() {
        return String.format("Product ID %d, %s, RMB %.2f, Rating %.1f", this.id, this.name, this.price, this.getAvgRating());
    }
}
