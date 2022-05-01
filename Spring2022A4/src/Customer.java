package Spring2022A4;

import java.util.ArrayList;

public class Customer {
    private static int cnt = 0;
    private int id;
    private String name;
    private ArrayList<Product> shoppingCart;
    private float wallet;

    private int purchaseTime;

    public Customer(String name, float wallet) {
        this.id = ++cnt;
        this.name = name;
        this.wallet = wallet;
        this.shoppingCart = new ArrayList<>();
    }

    public boolean rateProduct(Product product, int rating) {
        return product.setRating(rating);
    }

    public void updateWallet(float amount) {
        wallet += amount;
    }

    public boolean purchaseProduct(Store store, Product product) {
        if (product.getPrice() > wallet) {
            return false;
        }

        if (!store.hasProduct(product)) {
            return false;
        }

        shoppingCart.add(product);
        updateWallet(product.getPrice() * -1);
        product.purchased(++purchaseTime, store);
        store.transact(product, 0);

        return true;
    }

    public void viewShoppingCart(SortBy sortMethod) {
        ArrayList<Product> cloned = new ArrayList<>(shoppingCart);
        cloned.sort(sortMethod::compare);
        cloned.forEach(System.out::println);
    }

    public boolean refundProduct(Product product) {
        if(!shoppingCart.contains(product)){
            return false;
        }

        shoppingCart.remove(product);
        updateWallet(product.getPrice());

        Store purchasedStore = product.getPurchasedStore();

        product.refunded();
        purchasedStore.transact(product, 1);
        return true;
    }
}
