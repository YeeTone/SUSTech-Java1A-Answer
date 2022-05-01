import java.util.ArrayList;

public class CustomerOfficial {
    public static int cnt = 0; // initialized to 0, and will increase by 1 when the constructor is called.
    public int id;  // unique for each customer and the value is set to cnt.
    public String name;
    public ArrayList<ProductOfficial> shoppingCart; // The list of purchased products; default is empty.
    public float wallet;

    public CustomerOfficial(String name, float wallet) {
        cnt++;
        this.id = cnt;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
        this.wallet = wallet;
    }

    public boolean rateProduct(ProductOfficial product, int rating) {
        return product.setRating(rating);
    }

    public boolean purchaseProduct(StoreOfficial store, ProductOfficial product) {
        if (!store.hasProduct(product) || wallet < product.getPrice())
            return false;
        product.lastStore = store; //////
        store.transact(product, 0);
        shoppingCart.add(product);
        updateWallet(-product.getPrice());
        return true;
    }

    public void updateWallet(float amount) {
        wallet += amount;
    }

    public boolean refundProduct(ProductOfficial product) {
        if (!boughtProduct(product))
            return false;
        shoppingCart.remove(product);
        updateWallet(product.getPrice());
        product.lastStore.transact(product, 1);
        product.lastStore = null;
        return true;
    }

    public boolean boughtProduct(ProductOfficial product) { //////
        for (ProductOfficial value : shoppingCart) {
            if (value.getId() == product.getId())
                return true;
        }
        return false;
    }

    public void viewShoppingCart(SortByOfficial sortMethod) {
        ArrayList<ProductOfficial> sortList = sort(sortMethod);
        for (ProductOfficial product : sortList)
            System.out.println(product.toString());
    }

    public ArrayList<ProductOfficial> sort(SortByOfficial sortMethod) {
        ArrayList<ProductOfficial> sortList = new ArrayList<>(shoppingCart);
        for (int i = 1; i < sortList.size(); i++) {
            for (int j = 0; j < sortList.size() - 1; j++) {
                if ((sortMethod == SortByOfficial.Rating && sortList.get(j).getAvgRating() > sortList.get(j + 1).getAvgRating())
                        || (sortMethod == SortByOfficial.Price && sortList.get(j).getPrice() > sortList.get(j + 1).getPrice())) {
                    ProductOfficial temp = sortList.get(j);
                    sortList.set(j, sortList.get(j + 1));
                    sortList.set(j + 1, temp);
                }
            }
        }
        return sortList;
    }
}

