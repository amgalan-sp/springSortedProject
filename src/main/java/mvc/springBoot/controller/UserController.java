package mvc.springBoot.controller;
import mvc.springBoot.repository.UserRepository;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import mvc.springBoot.entity.User;
//import mvc.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String allUsers(
        @RequestParam(defaultValue = "id") String sortby, Model model) {
        model.addAttribute("usersList", userRepository.findAll(Sort.by(sortby)));
        return "users";
    }

    @GetMapping("/sorted")
    public String allUsersSortedByAge(Model model) {
//        userRepository.createTable();
        userRepository.flush();
        return "usersSorted";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "addPage";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addPage";
        }
        userRepository.saveAndFlush(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "editPage";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "editPage";
        }
        userRepository.save(user);
        return "redirect:/users";
    }
    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/";
    }
}