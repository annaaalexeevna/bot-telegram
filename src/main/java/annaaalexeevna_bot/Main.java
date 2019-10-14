package annaaalexeevna_bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Main extends TelegramLongPollingBot {

    public Main(DefaultBotOptions botOptions) {
        super(botOptions);
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

    private static final String BOT_USERNAME = "AnniesFirstBot";
    private static final String BOT_TOKEN = "923328848:AAGLEjP2PPknu4eeZ4WeOhhlEVowvzzHW1k";

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage;
        switch (message.getText()) {
            case "/start":
                sendMessage = new SendMessage(message.getChatId(), "Available commands: Hello; How are you?");
                break;
            case "Hello":
                sendMessage = new SendMessage(message.getChatId(),
                        "Hello, " + update.getMessage().getFrom().getFirstName());
                break;
            case "How are you?":
                sendMessage = new SendMessage(message.getChatId(), "I'm fine");
                break;
            default:
                sendMessage = new SendMessage(message.getChatId(), message.getText());
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {

        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {

        return BOT_TOKEN;
    }
}
