package com.banditos.server.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    @Override
        public String getBotToken() {
            try {
                String token = new BufferedReader(
                        new FileReader(
                                new File(  "src/main/resources/telegramApiToken")))
                        .readLine();
                return token;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public void onClosing() {
        logger.info("closing");
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("update");

        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(chatId);
            String text = message.getText();
            response.setText(text);
            try {
                execute(response);
                logger.info("Sent message \"{}\" to {}", text, chatId);
            } catch (TelegramApiException e) {
                logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
            }
        }
        logger.info(update.toString());
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        logger.info("updates received");
        for (Update update : updates) {
            onUpdateReceived(update);
        }

    }

    @Override
    public String getBotUsername() {
        // note that its unique username is eventSfinderbot
        return "eventfinder";
    }
}
