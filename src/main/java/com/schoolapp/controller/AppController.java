package com.schoolapp.controller;

import com.schoolapp.dto.LoginRequest;
import com.schoolapp.entity.User;
import com.schoolapp.repository.UserRepository;
import com.schoolapp.service.UserServices;
import com.schoolapp.entity.Student;
import com.schoolapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserServices service;


    @GetMapping("/index")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User ());

        return "signup_form";
    }



    @PostMapping("/process_register")
    public String processRegister(User user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        service.register(user, getSiteURL(request));
        return "register_success";
    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
//    @GetMapping("/login")
//    public String  getLogin(){
//        ModelAndView getLoginPage =new ModelAndView ( "login" );
//        System.out.println ( "In the Login Page Controller" );
//        getLoginPage.addObject ("PageTitle", "Login");
//        return "login";
//    }

//    @PostMapping("/login")
//    public String authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken (loginRequest.getEmail(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        //LocalUser localUser = (LocalUser) authentication.getPrincipal();
//        //boolean authenticated = !localUser.getUser().isUsing2FA();
//        //String jwt = tokenProvider.createToken(localUser, authenticated);
//        return "redirect:/students";
//        //ResponseEntity.ok(new JwtAuthenticationResponse(jwt, authenticated, authenticated ? GeneralUtils.buildUserInfo(localUser) : null));
//    }
//



//    @PostMapping("login")
//    public String loginUser(User User, HttpServletRequest request)
//            throws UnsupportedEncodingException, MessagingException {
//
//        return "redirect:api/v1/registration";
//
//
//    }






    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (service.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> listStudents = studentService.getAllStudents();
        model.addAttribute("listStudents", listStudents );
        return "students";

    }
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        //Student student=new Student();
        model.addAttribute("student", new Student());

        return "create_student";
    }
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {

        studentService.saveStudent(student);
        return "redirect:/students";

    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";

    }

    @PostMapping("/students/{id}")
    public String updateStudent (@PathVariable Long id,
                                 @ModelAttribute("student") Student student,
                                 Model model) {
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setRegNumber (student.getRegNumber ());

        studentService.updateStudent(existingStudent);
        return "redirect:/students";

    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

}