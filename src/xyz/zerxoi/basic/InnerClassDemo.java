package xyz.zerxoi.basic;

public class InnerClassDemo {
    int id = 1;

    class Inner {
        int id = 2;
        public void show() {
            int id = 3;
            System.out.println("外部类成员变量id:"+InnerClassDemo.this.id);
            System.out.println("内部类成员变量id:"+this.id);
            System.out.println("局部变量id:"+id);
        }
    }
    
    public static void main(String[] args) {
        InnerClassDemo outter = new InnerClassDemo();
        Inner inner = outter.new Inner();
        inner.show();
    }
}
