import java.util.ArrayList;

public class StoreOfficial {
    public static int cnt = 0; // initialized to 0, and will increase by 1 when the constructor is called.
    public int id; // unique for each store and the value is set to cnt.
    public String name;
    public ArrayList<ProductOfficial> productList;
    public float income;

    public StoreOfficial(String name) {
        cnt++;
        this.id = cnt;
        this.name = name;
        this.productList = new ArrayList<>();
        this.income = 0;
    }

    public StoreOfficial(String name, ArrayList<ProductOfficial> productList, float income) {
        cnt++;
        this.id = cnt;
        this.name = name;
        this.productList = productList;
        this.income = income;
    }

    public boolean addProduct(ProductOfficial product) {
        if (hasProduct(product))
            return false;
        productList.add(product);
        return true;
    }

    public boolean removeProduct(ProductOfficial product) {
        if (!hasProduct(product))
            return false;
        productList.remove(product); //
        return true;
    }

    public ArrayList<ProductOfficial> getProductList() {
        return productList;
    }

    public boolean hasProduct(ProductOfficial product) {
        for (ProductOfficial value : productList) {
            if (value.getId() == product.getId())
                return true;
        }
        return false;
    }

    public void transact(ProductOfficial product, int method){
        switch(method){
            case 0 : // purchase
                removeProduct(product);
                income += product.getPrice();
                break;
            case 1 : // refund
                addProduct(product);
                income -= product.getPrice();
                break;
        }
    }
}
