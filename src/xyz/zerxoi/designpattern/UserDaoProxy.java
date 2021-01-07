package xyz.zerxoi.designpattern;

public class UserDaoProxy implements UserDao {
    UserDao userDao;
    public UserDaoProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("代理前置处理");
        userDao.save();
        System.out.println("代理后置处理");
    }
}
