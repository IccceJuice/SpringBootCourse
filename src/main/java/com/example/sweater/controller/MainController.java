package com.example.sweater.controller;

import com.example.sweater.dao.MessageRepository;
import com.example.sweater.model.Message;
import com.example.sweater.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("main")
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepository.findAll());
        return "main";
    }

    @PostMapping("/add")
    public String add(@AuthenticationPrincipal User user,
                        @RequestParam String text,
                        @RequestParam String tag,
                        Map<String, Object> model) {
        messageRepository.save(new Message(text,tag, user));
        model.put("messages", messageRepository.findAll());
        return "main";
    }

    @PostMapping("/find")
    public String find(@RequestParam String findedTag, Map<String, Object> model) {
        model.put("messages", messageRepository.findAllByTag(findedTag));
        return "main";
    }

}
