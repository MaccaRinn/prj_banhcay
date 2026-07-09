package org.example.prj_banhcay.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://fe-nguvi.vercel.app", allowCredentials = "true")
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Backend is running";
    }
}
