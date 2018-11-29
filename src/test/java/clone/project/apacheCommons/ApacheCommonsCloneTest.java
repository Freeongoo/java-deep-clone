package clone.project.apacheCommons;

import clone.project.data.BaseWithDate;
import clone.project.data.DataExtendBaseWithDate;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApacheCommonsCloneTest {

    @Test
    public void cloneTest_WhenWithoutInheritance() {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        BaseWithDate clonedBaseWithDate = SerializationUtils.clone(baseWithDate);

        assertThat(baseWithDate, equalTo(clonedBaseWithDate));
    }

    @Test
    public void cloneTest_WhenWithInheritance() {
        List<String> strings = new ArrayList<>();
        strings.add("someStr");
        DataExtendBaseWithDate dataExtendBaseWithDate = new DataExtendBaseWithDate(123L, "String", new Date(123321232), 1.1F, strings);
        DataExtendBaseWithDate clonedDataExtendBaseWithDate = SerializationUtils.clone(dataExtendBaseWithDate);

        assertThat(dataExtendBaseWithDate, equalTo(clonedDataExtendBaseWithDate));
    }

    @Test
    public void cloneTest_WhenNullField() {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        baseWithDate.setId(null);
        BaseWithDate clonedBaseWithDate = SerializationUtils.clone(baseWithDate);

        assertThat(baseWithDate, equalTo(clonedBaseWithDate));
    }

    @Test
    public void cloneTest_WhenTryChangeClonedObject() {
        BaseWithDate baseWithDate = new BaseWithDate(123L, "String", new Date(123321232));
        BaseWithDate clonedBaseWithDate = SerializationUtils.clone(baseWithDate);

        clonedBaseWithDate.setId(null);

        assertThat(baseWithDate.getId(), equalTo(123L));
    }
}
