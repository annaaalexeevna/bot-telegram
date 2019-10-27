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
    private static final String Description = "yes";
    private static final String[] CatchWords = {"Site", "World", "share", "reply"};

    public InlineBotKeyboard() {
        super(CommandIdentifier, Description);
    }

    private ReplyKeyboard getInlineKeyboardMarkup(String[] args) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> keyboardFirstRow = new ArrayList<>();
        if (args != null && args.length > 0) {

            if (args[0].contains(CatchWords[0])) {
                keyboardFirstRow.add(new InlineKeyboardButton("open_site").setUrl("https://www.adme.ru/tvorchestvo-dizajn/15-unikalnyh-sajtov-o-kotoryh-vy-ne-slyshali-954010/"));
            }
            if (args[0].contains(CatchWords[1])) {
                keyboardFirstRow.add(new InlineKeyboardButton("open_world").setUrl("http://geacron.com/home-en/?&sid=GeaCron472516"));
            }
            if (args[0].contains(CatchWords[2])) {
                keyboardFirstRow.add(new InlineKeyboardButton("share to friend").setSwitchInlineQuery("it is  bot"));
            }
            if (args[0].contains(CatchWords[3])) {
                keyboardFirstRow.add(new InlineKeyboardButton("hello command").setCallbackData("/hello"));
            }
        } else {
            keyboardFirstRow.add(new InlineKeyboardButton("share to friend").setSwitchInlineQuery("it is a super bot"));
            keyboardFirstRow.add(new InlineKeyboardButton("only text").setSwitchInlineQueryCurrentChat("is good boy"));
        }

        keyboard.add(keyboardFirstRow);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        SendMessage answer = new SendMessage();
        StringBuilder messageTextBuilder;
        if (arguments != null && arguments.length > 0) {
            messageTextBuilder = new StringBuilder("inline answer");
        } else {
            messageTextBuilder = new StringBuilder("inline examples");
        }
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