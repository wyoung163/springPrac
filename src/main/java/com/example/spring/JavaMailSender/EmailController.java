package com.example.spring.JavaMailSender;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService mailService;

    /*
    @GetMapping("/mail")
    public String dispMail() {
        return "mails";
    }

     */

    @RequestMapping("/mail")
    public void execMail(@RequestBody EmailDto mailDto) {
        mailService.mailSend(mailDto);
    }
}