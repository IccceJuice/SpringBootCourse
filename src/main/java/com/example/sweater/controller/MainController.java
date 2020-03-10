package com.example.sweater.controller;

import com.example.sweater.dao.MessageRepository;
import com.example.sweater.model.Message;
import com.example.sweater.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String findedTag, Model model) {
        Iterable<Message> messages = messageRepository.findAll();

        if (findedTag == null || findedTag.isEmpty()) {
            messages =  messageRepository.findAll();
        }
        else {
            messages =  messageRepository.findAllByTag(findedTag);
        }
        model.addAttribute("messages", messages);
        model.addAttribute("findedTag", findedTag);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                        @RequestParam String text,
                        @RequestParam String tag,
                        @RequestParam("file") MultipartFile file,
                        Map<String, Object> model) throws IOException {
        Message message = new Message(text,tag, user);

        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "\\" + resultFileName));
            message.setFilename(resultFileName);
            model.put("uploadPath", uploadPath + "\\");
        }
        messageRepository.save(message);
        model.put("messages", messageRepository.findAll());
        return "main";
    }
}
