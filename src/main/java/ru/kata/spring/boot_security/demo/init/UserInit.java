package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserInit {

    private final UserService userService;

    @Autowired
    public UserInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init(){

        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        List<Role> adminRoleList = new ArrayList<>();
        adminRoleList.add(adminRole);
        List<Role> userRoleList = new ArrayList<>();
        userRoleList.add(userRole);

        User admin = new User("Марков",31,"Роман","admin","admin",adminRoleList);
        User user = new User("Иванов",26,"Иван","user","user",userRoleList);

        userService.saveUser(admin);
        userService.saveUser(user);
    }
}
