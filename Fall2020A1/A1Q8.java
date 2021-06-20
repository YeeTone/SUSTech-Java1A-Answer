import java.util.Scanner;

public class A1Q8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ln = scanner.nextInt();
        String strId = scanner.next();
        int id = Integer.parseInt(strId);
        int newId = 0;
        int digit = 1;
        for (int i = 0; i < strId.length() - 1; i++) {
            digit *= 10;
        }
        long position = digit;
        for (int j = 0; j < strId.length(); j++) {
            int num = digit;
            int a = id / num;//当前位数上的数字
            int b = id % num;//余数
            int record = num;//记录位数
            for (int i = 1; i <= strId.length() - 1; i++) {
                num /= 10;//向左移动位数
                if (b / num > a) {
                    a = b / num;
                    record = num;
                }
                b = b % num;
            }
            id -= a * record;//更新id
            newId += a * position;//更新newId
            position /= 10;//位数左移一位
        }
        //找出幸运数字及个数
        int num = digit;//倒序
        int j = digit;
        int a = newId / num;//当前位数上的数字
        int b = newId % num;//余数
        for (int i = 0; i <= strId.length() - 1; i++) {
            if (a == ln) {
                newId -= a * num;//更新newId
                newId = (newId / (num * 10)) * num + newId % num + ln * j;
            }
            if (i < strId.length() - 1) {
                num /= 10;//向左移动位数
                a = b / num;
                b = b % num;
            }
        }
        if (ln == 0) {
            for (int i = 0; i < strId.length(); i++) {
                if (strId.charAt(i) == '0') {
                    System.out.print(0);
                }
            }
        }
        System.out.println(newId);
    }
}
