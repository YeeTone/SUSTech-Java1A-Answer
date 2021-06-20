import java.util.Arrays;
import java.util.Scanner;

public class A2Q3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        String s = "";
        //开一个long数组存储每一行字符串计算所得结果
        //由于模的是998244353，则剩下的结果最大可是998244352在乘以对应位的字符ASCii码时可能会爆int
        //long可以规避此问题，10^9次方乘以ASCii码最大值必小于long的最大值
        long result[] = new long [number];
        sc.nextLine();

        for(int i=0;i<number;i++) {
            s = sc.nextLine();
            int length = s.length();
            long temp =1;
            long res =0;

            for(int j=0;j<length;j++) {
                //根据数据范围 length稍微过长的时候必定会超过数据范围，不可使用Math.pow且精度存在问题
                //写for循环手算，每次乘起来的时候都模998244353防止过大超出范围
                for(int k=0;k<j;k++) {
                    temp = (temp*length)%998244353;
                }
                //temp，res均用long规避int范围过小
                temp = s.charAt(j)*temp%998244353;
                res = (res+temp)%998244353;
                temp=1;
            }
            result[i]=res;
        }

        //直接排序取得最大值最小值
        Arrays.sort(result);
//        System.out.println(result[0]+" "+result[number-1]);
        long max= result[number-1];
        long min = result[0];

        //求最大公约数，并得到结果
        for(long i=min;i>0;i--){
            if(max%i==0&&min%i==0){
                System.out.println(max/i+" "+min/i);
                break;
            }
        }
    }
}
