import java.util.ArrayList;
import java.util.List;

public class AirlineUtil {
    public static <T> List<T> toModifiableList(List<T> arr){
        return new ArrayList<>(arr);
    }
}
