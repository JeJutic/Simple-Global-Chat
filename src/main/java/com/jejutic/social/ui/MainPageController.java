package com.jejutic.social.ui;

import com.jejutic.social.domain.GlobalMessageRepositoryDelegator;
import com.jejutic.social.ui.data_objects.Message;
import com.jejutic.social.ui.data_objects.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
@SessionAttributes("userInfo")
public class MainPageController {

    private final GlobalMessageRepositoryDelegator messageRepository;

    @Autowired
    public MainPageController(GlobalMessageRepositoryDelegator messageRepository) {
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
    public String newMessage(Message message, UserInfo userInfo) {
        if (userInfo == null || userInfo.getNickname() == null) {
            return "redirect:/login";
        }
        message.setAuthor(userInfo.getNickname());
        log.info("Message : {}", message);
        messageRepository.create(message);
        return "redirect:/";
    }
}