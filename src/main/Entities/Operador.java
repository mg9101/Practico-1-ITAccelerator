import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

public class Operador<T extends Comparable<T>> {

    public static <T extends Comparable<T>> T minimoArray(T[] array) throws ArrayIndexOutOfBoundsException{
        T min = array[0];
        for (int i = 1; i<array.length;i++){
            if (min.compareTo(array[i])<= 0){
                min = array[i];
            }
        }
        return min;
    }
    public static <T extends Comparable<T>> T maximoArray(T[] array) throws ArrayIndexOutOfBoundsException{
        T max = array[0];
        for (int i = 1; i<array.length;i++){
            if (max.compareTo(array[i])>= 0){
                max = array[i];
            }
        }
        return max;
    }

}
