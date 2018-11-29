package clone.project.apacheCommons;

import clone.project.data.BaseData;
import clone.project.data.Data;
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
        BaseData baseData = new BaseData(123L, "String", new Date(123321232));
        BaseData clonedBaseData = SerializationUtils.clone(baseData);

        assertThat(baseData, equalTo(clonedBaseData));
    }

    @Test
    public void cloneTest_WhenWithInheritance() {
        List<String> strings = new ArrayList<>();
        strings.add("someStr");
        Data data = new Data(123L, "String", new Date(123321232), 1.1F, strings);
        Data clonedData = SerializationUtils.clone(data);

        assertThat(data, equalTo(clonedData));
    }

    @Test
    public void cloneTest_WhenTryChangeClonedObject() {
        BaseData baseData = new BaseData(123L, "String", new Date(123321232));
        BaseData clonedBaseData = SerializationUtils.clone(baseData);

        clonedBaseData.setId(null);

        assertThat(baseData.getId(), equalTo(123L));
    }
}
