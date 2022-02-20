package com.example.zadanie20;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    public String list() {
        List<User> userList = userRepository.getAll();
        String result = "";
        for (User user : userList) {
            result += user.getName() + " " + user.getLastName() + " Wiek: " + user.getAge() + "</br>";
        }
        return result;
    }

    @RequestMapping(path = "add", method = {RequestMethod.GET, RequestMethod.POST})
    public String addUser(@RequestParam(name = "imie") String name, @RequestParam(name = "nazwisko") String lastName,
                              @RequestParam(name = "wiek") Integer age) {
        if (!isNameCorrect(name)) {
            return "redirect:/err.html";
        }
        User user = new User(name, lastName, age);
        userRepository.add(user);
        return "redirect:/success.html";
    }

    private boolean isNameCorrect(String name) {
        return name != null && name.length() > 1;
    }
}
