package Fall2021A1.judger;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class A1Judger {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void initiate(){
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void test() throws Throwable {
        for (int i = 1; i <= 6; i++) {
            String className = "Fall2021A1.A1Q"+i;
            for (int j = 1; j <= 1000; j++) {
                outContent.reset();

                //String inName = "D:\\IdeaProjects\\CS102A2021Fall\\src\\Fall2021A1\\data\\data"+i+"\\"+j+".in";
                //String outName = "D:\\IdeaProjects\\CS102A2021Fall\\src\\Fall2021A1\\data\\data"+i+"\\"+j+".out";

                String inName = "..\\..\\..\\src\\Fall2021A1\\data\\data"+i+"\\"+j+".in";
                String outName = "..\\..\\..\\src\\Fall2021A1\\data\\data"+i+"\\"+j+".out";

                File inF = new File(inName), outF = new File(outName);
                if(inF.exists() && outF.exists()){
                    judge(className,inName,outName);
                }
            }
        }


    }

    private void judge(String className,String in,String out) throws Throwable{
        StringBuilder expected = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(out));

        String s;

        while ((s=br.readLine())!=null){
            expected.append(s).append(System.lineSeparator());
        }

        System.setIn(new FileInputStream(in));

        Class<?> clazz = Class.forName(className);
        Method m = clazz.getDeclaredMethod("main", String[].class);
        m.invoke(null, (Object) null);

        assertEquals(expected.toString(),outContent.toString());
    }

}
