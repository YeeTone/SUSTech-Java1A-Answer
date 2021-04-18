package Spring2021A4;

import java.util.Arrays;

public class MagicStrings {
    private int[]priority;
    private String[]ss;

    public MagicStrings(String s){
        setSs(s);
        setPriority();
    }

    public MagicStrings(int[]priority,String s){
        setPriority(priority);
        setSs(s);
    }

    public MagicStrings(String priority,String s){
        setPriority(priority);
        setSs(s);
    }

    public void setPriority(){
        this.priority=new int[26];
        for (int i = 0; i < 26; i++) {
            this.priority[i]=i+1;
        }
    }

    public void setPriority(int[]priority){
        this.priority=priority.clone();
    }

    public void setPriority(String priority){
        this.priority=new int[26];
        String[]split=priority.split(" ");
        for (int i = 0; i < 26; i++) {
            this.priority[i]=Integer.parseInt(split[i]);
        }
    }

    public void setPriority(char c,int priority){
        if(Character.isLetter(c)){
            this.priority[Character.toLowerCase(c)-'a']=priority;
        }

    }

    public void setSs(String input){
        if(input==null){
            return;
        }

        StringBuilder b= new StringBuilder();
        char last=' ';
        for (int i = 0; i < input.length(); i++) {
            char ci=input.charAt(i);
            if(Character.isWhitespace(ci)||Character.isAlphabetic(ci)){
                if(!(Character.isWhitespace(last)&&Character.isWhitespace(ci))){
                    b.append(ci);
                    last=ci;
                }
            }
        }

        this.ss=b.toString().split(" ");
    }

    public int stringNum(){
        return this.ss==null?0:this.ss.length;
    }

    public int getPriority(char c){
        return this.priority[Character.toLowerCase(c)-'a'];
    }

    public int compareString(String a,String b){
        int minLength=Math.min(a.length(),b.length());
        for (int i = 0; i < minLength; i++) {
            int pa=getPriority(a.charAt(i));
            int pb=getPriority(b.charAt(i));
            if(pa<pb){
                return -1;
            }else if(pa>pb){
                return 1;
            }
        }

        if(a.length()==b.length()){
            return 0;
        }else {
            return a.length()>b.length()?1:-1;
        }
    }

    public void sortSs(){
        Arrays.sort(ss,((o1, o2) -> compareString(o2,o1)));
    }

    public String getStrings(){
        if(ss==null){
            return null;
        }
        int num=stringNum();
        StringBuilder b=new StringBuilder();
        for (int i = 0; i < num; i++) {
            b.append(ss[i]);
            if(i!=num-1){
                b.append(" ");
            }
        }

        return b.toString();
    }

    public static void main(String[] args) {
        int[] zeros =new int[26];
        String s="123456";
        String s2="0 0 0 0 0 0 0 0 "
                +"0 0 0 0 0 0 0 0 "
                +"0 0 0 0 0 0 0 0 "
                +"0 0";
        MagicStrings ms1=new MagicStrings(zeros,s);
        MagicStrings ms2=new MagicStrings(s);
        MagicStrings ms3=new MagicStrings(s2,s);
    }
}
