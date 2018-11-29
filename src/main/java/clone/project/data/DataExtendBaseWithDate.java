package clone.project.data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DataExtendBaseWithDate extends BaseWithDate {

    private static final long serialVersionUID = -5211572595644234603L;
    private Float someFloat;
    private List<String> list;

    public DataExtendBaseWithDate() {
    }

    public DataExtendBaseWithDate(Long id, String name, Date date, Float someFloat, List<String> list) {
        super(id, name, date);
        this.someFloat = someFloat;
        this.list = list;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DataExtendBaseWithDate dataExtendBaseWithDate = (DataExtendBaseWithDate) o;
        return Objects.equals(someFloat, dataExtendBaseWithDate.someFloat) &&
                Objects.equals(list, dataExtendBaseWithDate.list);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), someFloat, list);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataExtendBaseWithDate{");
        sb.append("someFloat=").append(someFloat);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
