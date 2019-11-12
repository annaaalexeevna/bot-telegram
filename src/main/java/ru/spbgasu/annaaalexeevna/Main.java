package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Main extends TelegramLongPollingCommandBot {

    private static final String BOT_USERNAME = "AnniesFirstBot";

    public Main(DefaultBotOptions botOptions) {
        super(botOptions, BOT_USERNAME);
        register(new Command("add", "numbers to add"));
        register(new ReplyBotKeyboard());
        register(new InlineBotKeyboard());
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost("177.8.226.254");
            botOptions.setProxyPort(8080);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
            telegramBotsApi.registerBot(new Main(botOptions));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void processNonCommandUpdate(Update update) {
        SendMessage sendMessage = null;
        String textMessage = null;

        if (update.hasMessage()) {
            Message message = update.getMessage();
            switch (message.getText()) {
                case "/start":
                    textMessage = "Available commands: Hello; How are you?; Command; /add; /reply; /inline";
                    break;
                case "Hello":
                    textMessage = "Hello, " + update.getMessage().getFrom().getFirstName();
                    break;
                case "How are you?":
                    textMessage = "I'm fine";
                    break;
                case "Command":
                    textMessage = "/add You can add arguments";
                    break;
                default:
                    textMessage = message.getText();
            }
            sendMessage = new SendMessage(message.getChatId(), textMessage);
        } else if (update.hasCallbackQuery()) {
            sendMessage = new SendMessage()
                    .setText(update.getCallbackQuery().getData())
                    .setChatId(update.getCallbackQuery().getMessage().getChatId());
        }
        if (sendMessage != null) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotToken() {

        return System.getenv("BOT_TOKEN");
    }
}
