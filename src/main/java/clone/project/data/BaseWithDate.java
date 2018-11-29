package clone.project.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BaseWithDate implements Serializable {

    private static final long serialVersionUID = -6349983832172906810L;

    private Long id;
    private String name;
    private Date date;

    public BaseWithDate() {
    }

    public BaseWithDate(Long id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseWithDate baseWithDate = (BaseWithDate) o;
        return Objects.equals(id, baseWithDate.id) &&
                Objects.equals(name, baseWithDate.name) &&
                Objects.equals(date, baseWithDate.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, date);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseWithDate{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
