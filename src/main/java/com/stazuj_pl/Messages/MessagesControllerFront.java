package com.stazuj_pl.Messages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="messages")
public class MessagesControllerFront {
    @GetMapping(value="/send_message")
    public String allUsers(){
        return "messages/send_message";
    }
}
