package dharma.github.io.mvvmsample.data.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Converting  array List to json and stored in room database
 */
public class ListConverter {


    /**
     * converting string type from db to list
     *
     * @param value
     * @return
     */
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);

    }


    /**
     * converting list to string and storing in db
     *
     * @param list
     * @return
     */
    @TypeConverter
    public static String fromArrayLisr(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;

    }
}