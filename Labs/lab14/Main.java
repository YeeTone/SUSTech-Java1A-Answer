package labs.lab14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, IPhone> iPhoneMap = new HashMap<>();
        iPhoneMap.put(1, new IPhone("iPhone 13 Pro", 7999));
        iPhoneMap.put(2, new IPhone("iPhone 13 Pro Max", 8999));
        iPhoneMap.put(3, new IPhone("iPhone 13 mini", 5199));
        iPhoneMap.put(4, new IPhone("iPhone 13", 5999));
        System.out.println(iPhoneMap.get(1));

        for (Map.Entry<Integer, IPhone> iPhoneEntry : iPhoneMap.entrySet()) {
            System.out.printf("[%d -> %s]\n", iPhoneEntry.getKey(), iPhoneEntry.getValue());
        }

    }

}
