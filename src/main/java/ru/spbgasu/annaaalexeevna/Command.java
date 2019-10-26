package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Command extends BotCommand {

    public Command(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        long result_sum = 0;
        long add = 0;
        SendMessage sendMessage;
        for (int i = 0; i < arguments.length; i++) {
            try {
                add = Integer.parseInt(arguments[i]);
            }
            catch (NumberFormatException nfe) {
                sendMessage = new SendMessage(chat.getId(), "invalid arguments");
                try {
                    absSender.execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            result_sum = result_sum + add;
        }
        sendMessage = new SendMessage(chat.getId(), String.valueOf(result_sum));
        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
