import java.util.Scanner;
public class UserTest {
    public static void main(String[] args) {
        User user =new User();
        Scanner in = new Scanner(System.in);
        user.setUser("Lucy");
        user.setPassword("123456");
        user.setMoney(1000);
        user.introduce();
        user.expense(2000,in);
        user.expense(500,in);
        user.income(1000);
        user.introduce();
        in.close();
    }
}
