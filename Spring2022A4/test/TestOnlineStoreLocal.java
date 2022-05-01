import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestOnlineStoreLocal {
    private static Product product1;
    private static Product product2;
    private static Product product3;
    private static Product product4;
    private static Product product5;
    private static Constructor<Product> productConstructor;
    private static Store store1;
    private static Store store2;

    private static Constructor<Store> storeConstructor1;
    private static Constructor<Store> storeConstructor2;

    private static Customer customer;
    private static Constructor<Customer> customerConstructor;

    @Test
    public void test00_ProductConstructorExist() {
        Class<?>[] parameters = {String.class, float.class};
        try {
            productConstructor = Product.class.getConstructor(parameters);
        } catch (NoSuchMethodException e) {
            fail("The declaration of constructor of Class Product doesn't meet the requirement!");
        }
    }

    @Test
    public void test01_ProductFieldExist() {
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

            assertEquals(cnt.getType(), int.class);
            assertEquals(id.getType(), int.class);
            assertEquals(name.getType(), String.class);
            assertEquals(price.getType(), float.class);
            assertEquals(ratings.getType(), ArrayList.class);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("The declaration of fields of Class Product doesn't meet the requirement!");
        }
    }

    @Test
    public void test02_ProductMethodExist() {
        try {
            Method setRating = Product.class.getDeclaredMethod("setRating", int.class);
            Method getAvgRating = Product.class.getDeclaredMethod("getAvgRating");
            Method toString = Product.class.getDeclaredMethod("toString");

            assertEquals(setRating.getReturnType(), boolean.class);
            assertEquals(getAvgRating.getReturnType(), float.class);
            assertEquals(toString.getReturnType(), String.class);

            assertTrue(Modifier.isPublic(setRating.getModifiers()));
            assertTrue(Modifier.isPublic(getAvgRating.getModifiers()));
            assertTrue(Modifier.isPublic(toString.getModifiers()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("The declaration of methods of Class Product doesn't meet the requirement!");
        }
    }

    @Test
    public void test03_ProductConstructor() {
        Class<?>[] parameters = {String.class, float.class};
        try {
            Object[] args = {"P1", 1.5f};
            productConstructor = Product.class.getConstructor(parameters);
            product1 = productConstructor.newInstance(args);

            Field id = product1.getClass().getDeclaredField("id");
            id.setAccessible(true);
            assertEquals(1, id.getInt(product1));

            Field name = product1.getClass().getDeclaredField("name");
            name.setAccessible(true);
            assertEquals("P1", name.get(product1));

            Field price = product1.getClass().getDeclaredField("price");
            price.setAccessible(true);
            assertEquals(1.5, price.getFloat(product1));

            Field ratings = product1.getClass().getDeclaredField("ratings");
            ratings.setAccessible(true);
            assertEquals(new ArrayList<Integer>(), ratings.get(product1));

            args = new Object[]{"P2", 2f};
            product2 = productConstructor.newInstance(args);
            Field cnt1 = product2.getClass().getDeclaredField("cnt");
            cnt1.setAccessible(true);
            assertEquals(2, cnt1.getInt(product2));

            product3 = productConstructor.newInstance("P3",3.5f);
            product4 = productConstructor.newInstance("P4",10000f);
            product5 = productConstructor.newInstance("P5",1f);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
            fail("Constructor of Product is wrong!");
        }
    }

    @Test
    public void test04_SetRating() {
        try {
            Method setRating = Product.class.getDeclaredMethod("setRating", int.class);
            Boolean b = (Boolean) setRating.invoke(product3, 1);
            assertTrue(b);

            Field ratings = Product.class.getDeclaredField("ratings");
            ratings.setAccessible(true);
            ArrayList<Integer> answer = new ArrayList<>();
            answer.add(1);
            assertTrue(answer.containsAll((ArrayList)ratings.get(product3)));
            assertEquals(answer.size(), ((ArrayList)ratings.get(product3)).size());

            assertTrue((Boolean) setRating.invoke(product3, 2));
            answer.add(2);
            assertTrue(answer.containsAll((ArrayList)ratings.get(product3)));
            assertEquals(answer.size(), ((ArrayList)ratings.get(product3)).size());

            assertFalse((Boolean) setRating.invoke(product3, 6));
            assertFalse((Boolean) setRating.invoke(product3, 0));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test05_getAvgRating() {
        try {
            Method getAvgRating = Product.class.getDeclaredMethod("getAvgRating");
            Field ratings = Product.class.getDeclaredField("ratings");
            ratings.setAccessible(true);
            float pre = (float) getAvgRating.invoke(product3);
            assertEquals(1.5f, pre);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test06_toString() {
        try {
            Method toString = Product.class.getDeclaredMethod("toString");
            String pre = (String) toString.invoke(product3);
            String ans = "Product ID 3, P3, RMB 3.50, Rating 1.5";
            assertEquals(ans, pre);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test07_StoreConstructorExist() {
        Class<?>[] parameters1 = {String.class};
        Class<?>[] parameters2 = {String.class, ArrayList.class, float.class};
        try {
            storeConstructor1 = Store.class.getConstructor(parameters1);
            storeConstructor2 = Store.class.getConstructor(parameters2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("The declaration of constructors of class Store doesn't meet the requirement!");
        }
    }

    @Test
    public void test08_StoreFieldExist() {
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
            fail("The declaration of fields of class Store doesn't meet the requirement!");
        }
    }

    @Test
    public void test09_StoreMethodExist() {
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
            fail("The declaration of methods of class Store doesn't meet the requirement!");
        }
    }

    @Test
    public void test10_StoreConstructor() {
        Class<?>[] parameters1 = {String.class};
        Class<?>[] parameters2 = {String.class, ArrayList.class, float.class};
        try {
            Object[] args1 = {"S1"};
            storeConstructor1 = Store.class.getConstructor(parameters1);
            store1 = storeConstructor1.newInstance(args1);

            ArrayList<Product> list2 = new ArrayList<>();
            list2.add(product2);
            list2.add(product3);
            Object[] args2 = {"S2", list2, 1000};
            storeConstructor2 = Store.class.getConstructor(parameters2);
            store2 = storeConstructor2.newInstance(args2);
            ArrayList<Product> products = new ArrayList<>();
            products.add(product2);
            products.add(product3);

            Field id = store1.getClass().getDeclaredField("id");
            id.setAccessible(true);
            assertEquals(1, id.getInt(store1));

            Field name = store1.getClass().getDeclaredField("name");
            name.setAccessible(true);
            assertEquals("S1", name.get(store1));

            Field income = store1.getClass().getDeclaredField("income");
            income.setAccessible(true);
            assertEquals(0, income.getFloat(store1));

            Field productList = store1.getClass().getDeclaredField("productList");
            productList.setAccessible(true);
            assertEquals(new ArrayList<Integer>(), productList.get(store1));

            Field id2 =Store.class.getDeclaredField("id");
            id2.setAccessible(true);
            assertEquals(2, id.getInt(store2));

            Field productList2 =Store.class.getDeclaredField("productList");
            productList2.setAccessible(true);
            assertEquals(products, productList.get(store2));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Constructor(s) of Store is/are wrong!");
        }
    }

    @Test
    public void test11_StoreAddProduct() {
        try {
            Method addProduct = Store.class.getDeclaredMethod("addProduct", Product.class);
            Field products = Store.class.getDeclaredField("productList");
            products.setAccessible(true);
            ArrayList<Product> answer = new ArrayList<>();
            answer.add(product2);
            answer.add(product3);
            assertFalse((Boolean) addProduct.invoke(store2, product2));
            assertTrue((Boolean) addProduct.invoke(store2, product1));
            assertFalse((Boolean) addProduct.invoke(store2, product1));
            answer.add(product1);
            assertTrue(answer.containsAll((ArrayList<Product>) products.get(store2)));
            assertEquals(answer.size(), ((ArrayList<Product>)products.get(store2)).size());

            ArrayList<Product> answer2 = new ArrayList<>();
            answer2.add(product5);
            assertTrue((Boolean) addProduct.invoke(store1, product5));
            assertTrue(answer2.containsAll((ArrayList<Product>) products.get(store1)));
            assertEquals(answer2.size(), ((ArrayList<Product>)products.get(store1)).size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("addProduct method of class Store is wrong!");
        }
    }

    @Test
    public void test12_StoreGetProductList() {
        try {
            Method getProductList = Store.class.getDeclaredMethod("getProductList");
            Field products = Store.class.getDeclaredField("productList");
            products.setAccessible(true);
            ArrayList<Product> answer = new ArrayList<>();
            answer.add(product2);
            answer.add(product3);
            answer.add(product1);
            assertTrue(answer.containsAll((ArrayList<Product>) getProductList.invoke(store2)));
            assertEquals(answer.size(), ((ArrayList<Product>)getProductList.invoke(store2)).size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("getProducts method of class Store is wrong!");
        }
    }

    @Test
    public void test13_StoreHasProduct() {
        try {
            Method hasProduct = Store.class.getDeclaredMethod("hasProduct", Product.class);
            assertTrue((Boolean) hasProduct.invoke(store2, product1));
            assertFalse((Boolean) hasProduct.invoke(store2, product4));
        } catch (Exception e) {
            e.printStackTrace();
            fail("hasProduct method of class Store is wrong!");
        }
    }

    @Test
    public void test14_StoreRemoveProduct() {
        try {
            Method removeProduct = Store.class.getDeclaredMethod("removeProduct", Product.class);
            Field products = Store.class.getDeclaredField("productList");
            products.setAccessible(true);
            ArrayList<Product> answer = new ArrayList<>();
            answer.add(product2);
            answer.add(product3);
            answer.add(product1);

            assertTrue((Boolean) removeProduct.invoke(store2, product1));
            assertFalse((Boolean) removeProduct.invoke(store2, product1));
            answer.remove(product1);
            assertTrue(answer.containsAll((ArrayList<Product>) products.get(store2)));
            assertEquals(answer.size(), ((ArrayList<Product>)products.get(store2)).size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("removeProduct method of class Store is wrong!");
        }
    }

    @Test
    public void test15_StorePurchase() {
        try {
            Method transact =Store.class.getDeclaredMethod("transact", Product.class, int.class);
            Field income =Store.class.getDeclaredField("income");
            Field products =Store.class.getDeclaredField("productList");
            income.setAccessible(true);
            products.setAccessible(true);
            ArrayList<Product> answer = new ArrayList<>();
            answer.add(product2);
            answer.add(product3);

            transact.invoke(store2, product2, 0);
            answer.remove(product2);
            assertTrue(answer.containsAll((ArrayList<Product>) products.get(store2)));
            assertEquals(answer.size(), ((ArrayList<Product>)products.get(store2)).size());
            assertEquals(1002f, income.get(store2));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Purchasing part in transact method of class Store is wrong!");
        }
    }

    @Test
    public void test16_StoreRefund() { //bonus
        try {
            Method transact =Store.class.getDeclaredMethod("transact", Product.class, int.class);
            Field income =Store.class.getDeclaredField("income");
            Field products =Store.class.getDeclaredField("productList");
            income.setAccessible(true);
            products.setAccessible(true);
            ArrayList<Product> answer = new ArrayList<>();
            answer.add(product3);

            transact.invoke(store2, product2, 1);
            answer.add(product2);
            assertTrue(answer.containsAll((ArrayList<Product>) products.get(store2)));
            assertEquals(answer.size(), ((ArrayList<Product>)products.get(store2)).size());
            assertEquals(1000f, income.get(store2));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Refunding part in transact method of class Store is wrong!");
        }
    }

    @Test
    public void test17_CustomerFieldExist() {
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
            fail("Field error!");
        }
    }

    @Test
    public void test18_CustomerMethodExist() {
        try {
            Method rateProduct = Customer.class.getDeclaredMethod("rateProduct", Product.class, int.class);
            Method purchaseProduct = Customer.class.getDeclaredMethod("purchaseProduct", Store.class, Product.class);
            Method updateWallet = Customer.class.getDeclaredMethod("updateWallet", float.class);
            Method viewShoppingCart = Customer.class.getDeclaredMethod("viewShoppingCart", SortBy.class);
            Method refundProduct = Customer.class.getDeclaredMethod("refundProduct", Product.class);

            assertEquals(boolean.class, rateProduct.getReturnType());
            assertEquals(boolean.class, purchaseProduct.getReturnType());
            assertEquals(void.class, updateWallet.getReturnType());
            assertEquals(void.class, viewShoppingCart.getReturnType());
            assertEquals(boolean.class, refundProduct.getReturnType());

            assertTrue(Modifier.isPublic(rateProduct.getModifiers()));
            assertTrue(Modifier.isPublic(purchaseProduct.getModifiers()));
            assertTrue(Modifier.isPublic(updateWallet.getModifiers()));
            assertTrue(Modifier.isPublic(viewShoppingCart.getModifiers()));
            assertTrue(Modifier.isPublic(refundProduct.getModifiers()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("The declaration of methods of Class Customer doesn't meet the requirement!");
        }
    }

    @Test
    public void test19_CustomerConstructorExist() {
        Class<?>[] parameters = {String.class, float.class};
        try {
            customerConstructor = Customer.class.getConstructor(parameters);
            customer = customerConstructor.newInstance("C1", 100);
            Field id = customer.getClass().getDeclaredField("id");
            Field name = customer.getClass().getDeclaredField("name");
            Field shoppingCart = customer.getClass().getDeclaredField("shoppingCart");
            Field wallet = customer.getClass().getDeclaredField("wallet");

            id.setAccessible(true);
            assertEquals(1, id.getInt(customer));
            name.setAccessible(true);
            assertEquals("C1", name.get(customer));
            shoppingCart.setAccessible(true);
            assertEquals(new ArrayList<Product>(), shoppingCart.get(customer));
            wallet.setAccessible(true);
            assertEquals(100f, wallet.get(customer));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Customer Constructor error!");
        }
    }

    @Test
    public void test20_rateProduct() {
        try {
            Method rateProduct = Customer.class.getDeclaredMethod("rateProduct", Product.class, int.class);
            rateProduct.setAccessible(true);
            assertTrue((Boolean) rateProduct.invoke(customer, product1, 5));
            assertFalse((Boolean) rateProduct.invoke(customer, product1, -1));
            assertFalse((Boolean) rateProduct.invoke(customer, product1, 6));
            Field ratings = Product.class.getDeclaredField("ratings");
            ratings.setAccessible(true);
            ArrayList<Integer> answer = new ArrayList<>();
            answer.add(5);
            assertEquals(answer, ratings.get(product1));
        } catch (Exception e) {
            e.printStackTrace();
            fail("rateProduct error!");
        }
    }

    @Test
    public void test21_purchaseProduct() {
        try {
            Method purchaseProduct = Customer.class.getDeclaredMethod("purchaseProduct", Store.class, Product.class);
            purchaseProduct.setAccessible(true);
            Field shoppingCart = Customer.class.getDeclaredField("shoppingCart");
            shoppingCart.setAccessible(true);
            Field wallet = Customer.class.getDeclaredField("wallet");
            wallet.setAccessible(true);
            Field productList = Store.class.getDeclaredField("productList");
            productList.setAccessible(true);
            Field income = Store.class.getDeclaredField("income");
            income.setAccessible(true);

            ArrayList<Product> answer_shoppingCart = (ArrayList<Product>) ((ArrayList<Product>) shoppingCart.get(customer)).clone();
            ArrayList<Product> answer_productList = (ArrayList<Product>) ((ArrayList<Product>) productList.get(store2)).clone();

            assertTrue((Boolean) purchaseProduct.invoke(customer, store2, product2));

            answer_shoppingCart.add(product2);
            assertTrue(answer_shoppingCart.containsAll((ArrayList)shoppingCart.get(customer)));
            assertEquals(answer_shoppingCart.size(), ((ArrayList)shoppingCart.get(customer)).size());
            assertEquals(98f, wallet.get(customer));
            answer_productList.remove(product2);
            assertTrue(answer_productList.containsAll((ArrayList)productList.get(store2)));
            assertEquals(answer_productList.size(), ((ArrayList)productList.get(store2)).size());
            assertEquals(1002f,income.get(store2));

            assertTrue((Boolean) purchaseProduct.invoke(customer, store2, product3));

            answer_shoppingCart.add(product3);
            assertTrue(answer_shoppingCart.containsAll((ArrayList)shoppingCart.get(customer)));
            assertEquals(answer_shoppingCart.size(), ((ArrayList)shoppingCart.get(customer)).size());
            assertEquals(94.5f, wallet.get(customer));
            answer_productList.remove(product3);
            assertTrue(answer_productList.containsAll((ArrayList)productList.get(store2)));
            assertEquals(answer_productList.size(), ((ArrayList)productList.get(store2)).size());
            assertEquals(1005.5f,income.get(store2));

            assertFalse((Boolean) purchaseProduct.invoke(customer, store2, product1));
            assertFalse((Boolean) purchaseProduct.invoke(customer, store2, product4));

            assertTrue((Boolean) purchaseProduct.invoke(customer, store1, product5));
            answer_shoppingCart.add(product5);
            assertTrue(answer_shoppingCart.containsAll((ArrayList)shoppingCart.get(customer)));
            assertEquals(answer_shoppingCart.size(), ((ArrayList)shoppingCart.get(customer)).size());
            assertEquals(93.5f, wallet.get(customer));
            answer_productList.remove(product5);
            assertTrue(answer_productList.containsAll((ArrayList)productList.get(store1)));
            assertEquals(answer_productList.size(), ((ArrayList)productList.get(store1)).size());
            assertEquals(1f, income.get(store1));
        } catch (Exception e) {
            e.printStackTrace();
            fail("purchaseProduct error!");
        }
    }

    @Test
    public void test22_updateWallet() {
        try {
            Customer customer1 = customerConstructor.newInstance("C2", 10);
            Method updateWallet = Customer.class.getDeclaredMethod("updateWallet", float.class);
            updateWallet.setAccessible(true);
            updateWallet.invoke(customer1, 100f);
            Field wallet = customer1.getClass().getDeclaredField("wallet");
            wallet.setAccessible(true);
            assertEquals(110f, wallet.get(customer1));
        } catch (Exception e) {
            e.printStackTrace();
            fail("updateWallet error!");
        }
    }

    @Test
    public void test23_viewShoppingCart() {
        try {
            Method viewShoppingCart = Customer.class.getDeclaredMethod("viewShoppingCart", SortBy.class);
            viewShoppingCart.setAccessible(true);
            Customer customer1 = customerConstructor.newInstance("C3", 10);
            Field shoppingCart = customer1.getClass().getDeclaredField("shoppingCart");
            shoppingCart.setAccessible(true);
            ArrayList<Product> cart = (ArrayList<Product>) (shoppingCart.get(customer1));
            cart.add(productConstructor.newInstance("2", 10f));
            cart.add(productConstructor.newInstance("1", 20f));
            cart.add(productConstructor.newInstance("3", 15f));
            Field ratings = Product.class.getDeclaredField("ratings");
            ratings.setAccessible(true);
            ratings.set(cart.get(0), new ArrayList<>(Arrays.asList(1,2,3)));
            ratings.set(cart.get(1), new ArrayList<>(Arrays.asList(1,1,1)));
            ratings.set(cart.get(2), new ArrayList<>(Arrays.asList(0,0,0)));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            viewShoppingCart.invoke(customer1, SortBy.Price);
            viewShoppingCart.invoke(customer1, SortBy.Rating);
            assertEquals("Product ID 6, 2, RMB 10.00, Rating 2.0" + System.lineSeparator() +
                    "Product ID 8, 3, RMB 15.00, Rating 0.0" + System.lineSeparator() +
                    "Product ID 7, 1, RMB 20.00, Rating 1.0" + System.lineSeparator() +
                    "Product ID 8, 3, RMB 15.00, Rating 0.0" + System.lineSeparator() +
                    "Product ID 7, 1, RMB 20.00, Rating 1.0" + System.lineSeparator() +
                    "Product ID 6, 2, RMB 10.00, Rating 2.0" + System.lineSeparator(), outContent.toString());
            System.setOut(System.out);
        } catch (Exception e) {
            System.setOut(System.out);
            e.printStackTrace();
            fail("viewShoppingCart error!");
        }
    }

    @Test
    public void test24_refundProduct() {
        try {
            Method refundProduct = Customer.class.getDeclaredMethod("refundProduct", Product.class);
            refundProduct.setAccessible(true);
            Field shoppingCart = Customer.class.getDeclaredField("shoppingCart");
            shoppingCart.setAccessible(true);
            Field wallet = Customer.class.getDeclaredField("wallet");
            wallet.setAccessible(true);
            Field productList = Store.class.getDeclaredField("productList");
            productList.setAccessible(true);
            Field income = Store.class.getDeclaredField("income");
            income.setAccessible(true);
            Field price = Product.class.getDeclaredField("price");
            price.setAccessible(true);

            ArrayList<Product> answer_productList = (ArrayList<Product>) ((ArrayList<Product>) productList.get(store2)).clone();
            ArrayList<Product> answer_shoppingCart = (ArrayList<Product>) ((ArrayList<Product>) shoppingCart.get(customer)).clone();
            float current_wallet = (float) wallet.get(customer);
            float current_income = (float) income.get(store2);

            assertTrue((Boolean) refundProduct.invoke(customer,product2));
            assertFalse((Boolean) refundProduct.invoke(customer,product2));
            assertFalse((Boolean) refundProduct.invoke(customer,product1));
            assertFalse((Boolean) refundProduct.invoke(customer,product4));
            answer_shoppingCart.remove(product2);
            answer_productList.add(product2);

            assertTrue(answer_shoppingCart.containsAll((ArrayList)shoppingCart.get(customer)));
            assertEquals(answer_shoppingCart.size(), ((ArrayList)shoppingCart.get(customer)).size());
            assertEquals(current_wallet + price.getFloat(product2), wallet.get(customer));

            assertTrue(answer_productList.containsAll((ArrayList<Product>) productList.get(store2)));
            assertEquals(answer_productList.size(), ((ArrayList<Product>) productList.get(store2)).size());
            assertEquals(current_income - price.getFloat(product2), income.get(store2));
            current_income = (float) income.get(store2);
            current_wallet = (float) wallet.get(customer);

            assertTrue((Boolean) refundProduct.invoke(customer,product3));
            answer_shoppingCart.remove(product3);
            answer_productList.add(product3);

            assertTrue(answer_shoppingCart.containsAll((ArrayList)shoppingCart.get(customer)));
            assertEquals(answer_shoppingCart.size(), ((ArrayList)shoppingCart.get(customer)).size());
            assertEquals(current_wallet + price.getFloat(product3), wallet.get(customer));

            assertTrue(answer_productList.containsAll((ArrayList<Product>) productList.get(store2)));
            assertEquals(answer_productList.size(), ((ArrayList<Product>) productList.get(store2)).size());
            assertEquals(current_income - price.getFloat(product3), income.get(store2));
            current_income = (float) income.get(store1);
            current_wallet = (float) wallet.get(customer);
            answer_productList = (ArrayList<Product>) ((ArrayList<Product>) productList.get(store1)).clone();

            assertTrue((Boolean) refundProduct.invoke(customer,product5));
            answer_shoppingCart.remove(product5);
            answer_productList.add(product5);

            assertTrue(answer_shoppingCart.containsAll((ArrayList)shoppingCart.get(customer)));
            assertEquals(answer_shoppingCart.size(), ((ArrayList)shoppingCart.get(customer)).size());
            assertEquals(current_wallet + price.getFloat(product5), wallet.get(customer));

            assertTrue(answer_productList.containsAll((ArrayList<Product>) productList.get(store1)));
            assertEquals(answer_productList.size(), ((ArrayList<Product>) productList.get(store1)).size());
            assertEquals(current_income - price.getFloat(product5), income.get(store1));

        } catch (Exception e) {
            e.printStackTrace();
            fail("refundProduct error!");
        }
    }
}
