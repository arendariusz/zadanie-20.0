package com.example.zadanie20;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @RequestMapping("/users")
    public String list() {
        List<User> userList = userRepository.getAll();
        String result = "";
        for (User user : userList) {
            result += user.getName() + " " + user.getLastName() + " Wiek: " + user.getAge() + "</br>";
        }
        return result;
    }

    @RequestMapping("/add")
    public String addFromParam(@RequestParam(name = "imie") String name, @RequestParam(name = "nazwisko") String lastName,
                               @RequestParam(name = "wiek") Integer age) {
        if (isNameCorrect(name)) {
            User user = new User(name, lastName, age);
            userRepository.add(user);
            return "success.html";
        }
        return "err.html";
    }

    @RequestMapping("/addForm")
    public String addFromForm(@RequestParam String name,
                              @RequestParam String lastName,
                              @RequestParam Integer age) {
        if (!isNameCorrect(name)) {
            return "redirect:/err.html";
        }
        User user = new User(name, lastName, age);
        userRepository.add(user);
        return "redirect:/success.html";
    }

    private boolean isNameCorrect(String name) {
        if (name == null || name.length() <= 1) {
            return false;
        }
        return true;
    }
}
