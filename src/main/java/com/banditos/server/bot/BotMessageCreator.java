package com.banditos.server.bot;

import com.banditos.server.model.Tusovka;
import com.banditos.server.orm.TusovkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotMessageCreator {

    private static TusovkaRepository tusovkaRepository;

    @Autowired
    public BotMessageCreator(TusovkaRepository tusovkaRepository) {
        this.tusovkaRepository = tusovkaRepository;
    }

    public static SendMessage createTusovkasMessage(Long id) {
        Iterable<Tusovka> tusovkas = tusovkaRepository.findAll();
        SendMessage response = new SendMessage();
        response.setChatId(id);

        StringBuilder sb = new StringBuilder();
        for (Tusovka t : tusovkas) {
            sb.append(t.getName() + "\n");
            sb.append(t.getPrice() + "\n");
            sb.append(t.getLink() + "\n");
        }
        response.setText(sb.toString());

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("next");
        row.add("previous");

        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);

        response.setReplyMarkup(keyboardMarkup);
        return response;
    }
}
