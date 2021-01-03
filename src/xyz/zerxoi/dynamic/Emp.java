package xyz.zerxoi.dynamic;
@Author(name = "zerxoi", year = 2020)
public class Emp {
    private int empno;
    private String name;
    private int age;

    public Emp() {

    }

    public Emp(int empno, String name, int age) {
        this.empno = empno;
        this.name = name;
        this.age = age;
    }

    public int getEmpno() {
        return empno;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
