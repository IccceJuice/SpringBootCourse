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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
                        @Valid Message message,
                        BindingResult bindingResult,
                        @RequestParam("file") MultipartFile file,
                        Model model) throws IOException {

        message.setAuthor(user);
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            model.addAttribute("message", message);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "\\" + resultFileName));
                message.setFilename(resultFileName);
            }
            messageRepository.save(message);
        }
        model.addAttribute("uploadPath", uploadPath + "\\");
        model.addAttribute("messages", messageRepository.findAll());
        return "main";
    }
}
