package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User ivan = new User("Ivan", "Ivanov", "ivan@mail.ru");
      User petr = new User("Petr", "Sidorov", "petr@mail.ru");
      User danil = new User("Danil", "Boynextdorov", "danil@mail.ru");
      User alexandr = new User("Alexandr", "Kek", "kek@mail.ru");

      Car lada = new Car("Lada", 10);
      Car volvo = new Car("Volvo", 14);
      Car bmw = new Car("BMW", 11);
      Car toyota = new Car("Toyota", 8);

      ivan.setCar(lada);
      petr.setCar(volvo);
      danil.setCar(bmw);
      alexandr.setCar(toyota);

      userService.add(ivan);
      userService.add(petr);
      userService.add(danil);
      userService.add(alexandr);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user + " "+ user.getCar());
      }

      System.out.println(userService.getUserByCar("toyota", 8));

      context.close();
   }
}
