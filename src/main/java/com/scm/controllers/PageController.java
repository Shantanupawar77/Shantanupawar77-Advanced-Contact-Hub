package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        // sending data to view
       
        model.addAttribute("githubRepo", "/images/default.jpg");
        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // services

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }

    // contact page

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // this is showing login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    // registration page
    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        // default data bhi daal sakte hai
        // userForm.setName("Durgesh");
        // userForm.setAbout("This is about : Write something about yourself");
        model.addAttribute("userForm", userForm);

        return "register";
    }

    // processing register

    // @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    // public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
    //         HttpSession session) {
    //     System.out.println("Processing registration");
    //     // fetch form data
    //     // UserForm
    //     System.out.println(userForm);

    //     // validate form data
    //     if (rBindingResult.hasErrors()) {
    //         return "register";
    //     }

    //     User user = new User();
    //     user.setName(userForm.getName());
    //     user.setEmail(userForm.getEmail());
    //     user.setPassword(userForm.getPassword());
    //     user.setAbout(userForm.getAbout());
    //     user.setPhoneNumber(userForm.getPhoneNumber());
    //     user.setEnabled(true);
    //     user.setProfilePic(
    //             "/images/default.jpg");

    //     User savedUser = userService.saveUser(user);

    //     System.out.println("user saved :");

    //     // message = "Registration Successful"

    //     // add the message:

    //     Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

    //     session.setAttribute("message", message);

    //     // redirectto login page
    //     return "redirect:/register";
    // }
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
        HttpSession session, Model model) {
    System.out.println("Processing registration");

    if (rBindingResult.hasErrors()) {
        return "register";
    }

    try {
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(true);
        user.setProfilePic("/images/default.jpg");

        User savedUser = userService.saveUser(user);
        System.out.println("User saved:");

        // Add success message to session
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);

        return "redirect:/register";

    } catch (DataIntegrityViolationException e) {
        // Handle duplicate entry error
        System.out.println("Duplicate entry error: " + e.getMessage());

        Message errorMessage = Message.builder()
                .content("Email already exists. Please use a different email.")
                .type(MessageType.red)
                .build();
        session.setAttribute("message", errorMessage);

        // Re-add the userForm object to the model to preserve form data
        model.addAttribute("userForm", userForm);
        
        return "register";
    }
}

}
