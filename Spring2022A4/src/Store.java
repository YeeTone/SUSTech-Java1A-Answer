package Spring2022A4;

import java.util.ArrayList;

public class Store {
    private static int cnt = 0;
    private int id;
    private String name;
    private ArrayList<Product> productList;
    private float income;

    public Store(String name, ArrayList<Product> productList, float income) {
        this.id = ++cnt;
        this.name = name;
        this.productList = productList;
        this.income = income;
    }

    public Store(String name) {
        this(name, new ArrayList<>(), 0f);
    }

    public boolean hasProduct(Product product) {
        if(product == null){
            return false;
        }

        return productList.contains(product);

    }

    public boolean addProduct(Product product) {
        if (product == null) {
            return false;
        }

        if (hasProduct(product)) {
            return false;
        }

        productList.add(product);
        return true;
    }

    public boolean removeProduct(Product product) {
        if (product == null) {
            return false;
        }

        if (!hasProduct(product)) {
            return false;
        }

        productList.remove(product);
        return true;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void transact(Product product, int method) {
        if (product == null) {
            return;
        }

        switch (method) {
            case 0: {
                {
                    income += product.getPrice();
                    removeProduct(product);
                }
                break;
            }

            case 1: {
                income -= product.getPrice();
                addProduct(product);
                break;
            }
        }
    }
}

