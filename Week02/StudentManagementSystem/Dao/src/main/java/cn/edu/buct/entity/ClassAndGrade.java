package cn.edu.buct.entity;

public class ClassAndGrade {
    private Integer id;

    private String cName;

    private String year;

    private Integer amount;

    private String state;

    @Override
    public String toString() {
        return "ClassAndGrade{" +
                "id=" + id +
                ", cName='" + cName + '\'' +
                ", year='" + year + '\'' +
                ", amount=" + amount +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}