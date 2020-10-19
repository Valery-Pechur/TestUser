package testUserProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testUserProject.dao.UsersDAOInterface;
import testUserProject.model.Users;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UsersDAOInterface usersDAO;

    @Autowired
    public UserController(@Qualifier("usersDAOClass") UsersDAOInterface usersDAO) {
        this.usersDAO = usersDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users",usersDAO.getAll());
        return "users/index";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user",new Users());
        return "users/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") Users user){

        usersDAO.save(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user",usersDAO.get(id));
        return "users/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,  Model model){
        model.addAttribute("user",usersDAO.get(id));
        return "users/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") Users user) {

        usersDAO.update(user);

        return "redirect:/users";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,  Model model){
        model.addAttribute("user",usersDAO.delete(id));
        return "redirect:/users";
    }

}
