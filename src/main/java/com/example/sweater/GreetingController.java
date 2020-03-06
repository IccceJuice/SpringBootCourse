package com.example.sweater;

import com.example.sweater.dao.MessageRepository;
import com.example.sweater.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepository.findAll());
        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam String text, @RequestParam String tag,
            Map<String, Object> model) {
        messageRepository.save(new Message(text,tag));
        model.put("messages", messageRepository.findAll());
        return "main";
    }

    @PostMapping("find")
    public String find(@RequestParam String findedTag, Map<String, Object> model) {
        model.put("messages", messageRepository.findAllByTag(findedTag));
        return "main";
    }

}
