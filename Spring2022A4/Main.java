package Spring2022A4;


import java.math.BigDecimal;

public class Main {

    static {
        SecurityManager sm  = new SecurityManager(){
            @Override
            public void checkPackageAccess(String pkg) {
                super.checkPackageAccess(pkg);
                if(pkg.equals("java.math")){
                    throw new RuntimeException("You cannot use java.util.BigInteger or java.util.BigDecimal class in this problem!");
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
