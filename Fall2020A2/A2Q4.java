import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Scanner;

/*测试样例
1、题目后面给的例子 --基础分
 第一个 ：1+2*6/5-9  答案：-6
2、第一个值是负数情况有没有处理
 第二个 ：-1+8*9     答案：63
 第三个 : -9*8+2+5*7 答案：-455
3、负数除法有没有实现
 第四个 ：-3/2+8*5+4 答案：34
 第五个 ：6+5/8*7+3  答案：10
4、多位数参与运算有没有实现
 第六个 ：36/4+5/7+8*3+9 答案：39
 第七个 ：1+198/8-99/10+5 答案：-3
5、所有的综合在一起
 第八个 ：-32/7+11*6+9-5*2 答案：80
 第九个 ：-100*8+9/7+297/5-8234/35+9*28 答案：-6328
 第十个 ：-99*8/123+989*17/5+6*9/4+200-98/9+20 答案：867
 */



public class A2Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        ArrayList<String> calculate = new ArrayList<>();

        //循环遍及输入的字符串，放入一个arraylist中
        for(int i=0;i<data.length();i++){
            String s="";
            //考虑多位数情况 例如36+9*4 36应该被放入一个位置中去
            if(Character.isDigit(data.charAt(i))) {
                while (i < data.length() && Character.isDigit(data.charAt(i))) {
                    s = s + data.charAt(i);
                    i++;
                }
                calculate.add(s);
                i--;
            }//考虑第一个数如果为负号的情况，前面补一个0
            else if(data.charAt(i)=='-'){
                if(i==0) calculate.add("0");
                calculate.add(String.valueOf(data.charAt(i)));
            }
            else calculate.add(String.valueOf(data.charAt(i)));
        }

        int mid_term =0;
        //遇到+-*/。将前面位置的数与后面位置的数进行计算，并放入后面的位置供后面使用
        for(int i=0;i<calculate.size()-1;i++){
            switch (calculate.get(i)){
                case "+" :
                    mid_term = Integer.parseInt(calculate.get(i-1))+ Integer.parseInt(calculate.get(i+1));
                    calculate.set(i+1,String.valueOf(mid_term));
                    break;

                case "-" :
                    mid_term = Integer.parseInt(calculate.get(i-1))- Integer.parseInt(calculate.get(i+1));
                    calculate.set(i+1,String.valueOf(mid_term));
                    break;
                case "*" :
                    mid_term = Integer.parseInt(calculate.get(i-1))* Integer.parseInt(calculate.get(i+1));
                    calculate.set(i+1,String.valueOf(mid_term));
                    break;
                case "/" :
                    //考虑向下取整，对应负数除法的情况
                    mid_term =(int) Math.floor(Integer.parseInt(calculate.get(i-1))*1.0/ Integer.parseInt(calculate.get(i+1)));
                    calculate.set(i+1,String.valueOf(mid_term));
                    break;
            }
        }

        System.out.println(calculate.get(calculate.size()-1));
    }
}
