import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    static BufferedReader bufferedReader;


    public static List<String> readTestInCase(String path) throws IOException {
        List<String> flights = new ArrayList<>();
        bufferedReader = new BufferedReader(new FileReader(path));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            flights.add(str);
        }
        flights.remove(0);
        bufferedReader.close();
        return flights;

    }

    public static List<OutputComponent> readTestOutCase(String path) throws IOException {
        bufferedReader = new BufferedReader(new FileReader(path));
        List<String> outs = new ArrayList<>();
        List<OutputComponent> outputComponents = new ArrayList<>();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            outs.add(str);
        }
        int index = 0;
        int size = outs.size() / 6;
        for (int i = 0; i < size; i++) {
            OutputComponent outputComponent = new OutputComponent();
            index = i * 6;
            outputComponent.id = i;
            outputComponent.depart = outs.get(index).substring(7).trim();
            outputComponent.arrive = outs.get(index + 1).substring(7).trim();
            outputComponent.allRoutes = outs.get(index + 2).substring(10).trim();
            if (!outputComponent.allRoutes.equals("[]")) {
                outputComponent.less_time = outs.get(index + 3).substring(15).trim();
                outputComponent.less_price = outs.get(index + 4).substring(16).trim();
            }
            outputComponents.add(outputComponent);
        }
        bufferedReader.close();
        return outputComponents;
    }

    static class OutputComponent {
        int id;
        String depart;
        String arrive;
        String allRoutes;
        String less_time;
        String less_price;

        @Override
        public String toString() {
            return String.format("%d (%s->%s)\nallRoutes: %s\nless_time: %s\nless_price: %s\n"
                    , id, depart, arrive, allRoutes, less_time, less_price);
        }
    }



}
