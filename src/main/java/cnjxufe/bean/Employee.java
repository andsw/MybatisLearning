package cnjxufe.bean;

/**
 * @author hsw
 * @create 2018-10-20  22:25
 */
public class Employee {
    private Integer id;
    private String name;
    private char gender;
    private String email;

    public Employee() {
    }

    public Employee(Integer id, String name, char gender, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}
