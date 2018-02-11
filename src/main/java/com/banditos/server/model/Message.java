package com.banditos.server.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="message_log")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private String messageText;

    private Date sendTime;

    // private Object state;

    public Message(Long chatId, String messageText, Date sendTime) {
        this.chatId = chatId;
        this.messageText = messageText;
        this.sendTime = sendTime;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
