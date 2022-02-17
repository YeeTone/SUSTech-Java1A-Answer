package Spring2022A4;


import java.math.BigDecimal;

public class Main {

    static {
        SecurityManager sm  = new SecurityManager(){
            @Override
            public void checkPackageAccess(String pkg) {
                super.checkPackageAccess(pkg);
                if(pkg.equals("java.math")){
                    throw new RuntimeException();
                }
            }
        };

        System.setSecurityManager(sm);
    }

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("1.1");
        System.out.println(bd);
    }
}
