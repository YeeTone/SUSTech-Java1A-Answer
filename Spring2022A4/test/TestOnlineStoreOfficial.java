import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Data {
    ProductOfficial[] productOfficials;
    StoreOfficial[] storeOfficials;
    CustomerOfficial[] customerOfficials;
    Map<String, ArrayList<ProductOfficial>> products = new Hashtable<>();
    Map<String, StoreOfficial> stores = new Hashtable<>();
    Map<String, CustomerOfficial> customers = new Hashtable<>();

    public Data() {
        ArrayList<ProductOfficial> books_extend = new ArrayList<>();
        books_extend.add(new ProductOfficial("magazine", 5.5f));
        books_extend.add(new ProductOfficial("textbook", 15f));
        books_extend.add(new ProductOfficial("dictionary", 30f));
        books_extend.add(new ProductOfficial("novel", 20f));
        books_extend.add(new ProductOfficial("comic", 10f));

        ArrayList<ProductOfficial> foods_origin = new ArrayList<>();
        foods_origin.add(new ProductOfficial("tomato", 1.5f));
        foods_origin.add(new ProductOfficial("apple", 1.5f));
        foods_origin.add(new ProductOfficial("banana", 5.5f));
        foods_origin.add(new ProductOfficial("orange", 3f));
        foods_origin.add(new ProductOfficial("pumpkin", 8f));

        ArrayList<ProductOfficial> foods_extend = new ArrayList<>();
        foods_extend.add(new ProductOfficial("carrot", 1.5f));
        foods_extend.add(new ProductOfficial("potato", 1f));
        foods_extend.add(new ProductOfficial("cucumber", 2f));
        foods_extend.add(new ProductOfficial("onion", 3.5f));
        foods_extend.add(new ProductOfficial("watermelon", 20f));

        ArrayList<ProductOfficial> clothes_origin = new ArrayList<>();
        clothes_origin.add(new ProductOfficial("shirt", 15f));
        clothes_origin.add(new ProductOfficial("pants", 25f));
        clothes_origin.add(new ProductOfficial("shoes", 30f));
        clothes_origin.add(new ProductOfficial("hat", 10f));
        clothes_origin.add(new ProductOfficial("glasses", 15f));

        ArrayList<ProductOfficial> clothes_extend = new ArrayList<>();
        clothes_extend.add(new ProductOfficial("jacket", 40f));
        clothes_extend.add(new ProductOfficial("coat", 40f));
        clothes_extend.add(new ProductOfficial("socks", 5f));
        clothes_extend.add(new ProductOfficial("dress", 30f));
        clothes_extend.add(new ProductOfficial("boots", 35f));

        products.put("books_extend", books_extend);
        products.put("foods_origin", foods_origin);
        products.put("foods_extend", foods_extend);
        products.put("clothes_origin", clothes_origin);
        products.put("clothes_extend", clothes_extend);

        ArrayList<ProductOfficial> foods = new ArrayList<>(foods_origin);
        ArrayList<ProductOfficial> clothes = new ArrayList<>(clothes_origin);

        storeOfficials = new StoreOfficial[]{new StoreOfficial("book_store"),
                new StoreOfficial("food_store", foods, 300),
                new StoreOfficial("clothes_store", clothes, 500)};

        stores.put("book_store", storeOfficials[0]);
        stores.put("food_store", storeOfficials[1]);
        stores.put("clothes_store", storeOfficials[2]);

        customerOfficials = new CustomerOfficial[]{new CustomerOfficial("PiPi", 150),
                new CustomerOfficial("MengGe", 120),
                new CustomerOfficial("HanHan", 80),
                new CustomerOfficial("XueLao", 20)};

        customers.put("PiPi", customerOfficials[0]);
        customers.put("MengGe", customerOfficials[1]);
        customers.put("HanHan", customerOfficials[2]);
        customers.put("XueLao", customerOfficials[3]);
    }

    public void ProductSetRantingValid() {
        int[] tomatoRanting = {4, 5, 4, 3, 5};
        int[] appleRanting = {4, 5, 4, 3, 5};
        int[] bananaRanting = {3, 2, 4};
        int[] orangeRanting = {4, 2, 2};
        int[] pumpkinRanting = {5, 5, 5, 5};
        for (int j : tomatoRanting)
            products.get("foods_origin").get(0).setRating(j);
        for (int j : appleRanting)
            products.get("foods_origin").get(1).setRating(j);
        for (int j : bananaRanting)
            products.get("foods_origin").get(2).setRating(j);
        for (int j : orangeRanting)
            products.get("foods_origin").get(3).setRating(j);
        for (int j : pumpkinRanting)
            products.get("foods_origin").get(4).setRating(j);
    }

    public void ProductSetRantingInvalid() {
        int[] shirtRanting = {5, 6, 7, 8, 5};
        int[] pantsRanting = {};
        int[] shoesRanting = {-3, 0, 12};
        int[] hatRanting = {4, 0, 7, 5};
        int[] glassesRanting = {2, 3, 4, 5, 6};
        for (int j : shirtRanting)
            products.get("clothes_origin").get(0).setRating(j);
        for (int j : pantsRanting)
            products.get("clothes_origin").get(1).setRating(j);
        for (int j : shoesRanting)
            products.get("clothes_origin").get(2).setRating(j);
        for (int j : hatRanting)
            products.get("clothes_origin").get(3).setRating(j);
        for (int j : glassesRanting)
            products.get("clothes_origin").get(4).setRating(j);
    }

    public void StoreAddProduct() {
        for (ProductOfficial product : products.get("books_extend"))
            stores.get("book_store").addProduct(product);
        for (ProductOfficial product : products.get("foods_extend"))
            stores.get("food_store").addProduct(product);
        for (ProductOfficial product : products.get("clothes_extend"))
            stores.get("clothes_store").addProduct(product);
    }

    public void StoreRemoveProduct() {
        for (ProductOfficial product : products.get("foods_extend"))
            stores.get("food_store").removeProduct(product);
        for (ProductOfficial product : products.get("clothes_origin"))
            stores.get("clothes_store").removeProduct(product);
    }

    public void StoreTransactProduct(int method) {
        for (ProductOfficial product : products.get("books_extend"))
            stores.get("book_store").transact(product, method);
    }

    public void CustomerRateProductValid() {
        int[] pipi1 = {5, 5, 4, 3, 5};
        int[] pipi2 = {5, 5, 3, 2, 1};
        int[] mengge2 = {5, 4, 3, 2, 1};
        int[] hanhan1 = {3, 3, 4, 4, 5};
        int[] xuelao1 = {4, 4, 3, 2, 1};
        int[] xuelao2 = {5, 5, 5, 5, 5};
        for (int i = 0; i < stores.get("food_store").productList.size(); i++) {
            customers.get("PiPi").rateProduct(stores.get("food_store").productList.get(i), pipi1[i]);
            customers.get("PiPi").rateProduct(stores.get("food_store").productList.get(i), pipi2[i]);
            customers.get("HanHan").rateProduct(stores.get("food_store").productList.get(i), hanhan1[i]);
            customers.get("XueLao").rateProduct(stores.get("food_store").productList.get(i), xuelao1[i]);
        }
        for (int i = 0; i < stores.get("clothes_store").productList.size(); i++) {
            customers.get("MengGe").rateProduct(stores.get("clothes_store").productList.get(i), mengge2[i]);
            customers.get("XueLao").rateProduct(stores.get("clothes_store").productList.get(i), xuelao2[i]);
        }
    }

    public void CustomerRateProductInvalid() {
        int[] pipi1 = {4, 5, 0, -1, 5};
        int[] pipi2 = {5, 9, 6, 2, 1};
        int[] mengge2 = {0, 0, 0, 0, 0};
        int[] hanhan1 = {0, 99, 5, 4, 5};
        int[] xuelao1 = {20, 123, 7, -100, 1};
        int[] xuelao2 = {5, 5, 5, 5, 5};
        for (int i = 0; i < stores.get("food_store").productList.size(); i++) {
            customers.get("HanHan").rateProduct(stores.get("food_store").productList.get(i), hanhan1[i]);
            customers.get("XueLao").rateProduct(stores.get("food_store").productList.get(i), xuelao1[i]);
        }
        for (int i = 0; i < stores.get("clothes_store").productList.size(); i++) {
            customers.get("PiPi").rateProduct(stores.get("clothes_store").productList.get(i), pipi1[i]);
            customers.get("PiPi").rateProduct(stores.get("clothes_store").productList.get(i), pipi2[i]);
            customers.get("MengGe").rateProduct(stores.get("clothes_store").productList.get(i), mengge2[i]);
            customers.get("XueLao").rateProduct(stores.get("clothes_store").productList.get(i), xuelao2[i]);
        }
    }
}

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestOnlineStoreOfficial {
    static Data data = new Data();
    static Map<String, ArrayList<Product>> products = new Hashtable<>();
    static Map<String, Store> stores = new Hashtable<>();
    static Map<String, Customer> customers = new Hashtable<>();
    static Product product;

    private static Constructor<Product> productConstructor;
    private static Constructor<Store> storeConstructor1;
    private static Constructor<Store> storeConstructor2;
    private static Constructor<Customer> customerConstructor;

    public boolean productListEquals(ArrayList<ProductOfficial> a, ArrayList<Product> b) throws NoSuchFieldException, IllegalAccessException {
        Field id = Product.class.getDeclaredField("id");
        Field name = Product.class.getDeclaredField("name");
        Field price = Product.class.getDeclaredField("price");
        Field ratings = Product.class.getDeclaredField("ratings");
        id.setAccessible(true);
        name.setAccessible(true);
        price.setAccessible(true);
        ratings.setAccessible(true);

        if (a.size() != b.size()) return false;
        int cnt = 0;
        for (ProductOfficial p : a) {
            for (Product q : b) {
                if (p.id == (int) id.get(q)) {
                    if (p.name.equals(name.get(q)) && p.price == (float) price.get(q) &&
                            p.ratings.containsAll((ArrayList<Integer>) ratings.get(q)) && p.ratings.size() == ((ArrayList<Integer>) ratings.get(q)).size())
                        cnt++;
                    else
                        return false;
                }
            }
        }
        return cnt == a.size();
    }

    @Test
    public void test01() { //1
        //ProductFieldExist
        try {
            Field cnt = Product.class.getDeclaredField("cnt");
            Field id = Product.class.getDeclaredField("id");
            Field name = Product.class.getDeclaredField("name");
            Field price = Product.class.getDeclaredField("price");
            Field ratings = Product.class.getDeclaredField("ratings");

            assertTrue(Modifier.isPrivate(cnt.getModifiers()));
            assertTrue(Modifier.isStatic(cnt.getModifiers()));
            assertTrue(Modifier.isPrivate(id.getModifiers()));
            assertTrue(Modifier.isPrivate(name.getModifiers()));
            assertTrue(Modifier.isPrivate(price.getModifiers()));
            assertTrue(Modifier.isPrivate(ratings.getModifiers()));

            assertEquals(int.class, cnt.getType());
            assertEquals(int.class, id.getType());
            assertEquals(String.class, name.getType());
            assertEquals(float.class, price.getType());
            assertEquals(ArrayList.class, ratings.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("field not meet requirement");
        }
    }

    @Test
    public void test02() { //1
        //ProductMethodExist
        try {
            Method setRating = Product.class.getDeclaredMethod("setRating", int.class);
            Method getAvgRating = Product.class.getDeclaredMethod("getAvgRating");
            Method toString = Product.class.getDeclaredMethod("toString");

            assertEquals(boolean.class, setRating.getReturnType());
            assertEquals(float.class, getAvgRating.getReturnType());
            assertEquals(String.class, toString.getReturnType());

            assertTrue(Modifier.isPublic(setRating.getModifiers()));
            assertTrue(Modifier.isPublic(getAvgRating.getModifiers()));
            assertTrue(Modifier.isPublic(toString.getModifiers()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("method not meet requirement");
        }
    }


    @Test
    public void test03() { //2
        //ProductConstructor
        Class<?>[] parameters = {String.class, float.class};
        try {
            productConstructor = Product.class.getConstructor(parameters);

            ArrayList<Product> books_extend = new ArrayList<>();
            books_extend.add(productConstructor.newInstance("magazine", 5.5f));
            books_extend.add(productConstructor.newInstance("textbook", 15f));
            books_extend.add(productConstructor.newInstance("dictionary", 30f));
            books_extend.add(productConstructor.newInstance("novel", 20f));
            books_extend.add(productConstructor.newInstance("comic", 10f));

            ArrayList<Product> foods_origin = new ArrayList<>();
            foods_origin.add(productConstructor.newInstance("tomato", 1.5f));
            foods_origin.add(productConstructor.newInstance("apple", 1.5f));
            foods_origin.add(productConstructor.newInstance("banana", 5.5f));
            foods_origin.add(productConstructor.newInstance("orange", 3f));
            foods_origin.add(productConstructor.newInstance("pumpkin", 8f));

            ArrayList<Product> foods_extend = new ArrayList<>();
            foods_extend.add(productConstructor.newInstance("carrot", 1.5f));
            foods_extend.add(productConstructor.newInstance("potato", 1f));
            foods_extend.add(productConstructor.newInstance("cucumber", 2f));
            foods_extend.add(productConstructor.newInstance("onion", 3.5f));
            foods_extend.add(productConstructor.newInstance("watermelon", 20f));

            ArrayList<Product> clothes_origin = new ArrayList<>();
            clothes_origin.add(productConstructor.newInstance("shirt", 15f));
            clothes_origin.add(productConstructor.newInstance("pants", 25f));
            clothes_origin.add(productConstructor.newInstance("shoes", 30f));
            clothes_origin.add(productConstructor.newInstance("hat", 10f));
            clothes_origin.add(productConstructor.newInstance("glasses", 15f));

            ArrayList<Product> clothes_extend = new ArrayList<>();
            clothes_extend.add(productConstructor.newInstance("jacket", 40f));
            clothes_extend.add(productConstructor.newInstance("coat", 40f));
            clothes_extend.add(productConstructor.newInstance("socks", 5f));
            clothes_extend.add(productConstructor.newInstance("dress", 30f));
            clothes_extend.add(productConstructor.newInstance("boots", 35f));

            Field id = Product.class.getDeclaredField("id");
            Field name = Product.class.getDeclaredField("name");
            Field price = Product.class.getDeclaredField("price");
            Field ratings = Product.class.getDeclaredField("ratings");
            id.setAccessible(true);
            name.setAccessible(true);
            price.setAccessible(true);
            ratings.setAccessible(true);

            products.put("books_extend", books_extend);
            products.put("foods_origin", foods_origin);
            products.put("foods_extend", foods_extend);
            products.put("clothes_origin", clothes_origin);
            products.put("clothes_extend", clothes_extend);

            product = productConstructor.newInstance("0rating", 5.5f);
            for (String key : products.keySet()) {
                for (int i = 0; i < products.get(key).size(); i++) {
                    assertEquals(data.products.get(key).get(i).id, id.get(products.get(key).get(i)));
                    assertEquals(data.products.get(key).get(i).name, name.get(products.get(key).get(i)));
                    assertEquals(data.products.get(key).get(i).price, price.get(products.get(key).get(i)));
                    assertEquals(data.products.get(key).get(i).ratings, ratings.get(products.get(key).get(i)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("constructor not meet requirement");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong member variable in Product class");
        }
    }

    @Test
    public void test04() { //1.5
        //ProductSetRating(valid)
        try {
            Method setRating = Product.class.getDeclaredMethod("setRating", int.class);
            Field ratings = Product.class.getDeclaredField("ratings");
            ratings.setAccessible(true);

            int[] tomatoRanting = {4, 5, 4, 3, 5};
            int[] appleRanting = {4, 5, 4, 3, 5};
            int[] bananaRanting = {3, 2, 4};
            int[] orangeRanting = {4, 2, 2};
            int[] pumpkinRanting = {5, 5, 5, 5};

            for (int j : tomatoRanting) { //4, 5, 4, 3, 5
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("foods_origin").get(0), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("foods_origin").get(0), j));
            }
            for (int j : appleRanting) { //5
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("foods_origin").get(1), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("foods_origin").get(1), j));
            }
            for (int j : bananaRanting) { //3, 2, 4
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("foods_origin").get(2), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("foods_origin").get(2), j));
            }
            for (int j : orangeRanting) { //4, 2, 2
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("foods_origin").get(3), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("foods_origin").get(3), j));
            }
            for (int j : pumpkinRanting) { //5, 5, 5, 5
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("foods_origin").get(4), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("foods_origin").get(4), j));
            }

            data.ProductSetRantingValid();

            for (int i = 0; i < products.get("foods_origin").size(); i++) {
                assertTrue(data.products.get("foods_origin").get(i).ratings.containsAll((Collection<?>) ratings.get(products.get("foods_origin").get(i))));
                assertEquals(data.products.get("foods_origin").get(i).ratings.size(), ((ArrayList) ratings.get(products.get("foods_origin").get(i))).size());
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("setRating error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong ratings");
        }
    }

    @Test
    public void test05() { //1.5
        //ProductSetRating(invalid)
        try {
            Method setRating = Product.class.getDeclaredMethod("setRating", int.class);
            Field ratings = Product.class.getDeclaredField("ratings");
            ratings.setAccessible(true);

            int[] shirtRanting = {5, 6, 7, 8, 5};
            int[] pantsRanting = {};
            int[] shoesRanting = {-3, 0, 12};
            int[] hatRanting = {4, 0, 7, 5};
            int[] glassesRanting = {2, 3, 4, 5, 6};

            for (int j : shirtRanting) { // 5, 5
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("clothes_origin").get(0), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("clothes_origin").get(0), j));
            }
            for (int j : pantsRanting) { //
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("clothes_origin").get(1), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("clothes_origin").get(1), j));
            }
            for (int j : shoesRanting) { //
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("clothes_origin").get(2), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("clothes_origin").get(2), j));
            }
            for (int j : hatRanting) { //4, 5
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("clothes_origin").get(3), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("clothes_origin").get(3), j));
            }
            for (int j : glassesRanting) { //2, 3, 4, 5
                if (j > 5 || j < 1)
                    assertFalse((Boolean) setRating.invoke(products.get("clothes_origin").get(4), j));
                else
                    assertTrue((Boolean) setRating.invoke(products.get("clothes_origin").get(4), j));
            }

            data.ProductSetRantingInvalid();

            for (int i = 0; i < products.get("clothes_origin").size(); i++) {
                assertTrue(data.products.get("clothes_origin").get(i).ratings.containsAll((Collection<?>) ratings.get(products.get("clothes_origin").get(i))));
                assertEquals(data.products.get("clothes_origin").get(i).ratings.size(), ((ArrayList) ratings.get(products.get("clothes_origin").get(i))).size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("setRating error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong ratings");
        }
    }

    @Test
    public void test06() { //1.5
        //ProductAvgRating(size not zero)
        try {
            Method getAvgRating = Product.class.getDeclaredMethod("getAvgRating");
            for (int i = 0; i < products.get("foods_origin").size(); i++) //4.2, 5, 3, 2.67, 5
                assertEquals(data.products.get("foods_origin").get(i).getAvgRating(), getAvgRating.invoke(products.get("foods_origin").get(i)));
        } catch (Exception e) {
            e.printStackTrace();
            fail("getAvgRating error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong average rating");
        }
    }

    @Test
    public void test07() { //1.5
        //ProductAvgRating(size zero)
        try {
            Method getAvgRating = Product.class.getDeclaredMethod("getAvgRating");
            for (int i = 0; i < products.get("clothes_origin").size(); i++) //5, 0, 0, 4.5, 3.5
                assertEquals(data.products.get("clothes_origin").get(i).getAvgRating(), getAvgRating.invoke(products.get("clothes_origin").get(i)));
        } catch (Exception e) {
            e.printStackTrace();
            fail("getAvgRating error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong average rating");
        }
    }

    @Test
    public void test08() { //5
        // ProductToString
        try {
            Method toString = Product.class.getDeclaredMethod("toString");
            for (int i = 0; i < products.get("foods_origin").size(); i++) {
                String pre = (String) toString.invoke(products.get("foods_origin").get(i));
                String ans = data.products.get("foods_origin").get(i).toString();
                assertEquals(ans, pre);
            }
            for (int i = 0; i < products.get("clothes_origin").size(); i++) {
                String pre = (String) toString.invoke(products.get("clothes_origin").get(i));
                String ans = data.products.get("clothes_origin").get(i).toString();
                assertEquals(ans, pre);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("toString error");
        } catch (AssertionError e) {
            fail("wrong toString");
        }
    }

    // class Product: 15

    @Test
    public void test09() { //1
        //StoreFieldExist
        try {
            Field cnt = Store.class.getDeclaredField("cnt");
            Field id = Store.class.getDeclaredField("id");
            Field name = Store.class.getDeclaredField("name");
            Field productList = Store.class.getDeclaredField("productList");
            Field income = Store.class.getDeclaredField("income");

            assertTrue(Modifier.isStatic(cnt.getModifiers()));
            assertTrue(Modifier.isPrivate(cnt.getModifiers()));
            assertTrue(Modifier.isPrivate(id.getModifiers()));
            assertTrue(Modifier.isPrivate(name.getModifiers()));
            assertTrue(Modifier.isPrivate(productList.getModifiers()));
            assertTrue(Modifier.isPrivate(income.getModifiers()));

            assertEquals(int.class, cnt.getType());
            assertEquals(int.class, id.getType());
            assertEquals(String.class, name.getType());
            assertEquals(ArrayList.class, productList.getType());
            assertEquals(float.class, income.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("field not meet requirement");
        }
    }

    @Test
    public void test10() { //1
        //StoreMethodExist
        try {
            Method addProduct = Store.class.getDeclaredMethod("addProduct", Product.class);
            Method removeProduct = Store.class.getDeclaredMethod("removeProduct", Product.class);
            Method getProductList = Store.class.getDeclaredMethod("getProductList");
            Method hasProduct = Store.class.getDeclaredMethod("hasProduct", Product.class);
            Method transact = Store.class.getDeclaredMethod("transact", Product.class, int.class);

            assertEquals(boolean.class, addProduct.getReturnType());
            assertEquals(boolean.class, removeProduct.getReturnType());
            assertEquals(ArrayList.class, getProductList.getReturnType());
            assertEquals(boolean.class, hasProduct.getReturnType());
            assertEquals(void.class, transact.getReturnType());

            assertTrue(Modifier.isPublic(addProduct.getModifiers()));
            assertTrue(Modifier.isPublic(removeProduct.getModifiers()));
            assertTrue(Modifier.isPublic(getProductList.getModifiers()));
            assertTrue(Modifier.isPublic(hasProduct.getModifiers()));
            assertTrue(Modifier.isPublic(transact.getModifiers()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("method not meet requirement");
        }
    }

    @Test
    public void test11() { //1.5
        //StoreConstructor(1)
        Class<?>[] parameters1 = {String.class};
        try {
            storeConstructor1 = Store.class.getConstructor(parameters1);

            Store book_store = storeConstructor1.newInstance("book_store");

            Field id = Store.class.getDeclaredField("id");
            Field name = Store.class.getDeclaredField("name");
            Field productList = Store.class.getDeclaredField("productList");
            Field income = Store.class.getDeclaredField("income");
            id.setAccessible(true);
            name.setAccessible(true);
            productList.setAccessible(true);
            income.setAccessible(true);

            stores.put("book_store", book_store);

            assertEquals(data.stores.get("book_store").id, id.get(stores.get("book_store")));
            assertEquals(data.stores.get("book_store").name, name.get(stores.get("book_store")));
            assertEquals(data.stores.get("book_store").productList, productList.get(stores.get("book_store")));
            assertEquals(data.stores.get("book_store").income, income.get(stores.get("book_store")));
        } catch (Exception e) {
            e.printStackTrace();
            fail("constructor not meet requirement");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong member variable in Store class");
        }
    }

    @Test
    public void test12() { //2
        //StoreConstructor(2)
        Class<?>[] parameters2 = {String.class, ArrayList.class, float.class};
        try {
            storeConstructor2 = Store.class.getConstructor(parameters2);

            ArrayList<Product> foods = new ArrayList<>(products.get("foods_origin"));
            ArrayList<Product> clothes = new ArrayList<>(products.get("clothes_origin"));
            Store food_store = storeConstructor2.newInstance("food_store", foods, 300);
            Store clothes_store = storeConstructor2.newInstance("clothes_store", clothes, 500);

            Field id = Store.class.getDeclaredField("id");
            Field name = Store.class.getDeclaredField("name");
            Field productList = Store.class.getDeclaredField("productList");
            Field income = Store.class.getDeclaredField("income");
            id.setAccessible(true);
            name.setAccessible(true);
            productList.setAccessible(true);
            income.setAccessible(true);

            stores.put("food_store", food_store);
            stores.put("clothes_store", clothes_store);

            assertEquals(data.stores.get("food_store").id, id.get(stores.get("food_store")));
            assertEquals(data.stores.get("food_store").name, name.get(stores.get("food_store")));
            assertTrue(productListEquals(data.stores.get("food_store").productList, (ArrayList<Product>) productList.get(stores.get("food_store"))));
            assertEquals(data.stores.get("food_store").income, income.get(stores.get("food_store")));
            assertEquals(data.stores.get("clothes_store").id, id.get(stores.get("clothes_store")));
            assertEquals(data.stores.get("clothes_store").name, name.get(stores.get("clothes_store")));
            assertTrue(productListEquals(data.stores.get("clothes_store").productList, (ArrayList<Product>) productList.get(stores.get("clothes_store"))));
            assertEquals(data.stores.get("clothes_store").income, income.get(stores.get("clothes_store")));
        } catch (Exception e) {
            e.printStackTrace();
            fail("constructor not meet requirement");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong member variable in Store class");
        }
    }

    @Test
    public void test13() { //1.5
        //StoreAddProduct(valid)
        try {
            Method addProduct = Store.class.getDeclaredMethod("addProduct", Product.class);
            Field productList = Store.class.getDeclaredField("productList");
            productList.setAccessible(true);

            for (Product product : products.get("books_extend"))
                assertTrue((Boolean) addProduct.invoke(stores.get("book_store"), product));
            for (Product product : products.get("foods_extend"))
                assertTrue((Boolean) addProduct.invoke(stores.get("food_store"), product));
            for (Product product : products.get("clothes_extend"))
                assertTrue((Boolean) addProduct.invoke(stores.get("clothes_store"), product));

            data.StoreAddProduct();

            for (String key : stores.keySet())
                for (int i = 0; i < data.stores.get(key).productList.size(); i++)
                    assertTrue(productListEquals(data.stores.get(key).productList, (ArrayList<Product>) productList.get(stores.get(key))));
        } catch (Exception e) {
            e.printStackTrace();
            fail("addProduct error");
        } catch (AssertionError e) {
            fail("wrong productList");
        }
    }

    @Test
    public void test14() { //1.5
        //StoreAddProduct(invalid)
        try {
            Method addProduct = Store.class.getDeclaredMethod("addProduct", Product.class);
            Field productList = Store.class.getDeclaredField("productList");
            productList.setAccessible(true);

            for (Product product : products.get("books_extend"))
                assertFalse((Boolean) addProduct.invoke(stores.get("book_store"), product));
            for (Product product : products.get("foods_extend"))
                assertFalse((Boolean) addProduct.invoke(stores.get("food_store"), product));
            for (Product product : products.get("clothes_extend"))
                assertFalse((Boolean) addProduct.invoke(stores.get("clothes_store"), product));

            data.StoreAddProduct();

            for (String key : stores.keySet())
                for (int i = 0; i < data.stores.get(key).productList.size(); i++)
                    assertTrue(productListEquals(data.stores.get(key).productList, (ArrayList<Product>) productList.get(stores.get(key))));
        } catch (Exception e) {
            e.printStackTrace();
            fail("addProduct error");
        } catch (AssertionError e) {
            fail("wrong productList");
        }
    }

    @Test
    public void test15() { //1.5
        //StoreRemoveProduct(valid)
        try {
            Method removeProduct = Store.class.getDeclaredMethod("removeProduct", Product.class);
            Field productList = Store.class.getDeclaredField("productList");
            productList.setAccessible(true);

            for (Product product : products.get("foods_extend"))
                assertTrue((Boolean) removeProduct.invoke(stores.get("food_store"), product));
            for (Product product : products.get("clothes_origin"))
                assertTrue((Boolean) removeProduct.invoke(stores.get("clothes_store"), product));

            data.StoreRemoveProduct();

            for (String key : stores.keySet())
                for (int i = 0; i < data.stores.get(key).productList.size(); i++)
                    assertTrue(productListEquals(data.stores.get(key).productList, (ArrayList<Product>) productList.get(stores.get(key))));
        } catch (Exception e) {
            e.printStackTrace();
            fail("removeProduct error");
        } catch (AssertionError e) {
            fail("wrong productList");
        }
    }

    @Test
    public void test16() { //1.5
        //StoreRemoveProduct(invalid)
        try {
            Method removeProduct = Store.class.getDeclaredMethod("removeProduct", Product.class);
            Field productList = Store.class.getDeclaredField("productList");
            productList.setAccessible(true);

            for (Product product : products.get("foods_extend"))
                assertFalse((Boolean) removeProduct.invoke(stores.get("food_store"), product));
            for (Product product : products.get("clothes_origin"))
                assertFalse((Boolean) removeProduct.invoke(stores.get("clothes_store"), product));

            data.StoreRemoveProduct();

            for (String key : stores.keySet())
                for (int i = 0; i < data.stores.get(key).productList.size(); i++)
                    assertTrue(productListEquals(data.stores.get(key).productList, (ArrayList<Product>) productList.get(stores.get(key))));
        } catch (Exception e) {
            e.printStackTrace();
            fail("removeProduct error");
        } catch (AssertionError e) {
            fail("wrong productList");
        }
    }

    @Test
    public void test17() { //1.5
        //StoreGetProductList
        try {
            Method getProductList = Store.class.getDeclaredMethod("getProductList");
            Field productList = Store.class.getDeclaredField("productList");
            productList.setAccessible(true);

            for (String key : stores.keySet())
                assertTrue(productListEquals(data.stores.get(key).getProductList(), (ArrayList<Product>) getProductList.invoke(stores.get(key))));
        } catch (Exception e) {
            e.printStackTrace();
            fail("getProductList error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong productList");
        }
    }

    @Test
    public void test18() { //1.5
        //StoreHasProduct(true)
        try {
            Method hasProduct = Store.class.getDeclaredMethod("hasProduct", Product.class);
            for (Product product : products.get("books_extend")) //
                assertTrue((Boolean) hasProduct.invoke(stores.get("book_store"), product));
            for (Product product : products.get("foods_origin")) //
                assertTrue((Boolean) hasProduct.invoke(stores.get("food_store"), product));
            for (Product product : products.get("clothes_extend")) //
                assertTrue((Boolean) hasProduct.invoke(stores.get("clothes_store"), product));
        } catch (Exception e) {
            e.printStackTrace();
            fail("hasProduct error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong productList");
        }
    }

    @Test
    public void test19() { //1.5
        //StoreHasProduct(false)
        try {
            Method hasProduct = Store.class.getDeclaredMethod("hasProduct", Product.class);
            for (Product product : products.get("foods_extend"))
                assertFalse((Boolean) hasProduct.invoke(stores.get("food_store"), product));
            for (Product product : products.get("clothes_origin"))
                assertFalse((Boolean) hasProduct.invoke(stores.get("clothes_store"), product));
        } catch (Exception e) {
            e.printStackTrace();
            fail("hasProduct error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong productList");
        }
    }

    @Test
    public void test20() { //4
        //StorePurchase
        try {
            Method transact = Store.class.getDeclaredMethod("transact", Product.class, int.class);
            Field productList = Store.class.getDeclaredField("productList");
            Field income = Store.class.getDeclaredField("income");
            productList.setAccessible(true);
            income.setAccessible(true);

            for (Product product : products.get("books_extend"))
                transact.invoke(stores.get("book_store"), product, 0);

            data.StoreTransactProduct(0);

            for (String key : stores.keySet()) {
                assertTrue(productListEquals(data.stores.get(key).productList, (ArrayList<Product>) productList.get(stores.get(key))));
                assertEquals(data.stores.get(key).income, (float) income.get(stores.get(key)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("transact error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong purchase");
        }
    }

    @Test
    public void test21() { //bonus//1
        //StoreRefund
        try {
            Method transact = Store.class.getDeclaredMethod("transact", Product.class, int.class);
            Field productList = Store.class.getDeclaredField("productList");
            Field income = Store.class.getDeclaredField("income");
            productList.setAccessible(true);
            income.setAccessible(true);

            for (Product product : products.get("books_extend"))
                transact.invoke(stores.get("book_store"), product, 1);

            data.StoreTransactProduct(1);

            for (String key : stores.keySet()) {
                assertTrue(productListEquals(data.stores.get(key).productList, (ArrayList<Product>) productList.get(stores.get(key))));
                assertEquals(data.stores.get(key).income, (float) income.get(stores.get(key)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("transact error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("Bonus: wrong refund");
        }
    }

    //class Store: 20 + 1

    @Test
    public void test22() { //1
        //CustomerFieldExist
        try {
            Field cnt = Customer.class.getDeclaredField("cnt");
            Field id = Customer.class.getDeclaredField("id");
            Field name = Customer.class.getDeclaredField("name");
            Field shoppingCart = Customer.class.getDeclaredField("shoppingCart");
            Field wallet = Customer.class.getDeclaredField("wallet");

            assertTrue(Modifier.isStatic(cnt.getModifiers()));
            assertTrue(Modifier.isPrivate(cnt.getModifiers()));
            assertTrue(Modifier.isPrivate(id.getModifiers()));
            assertTrue(Modifier.isPrivate(name.getModifiers()));
            assertTrue(Modifier.isPrivate(shoppingCart.getModifiers()));
            assertTrue(Modifier.isPrivate(wallet.getModifiers()));

            assertEquals(int.class, cnt.getType());
            assertEquals(int.class, id.getType());
            assertEquals(String.class, name.getType());
            assertEquals(ArrayList.class, shoppingCart.getType());
            assertEquals(float.class, wallet.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("field not meet requirement");
        }
    }

    @Test
    public void test23() { //1
        //CustomerMethodExist
        try {
            Method rateProduct = Customer.class.getDeclaredMethod("rateProduct", Product.class, int.class);
            Method purchaseProduct = Customer.class.getDeclaredMethod("purchaseProduct", Store.class, Product.class);
            Method updateWallet = Customer.class.getDeclaredMethod("updateWallet", float.class);
            Method viewShoppingCart = Customer.class.getDeclaredMethod("viewShoppingCart", SortBy.class);

            assertEquals(boolean.class, rateProduct.getReturnType());
            assertEquals(boolean.class, purchaseProduct.getReturnType());
            assertEquals(void.class, updateWallet.getReturnType());
            assertEquals(void.class, viewShoppingCart.getReturnType());

            assertTrue(Modifier.isPublic(rateProduct.getModifiers()));
            assertTrue(Modifier.isPublic(purchaseProduct.getModifiers()));
            assertTrue(Modifier.isPublic(updateWallet.getModifiers()));
            assertTrue(Modifier.isPublic(viewShoppingCart.getModifiers()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("method not meet requirement");
        }
    }

    @Test
    public void test24() { //2
        //CustomerConstructorExist
        Class<?>[] parameters = {String.class, float.class};
        try {
            customerConstructor = Customer.class.getConstructor(parameters);

            Customer pipi = customerConstructor.newInstance("PiPi", 150);
            Customer mengge = customerConstructor.newInstance("MengGe", 120);
            Customer hanhan = customerConstructor.newInstance("HanHan", 80);
            Customer xuelao = customerConstructor.newInstance("XueLao", 20);

            Field id = Customer.class.getDeclaredField("id");
            Field name = Customer.class.getDeclaredField("name");
            Field shoppingCart = Customer.class.getDeclaredField("shoppingCart");
            Field wallet = Customer.class.getDeclaredField("wallet");
            id.setAccessible(true);
            name.setAccessible(true);
            shoppingCart.setAccessible(true);
            wallet.setAccessible(true);

            customers.put("PiPi", pipi);
            customers.put("MengGe", mengge);
            customers.put("HanHan", hanhan);
            customers.put("XueLao", xuelao);

            for (String key : customers.keySet()) {
                assertEquals(data.customers.get(key).id, id.get(customers.get(key)));
                assertEquals(data.customers.get(key).name, name.get(customers.get(key)));
                assertEquals(data.customers.get(key).shoppingCart, shoppingCart.get(customers.get(key)));
                assertEquals(data.customers.get(key).wallet, wallet.get(customers.get(key)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("constructor not meet requirement");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong member variable in Customer class");
        }
    }

    @Test
    public void test25() { //1.5
        //CustomerRateProduct(valid)
        try {
            Method rateProduct = Customer.class.getDeclaredMethod("rateProduct", Product.class, int.class);
            Field productList = Store.class.getDeclaredField("productList");
            Field ratings = Product.class.getDeclaredField("ratings");
            productList.setAccessible(true);
            ratings.setAccessible(true);

            int[] pipi1 = {5, 5, 4, 3, 5};
            int[] pipi2 = {5, 5, 3, 2, 1};
            int[] mengge2 = {5, 4, 3, 2, 1};
            int[] hanhan1 = {3, 3, 4, 4, 5};
            int[] xuelao1 = {4, 4, 3, 2, 1};
            int[] xuelao2 = {5, 5, 5, 5, 5};

            for (int i = 0; i < ((ArrayList) productList.get(stores.get("food_store"))).size(); i++) {
                rateProduct.invoke(customers.get("PiPi"), ((ArrayList<Product>) productList.get(stores.get("food_store"))).get(i), pipi1[i]);
                rateProduct.invoke(customers.get("PiPi"), ((ArrayList<Product>) productList.get(stores.get("food_store"))).get(i), pipi2[i]);
                rateProduct.invoke(customers.get("HanHan"), ((ArrayList<Product>) productList.get(stores.get("food_store"))).get(i), hanhan1[i]);
                rateProduct.invoke(customers.get("XueLao"), ((ArrayList<Product>) productList.get(stores.get("food_store"))).get(i), xuelao1[i]);
            }
            for (int i = 0; i < ((ArrayList) productList.get(stores.get("clothes_store"))).size(); i++) {
                rateProduct.invoke(customers.get("MengGe"), ((ArrayList<Product>) productList.get(stores.get("clothes_store"))).get(i), mengge2[i]);
                rateProduct.invoke(customers.get("XueLao"), ((ArrayList<Product>) productList.get(stores.get("clothes_store"))).get(i), xuelao2[i]);
            }
            data.CustomerRateProductValid();

            for (String key : products.keySet()) {
                for (int i = 0; i < products.get(key).size(); i++) {

                    assertTrue(data.products.get(key).get(i).ratings.containsAll((Collection<?>) ratings.get(products.get(key).get(i))));
                    assertEquals(data.products.get(key).get(i).ratings.size(), ((ArrayList) ratings.get(products.get(key).get(i))).size());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("rateProduct error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong ratings");
        }
    }

    @Test
    public void test26() { //1.5
        //CustomerRateProduct(invalid)
        try {
            Method rateProduct = Customer.class.getDeclaredMethod("rateProduct", Product.class, int.class);
            Field productList = Store.class.getDeclaredField("productList");
            Field ratings = Product.class.getDeclaredField("ratings");
            productList.setAccessible(true);
            ratings.setAccessible(true);
            ratings.setAccessible(true);

            int[] pipi1 = {4, 5, 0, -1, 5};
            int[] pipi2 = {5, 9, 6, 2, 1};
            int[] mengge2 = {0, 0, 0, 0, 0};
            int[] hanhan1 = {0, 99, 5, 4, 5};
            int[] xuelao1 = {20, 123, 7, -100, 1};
            int[] xuelao2 = {5, 5, 5, 5, 5};

            for (int i = 0; i < ((ArrayList) productList.get(stores.get("food_store"))).size(); i++) {
                rateProduct.invoke(customers.get("HanHan"), ((ArrayList<Product>) productList.get(stores.get("food_store"))).get(i), hanhan1[i]);
                rateProduct.invoke(customers.get("XueLao"), ((ArrayList<Product>) productList.get(stores.get("food_store"))).get(i), xuelao1[i]);
            }

            for (int i = 0; i < ((ArrayList) productList.get(stores.get("clothes_store"))).size(); i++) {
                rateProduct.invoke(customers.get("PiPi"), ((ArrayList<Product>) productList.get(stores.get("clothes_store"))).get(i), pipi1[i]);
                rateProduct.invoke(customers.get("PiPi"), ((ArrayList<Product>) productList.get(stores.get("clothes_store"))).get(i), pipi2[i]);
                rateProduct.invoke(customers.get("MengGe"), ((ArrayList<Product>) productList.get(stores.get("clothes_store"))).get(i), mengge2[i]);
                rateProduct.invoke(customers.get("XueLao"), ((ArrayList<Product>) productList.get(stores.get("clothes_store"))).get(i), xuelao2[i]);
            }

            data.CustomerRateProductInvalid();

            for (String key : products.keySet()) {
                for (int i = 0; i < products.get(key).size(); i++) {

                    assertTrue(data.products.get(key).get(i).ratings.containsAll((Collection<?>) ratings.get(products.get(key).get(i))));
                    assertEquals(data.products.get(key).get(i).ratings.size(), ((ArrayList) ratings.get(products.get(key).get(i))).size());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("rateProduct error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong ratings");
        }
    }

    @Test
    public void test27() { //3
        //CustomerPurchaseProduct(valid)
        try {
            Method purchaseProduct = Customer.class.getDeclaredMethod("purchaseProduct", Store.class, Product.class);
            Field shoppingCart = Customer.class.getDeclaredField("shoppingCart");
            Field wallet = Customer.class.getDeclaredField("wallet");
            Field productList = Store.class.getDeclaredField("productList");
            Field income = Store.class.getDeclaredField("income");
            wallet.setAccessible(true);
            shoppingCart.setAccessible(true);
            productList.setAccessible(true);
            income.setAccessible(true);

            for (int i = 0; i < 2; i++) {
                assertEquals(data.customers.get("PiPi").purchaseProduct(data.stores.get("clothes_store"), data.products.get("clothes_extend").get(i)),
                        purchaseProduct.invoke(customers.get("PiPi"), stores.get("clothes_store"), products.get("clothes_extend").get(i)));
                assertEquals(data.customers.get("PiPi").purchaseProduct(data.stores.get("food_store"), data.products.get("foods_origin").get(i)),
                        purchaseProduct.invoke(customers.get("PiPi"), stores.get("food_store"), products.get("foods_origin").get(i)));
            }
            for (int i = 3; i < 5; i++) {
                assertEquals(data.customers.get("HanHan").purchaseProduct(data.stores.get("food_store"), data.products.get("foods_origin").get(i)),
                        purchaseProduct.invoke(customers.get("HanHan"), stores.get("food_store"), products.get("foods_origin").get(i)));
            }
            for (String key : data.customers.keySet()) {
                assertTrue(productListEquals(data.customers.get(key).shoppingCart, (ArrayList<Product>) shoppingCart.get(customers.get(key))));
                assertEquals(data.customers.get(key).wallet, wallet.get(customers.get(key)));
            }
            for (String key : new String[]{"food_store", "clothes_store"}) {
                assertTrue(productListEquals(data.stores.get(key).productList, (ArrayList<Product>) productList.get(stores.get(key))));
                assertEquals(data.stores.get(key).income, income.get(stores.get(key)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("purchaseProduct error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong purchase");
        }
    }

    @Test
    public void test28() { //3
        //CustomerPurchaseProduct(invalid)
        try {
            Method purchaseProduct = Customer.class.getDeclaredMethod("purchaseProduct", Store.class, Product.class);
            Field shoppingCart = Customer.class.getDeclaredField("shoppingCart");
            Field wallet = Customer.class.getDeclaredField("wallet");
            Field productList = Store.class.getDeclaredField("productList");
            Field income = Store.class.getDeclaredField("income");
            shoppingCart.setAccessible(true);
            wallet.setAccessible(true);
            productList.setAccessible(true);
            income.setAccessible(true);

            for (int i = 0; i < 5; i++) {
                assertEquals(data.customers.get("XueLao").purchaseProduct(data.stores.get("clothes_store"), data.products.get("foods_origin").get(i)),
                        purchaseProduct.invoke(customers.get("XueLao"), stores.get("clothes_store"), products.get("foods_origin").get(i)));
                assertEquals(data.customers.get("XueLao").purchaseProduct(data.stores.get("clothes_store"), data.products.get("clothes_origin").get(i)),
                        purchaseProduct.invoke(customers.get("XueLao"), stores.get("clothes_store"), products.get("clothes_origin").get(i)));
            }//false
            for (int i = 0; i < 5; i++) {
                assertEquals(data.customers.get("XueLao").purchaseProduct(data.stores.get("clothes_store"), data.products.get("clothes_extend").get(i)),
                        purchaseProduct.invoke(customers.get("XueLao"), stores.get("clothes_store"), products.get("clothes_extend").get(i)));
            }
            for (String key : data.customers.keySet()) {
                assertTrue(productListEquals(data.customers.get(key).shoppingCart, (ArrayList<Product>) shoppingCart.get(customers.get(key))));
                assertEquals(data.customers.get(key).wallet, wallet.get(customers.get(key)));
            }
            for (String key : new String[]{"food_store", "clothes_store"}) {
                assertTrue(productListEquals(data.stores.get(key).productList, (ArrayList<Product>) productList.get(stores.get(key))));
                assertEquals(data.stores.get(key).income, income.get(stores.get(key)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("purchaseProduct error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong purchase");
        }
    }

    @Test
    public void test29() { //3
        //updateWallet
        try {
            Method updateWallet = Customer.class.getDeclaredMethod("updateWallet", float.class);
            Field wallet = Customer.class.getDeclaredField("wallet");
            wallet.setAccessible(true);

            for (String key : data.customers.keySet()) {
                data.customers.get(key).updateWallet(10.5f);
                updateWallet.invoke(customers.get(key), 10.5f);
                assertEquals(data.customers.get(key).wallet, wallet.get(customers.get(key)));
                //pipi 76.5, mengge 130.5, hanhan 79.5, xuelao 25.5
                data.customers.get(key).updateWallet(-5.5f);
                updateWallet.invoke(customers.get(key), -5.5f);
                assertEquals(data.customers.get(key).wallet, wallet.get(customers.get(key)));
                //pipi 71, mengge 125, hanhan 74, xuelao 20
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("updateWallet error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("wrong wallet");
        }
    }

    @Test
    public void test30() { //3
        //viewShoppingCart(purchaseTime)
        try {
            Method viewShoppingCart = Customer.class.getDeclaredMethod("viewShoppingCart", SortBy.class);
            viewShoppingCart.setAccessible(true);
            ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
            ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent1));
            Field shoppingCart = Customer.class.getDeclaredField("shoppingCart");
            shoppingCart.setAccessible(true);
            ((ArrayList)shoppingCart.get(customers.get("PiPi"))).add(product);
            data.customers.get("PiPi").shoppingCart.add(new ProductOfficial("0rating",5.5f));

            data.customers.get("PiPi").viewShoppingCart(SortByOfficial.PurchaseTime);
            System.setOut(new PrintStream(outContent2));
            viewShoppingCart.invoke(customers.get("PiPi"), SortBy.PurchaseTime);
            assertEquals(outContent1.toString(), outContent2.toString());
            System.setOut(System.out);
        } catch (Exception e) {
            e.printStackTrace();
            fail("viewShoppingCart error");
        } catch (AssertionError e) {
            fail("wrong shoppingCart");
        }
    }

    @Test
    public void test31() { //3
        //viewShoppingCart(rating)
        try {
            Method viewShoppingCart = Customer.class.getDeclaredMethod("viewShoppingCart", SortBy.class);
            viewShoppingCart.setAccessible(true);
            ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
            ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent1));
            data.customers.get("PiPi").viewShoppingCart(SortByOfficial.Rating);
            System.setOut(new PrintStream(outContent2));
            viewShoppingCart.invoke(customers.get("PiPi"), SortBy.Rating);
            assertEquals(outContent1.toString(), outContent2.toString());
            System.setOut(System.out);
        } catch (Exception e) {
            e.printStackTrace();
            fail("viewShoppingCart error");
        } catch (AssertionError e) {
            fail("wrong shoppingCart");
        }
    }

    @Test
    public void test32() { //3
        //viewShoppingCart(price)
        try {
            Method viewShoppingCart = Customer.class.getDeclaredMethod("viewShoppingCart", SortBy.class);
            ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
            ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent1));
            data.customers.get("PiPi").viewShoppingCart(SortByOfficial.Price);
            System.setOut(new PrintStream(outContent2));
            viewShoppingCart.invoke(customers.get("PiPi"), SortBy.Price);
            assertEquals(outContent1.toString(), outContent2.toString());
            System.setOut(System.out);
        } catch (Exception e) {
            e.printStackTrace();
            fail("viewShoppingCart error");
        } catch (AssertionError e) {
            fail("wrong shoppingCart");
        }
    }

    @Test
    public void test33() { //bonus//4
        //refundProduct
        try {
            Method refundProduct = Customer.class.getDeclaredMethod("refundProduct", Product.class);
            Field shoppingCart = Customer.class.getDeclaredField("shoppingCart");
            Field wallet = Customer.class.getDeclaredField("wallet");
            Field productList = Store.class.getDeclaredField("productList");
            Field income = Store.class.getDeclaredField("income");
            Field price = Product.class.getDeclaredField("price");
            shoppingCart.setAccessible(true);
            wallet.setAccessible(true);
            productList.setAccessible(true);
            income.setAccessible(true);
            price.setAccessible(true);

            for (String key : data.customers.keySet()) {
                for (int i = 0; i < data.products.get("foods_origin").size(); i++) {
                    CustomerOfficial customer = data.customers.get(key);
                    Customer customer1 = customers.get(key);
                    boolean expect = customer.refundProduct(data.products.get("foods_origin").get(i));
                    boolean actual = (boolean) refundProduct.invoke(customer1, products.get("foods_origin").get(i));
                    assertEquals(expect, actual);
                    assertEquals(customer.wallet, wallet.getFloat(customer1));
                    assertTrue(productListEquals(customer.shoppingCart, (ArrayList<Product>) shoppingCart.get(customer1)));
                    for (String key1 : data.stores.keySet()) {
                        StoreOfficial store = data.stores.get(key1);
                        Store store1 = stores.get(key1);
                        assertEquals(store.income, income.getFloat(store1));
                        assertTrue(productListEquals(store.productList, (ArrayList<Product>) productList.get(store1)));
                    }
                }

                for (int i = 0; i < data.products.get("foods_extend").size(); i++) {
                    CustomerOfficial customer = data.customers.get(key);
                    Customer customer1 = customers.get(key);
                    boolean expect = customer.refundProduct(data.products.get("foods_extend").get(i));
                    boolean actual = (boolean) refundProduct.invoke(customer1, products.get("foods_extend").get(i));
                    assertEquals(expect, actual);
                    assertEquals(customer.wallet, wallet.getFloat(customer1));
                    assertTrue(productListEquals(customer.shoppingCart, (ArrayList<Product>) shoppingCart.get(customer1)));
                    for (String key1 : data.stores.keySet()) {
                        StoreOfficial store = data.stores.get(key1);
                        Store store1 = stores.get(key1);
                        assertEquals(store.income, income.getFloat(store1));
                        assertTrue(productListEquals(store.productList, (ArrayList<Product>) productList.get(store1)));
                    }
                }

                for (int i = 0; i < data.products.get("clothes_extend").size(); i++) {
                    CustomerOfficial customer = data.customers.get(key);
                    Customer customer1 = customers.get(key);
                    boolean expect = customer.refundProduct(data.products.get("clothes_extend").get(i));
                    boolean actual = (boolean) refundProduct.invoke(customer1, products.get("clothes_extend").get(i));
                    assertEquals(expect, actual);
                    assertEquals(customer.wallet, wallet.getFloat(customer1));
                    assertTrue(productListEquals(customer.shoppingCart, (ArrayList<Product>) shoppingCart.get(customer1)));
                    for (String key1 : data.stores.keySet()) {
                        StoreOfficial store = data.stores.get(key1);
                        Store store1 = stores.get(key1);
                        assertEquals(store.income, income.getFloat(store1));
                        assertTrue(productListEquals(store.productList, (ArrayList<Product>) productList.get(store1)));
                    }
                }

                for (int i = 0; i < data.products.get("clothes_origin").size(); i++) {
                    CustomerOfficial customer = data.customers.get(key);
                    Customer customer1 = customers.get(key);
                    boolean expect = customer.refundProduct(data.products.get("clothes_origin").get(i));
                    boolean actual = (boolean) refundProduct.invoke(customer1, products.get("clothes_origin").get(i));
                    assertEquals(expect, actual);
                    assertEquals(customer.wallet, wallet.getFloat(customer1));
                    assertTrue(productListEquals(customer.shoppingCart, (ArrayList<Product>) shoppingCart.get(customer1)));
                    for (String key1 : data.stores.keySet()) {
                        StoreOfficial store = data.stores.get(key1);
                        Store store1 = stores.get(key1);
                        assertEquals(store.income, income.getFloat(store1));
                        assertTrue(productListEquals(store.productList, (ArrayList<Product>) productList.get(store1)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("refundProduct error");
        } catch (AssertionError e) {
            e.printStackTrace();
            fail("Bonus: wrong refund");
        }
    }

    //class Customer: 25+4
}
