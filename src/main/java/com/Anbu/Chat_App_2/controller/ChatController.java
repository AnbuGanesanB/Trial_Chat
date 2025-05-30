package com.Anbu.Chat_App_2.controller;

import com.Anbu.Chat_App_2.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    /*@MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message){
        System.out.println(message.getSender());
        System.out.println(message.getContent());
        return message;
    }*/

    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage message){

        String user1 = message.getSender();
        String user2 = message.getRecipient();

        System.out.println(message.getSender());
        System.out.println(message.getRecipient());
        System.out.println(message.getContent());


        String chatroom = "/topic/chat/" + (user1.compareTo(user2) < 0 ? user1 + "-" + user2 : user2 + "-" + user1);

        simpMessagingTemplate.convertAndSend(chatroom,message);
    }

    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }
}
