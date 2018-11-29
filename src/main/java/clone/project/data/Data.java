package clone.project.data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Data extends BaseData {

    private Float someFloat;
    private List<String> list;

    public Float getSomeFloat() {
        return someFloat;
    }

    public void setSomeFloat(Float someFloat) {
        this.someFloat = someFloat;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Data(Long id, String name, Date date, Float someFloat, List<String> list) {
        super(id, name, date);
        this.someFloat = someFloat;
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Data data = (Data) o;
        return Objects.equals(someFloat, data.someFloat) &&
                Objects.equals(list, data.list);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), someFloat, list);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Data{");
        sb.append("someFloat=").append(someFloat);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
