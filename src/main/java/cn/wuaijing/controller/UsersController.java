package cn.wuaijing.controller;

import cn.wuaijing.bean.User;
import cn.wuaijing.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    @Autowired()
    private UsersService usersService;

    @RequestMapping("/selectUsers")
    public String selectUsers(Map<String,Object> map){
     List<User> users =  usersService.getUsers();
        System.out.println("-------------------------");
        for (User u: users
             ) {
            System.out.println(u);
        }
     map.put("users",users);
     return "list";
    }
}

