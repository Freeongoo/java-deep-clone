package clone.project.jackson;

import clone.project.data.BaseWithDate;
import clone.project.data.DataExtendBaseWithDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JacksonJsonCloneTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void cloneTest_WhenWithoutInheritance() throws IOException {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        BaseWithDate clonedBaseWithDate = objectMapper
                .readValue(objectMapper.writeValueAsString(baseWithDate), BaseWithDate.class);

        assertThat(baseWithDate, equalTo(clonedBaseWithDate));
    }

    @Test
    public void cloneTest_WhenWithInheritance() throws IOException {
        List<String> strings = new ArrayList<>();
        strings.add("someStr");
        DataExtendBaseWithDate dataExtendBaseWithDate = new DataExtendBaseWithDate(123L, "String", new Date(123321232), 1.1F, strings);
        DataExtendBaseWithDate clonedDataExtendBaseWithDate = objectMapper
                .readValue(objectMapper.writeValueAsString(dataExtendBaseWithDate), DataExtendBaseWithDate.class);

        assertThat(dataExtendBaseWithDate, equalTo(clonedDataExtendBaseWithDate));
    }

    @Test
    public void cloneTest_WhenNullField() throws IOException {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        baseWithDate.setId(null);
        BaseWithDate clonedBaseWithDate = objectMapper
                .readValue(objectMapper.writeValueAsString(baseWithDate), BaseWithDate.class);

        assertThat(baseWithDate, equalTo(clonedBaseWithDate));
    }

    @Test
    public void cloneTest_WhenTryChangeClonedObject() throws IOException {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        BaseWithDate clonedBaseWithDate = objectMapper
                .readValue(objectMapper.writeValueAsString(baseWithDate), BaseWithDate.class);

        clonedBaseWithDate.setId(null);

        assertThat(baseWithDate.getId(), equalTo(123L));
    }
}
