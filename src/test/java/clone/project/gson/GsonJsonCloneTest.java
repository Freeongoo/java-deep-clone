package clone.project.gson;

import clone.project.data.BaseWithDate;
import clone.project.data.DataExtendBaseWithDate;
import com.google.gson.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GsonJsonCloneTest {

    private Gson gson;

    @Before
    public void setUp() {
        gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
    }

    @Test
    public void cloneTest_WhenWithoutInheritance() {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        BaseWithDate clonedBaseWithDate = gson.fromJson(gson.toJson(baseWithDate), BaseWithDate.class);

        assertThat(baseWithDate, equalTo(clonedBaseWithDate));
    }

    @Test
    public void cloneTest_WhenWithInheritance() {
        List<String> strings = new ArrayList<>();
        strings.add("someStr");
        DataExtendBaseWithDate dataExtendBaseWithDate = new DataExtendBaseWithDate(123L, "String", new Date(123321232), 1.1F, strings);
        DataExtendBaseWithDate clonedDataExtendBaseWithDate = gson.fromJson(gson.toJson(dataExtendBaseWithDate), DataExtendBaseWithDate.class);

        assertThat(dataExtendBaseWithDate, equalTo(clonedDataExtendBaseWithDate));
    }

    @Test
    public void cloneTest_WhenNullField() {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        baseWithDate.setId(null);
        BaseWithDate clonedBaseWithDate = gson.fromJson(gson.toJson(baseWithDate), BaseWithDate.class);

        assertThat(baseWithDate, equalTo(clonedBaseWithDate));
    }

    @Test
    public void cloneTest_WhenTryChangeClonedObject() {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        BaseWithDate clonedBaseWithDate = gson.fromJson(gson.toJson(baseWithDate), BaseWithDate.class);

        clonedBaseWithDate.setId(null);

        assertThat(baseWithDate.getId(), equalTo(123L));
    }
}

class GsonUTCDateAdapter implements JsonSerializer<Date>,JsonDeserializer<Date> {

    private final DateFormat dateFormat;

    public GsonUTCDateAdapter() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);    // This is the format I need
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));                                     // This is the key line which converts the date to UTC which cannot be accessed with the default serializer
    }

    @Override public synchronized JsonElement serialize(Date date,Type type,JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateFormat.format(date));
    }

    @Override public synchronized Date deserialize(JsonElement jsonElement,Type type,JsonDeserializationContext jsonDeserializationContext) {
        try {
            return dateFormat.parse(jsonElement.getAsString());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }
}
