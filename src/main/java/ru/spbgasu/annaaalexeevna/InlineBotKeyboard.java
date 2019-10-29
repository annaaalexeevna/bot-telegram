package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

class InlineBotKeyboard extends BotCommand {

    private static final String CommandIdentifier = "inline";
    private static final String Description = "buttons for inline";

    public InlineBotKeyboard() {
        super(CommandIdentifier, Description);
    }

    private ReplyKeyboard getInlineKeyboardMarkup(String[] args) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> keyboardFirstRow = new ArrayList<>();

        keyboardFirstRow.add(new InlineKeyboardButton("Yes").setCallbackData("My ex-wife still misses me... But her aim is getting better!"));
        keyboardFirstRow.add(new InlineKeyboardButton("No.").setCallbackData("Oh! Really?"));
        keyboard.add(keyboardFirstRow);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        SendMessage answer = new SendMessage();
        StringBuilder messageTextBuilder;
        messageTextBuilder = new StringBuilder("Wanna hear a joke?");
        answer.setChatId(chat.getId().toString());
        answer.setText(messageTextBuilder.toString());
        answer.setReplyMarkup(getInlineKeyboardMarkup(arguments));
        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}