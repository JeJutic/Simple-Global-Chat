package com.jejtuic.web_service.ui;

import com.jejtuic.web_service.ui.data_objects.Message;
import com.jejtuic.web_service.ui.data_objects.UserInfo;
import com.jejtuic.web_service.domain.GlobalMessageRepositoryProcessor;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
@SessionAttributes("userInfo")
public class MainPageController {

    private final GlobalMessageRepositoryProcessor messageRepository;

    @Autowired
    public MainPageController(GlobalMessageRepositoryProcessor messageRepository) {
        this.messageRepository = messageRepository;
    }

    @ModelAttribute(name = "messages")
    public List<Message> messages() {
        return messageRepository.findAll();
    }

    @ModelAttribute(name = "messageField")
    public Message messageField(UserInfo userInfo) {
        log.info("UserInfo : {}", userInfo);
        return new Message("", userInfo.getNickname());
    }

    @GetMapping
    public String mainPage() {
        return "main_page";
    }

    @PostMapping
    public String newMessage(Message message, @Valid UserInfo userInfo, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/login";
        }
        message.setAuthor(userInfo.getNickname());
        log.info("Message : {}", message);
        messageRepository.create(message);
        return "redirect:/";
    }
}