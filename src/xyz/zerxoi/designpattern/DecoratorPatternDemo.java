package xyz.zerxoi.designpattern;

// 1. 抽象组件：需要装饰的抽象对象（接口或者抽象父类）
// 2. 具体组件：需要装饰的对象
// 3. 抽象装饰类：包含了对抽象组件的引用及其方法
// 4. 具体装饰类：装饰具体组件的类
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Drink drink = new Coffee();
        System.out.println(drink.name()+":"+drink.price());
        drink = new Sugar(drink);
        System.out.println(drink.name()+":"+drink.price());
        drink = new Milk(drink);
        System.out.println(drink.name()+":"+drink.price());
    }
}

// 抽象组件
interface Drink {
    int price();
    String name();
}

// 具体组件
class Coffee implements Drink {
    int price = 10;
    String name = "原味咖啡";
    @Override
    public int price() {
        return this.price;
    }
    @Override
    public String name() {
        return this.name;
    }
}

// 抽象装饰类
abstract class Decorator implements Drink {
    Drink drink;
    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public int price() {
        return drink.price();
    }

    @Override
    public String name() {
        return drink.name();
    }
}

// 具体装饰类
class Milk extends Decorator {
    public Milk(Drink drink) {
        super(drink);
    }

    @Override
    public int price() {
        return super.price() + 5;
    }

    @Override
    public String name() {
        return super.name() +"加奶";
    }
}

// 具体装饰类
class Sugar extends Decorator {
    public Sugar(Drink drink) {
        super(drink);
    }

    @Override
    public int price() {
        return super.price() + 3;
    }

    @Override
    public String name() {
        return super.name() +"加糖";
    }
}
