import java.util.ArrayList;

public class FoodTest {
    public static void main(String[] args) {
        ArrayList<Food> foodList = genarateMenu();  //generate a Menu
        getMenu(foodList);							//show the menu
    }
    public static ArrayList<Food> genarateMenu(){
        Food pizza1=new Food();
        pizza1.setId(1);
        pizza1.setName("pizza");
        pizza1.setType("Seafood");
        pizza1.setSize(11);
        pizza1.setPrice(12);

        Food pizza2=new Food();
        pizza2.setId(2);
        pizza2.setName("pizza");
        pizza2.setType("Beef");
        pizza2.setSize(9);
        pizza2.setPrice(10);

        Food friedRice =new Food();
        friedRice.setId(3);
        friedRice.setName("fried rice");
        friedRice.setType("Seafood");
        friedRice.setSize(5);
        friedRice.setPrice(12);

        Food noodles =new Food();
        noodles.setId(4);
        noodles.setName("noodles");
        noodles.setType("Beef");
        noodles.setSize(6);
        noodles.setPrice(14);

        ArrayList<Food> foodList=new ArrayList<>();
        foodList.add(pizza1);
        foodList.add(pizza2);
        foodList.add(friedRice);
        foodList.add(noodles);
        return foodList;
    }
    public static void getMenu(ArrayList<Food> foodList){
        System.out.println("-------------------welcome,this is Start of the Menu-------------------");
        for(Food food:foodList) {
            food.showInformation();
        }
        System.out.println("-------------------welcome,this is  End  of the Menu-------------------");
    }
}
