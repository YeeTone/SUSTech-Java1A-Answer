import java.util.ArrayList;
import java.util.Scanner;

public class SoftOpen {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Food> foodList = genarateMenu();  //generate a Menu
		User user = genarateUser(in);				//generate a user
		user.introduce();							//show the account of the user
		userConsume(foodList,user,in);				//user consume
		user.introduce();							//after consume, show the account of the user
		in.close();
	}
	public static User genarateUser(Scanner in){
		User user =new User();
		System.out.print("Generate a user,please input name:");
		String account = in.next();
		user.setUser(account);
		System.out.print("balance($):");
		user.setPassword("123456");			//default password
		double money = in.nextDouble();
		user.setMoney(money);
		return user;
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

	public static void userConsume(ArrayList<Food> foodList, User user,Scanner in){
		getMenu(foodList);
		double cost = userSelect(foodList,in);
		if(cost > 0)
			user.expense(cost,in);
		else
			System.out.println(user.getAccount() + "consume 0$");
	}

	public static double userSelect(ArrayList<Food> foodList,Scanner in){
		double cost = 0.0;
		System.out.println("please input the foodID and the number you want,to exit input 0 as foodID");
		while(true){
			System.out.print("food id(input 0 to end select):");
			int foodId = in.nextInt();
			if(0==foodId) {
				System.out.println("select end");
				return cost;
			}
			if(foodId<0 || foodId>foodList.size()){
				System.out.println(foodId+" not in the Menu,try again");
				continue;
			}
			System.out.print("number of this food:");
			int num = in.nextInt();
			if(num < 0) {
				System.out.println("wrong number,try again");
				continue;
			}
			cost += foodList.get(foodId-1).getPrice()*num;
		}
	}
}
