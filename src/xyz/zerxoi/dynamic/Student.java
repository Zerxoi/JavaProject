package xyz.zerxoi.dynamic;

@StudentTable("t_student")
public class Student {
    @StudentField(column = "id", type = "int", length = 10)
    private int id;
    @StudentField(column = "sname", type = "varchar", length = 10)
    private String name;
    @StudentField(column = "age", type = "int", length =3)
    private int age;

    public Student() {
        id = 10001;
        name = "zerxoi";
        age = 18;
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}