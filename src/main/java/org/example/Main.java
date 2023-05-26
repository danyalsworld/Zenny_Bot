package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.listeners.MyListener;

import javax.security.auth.login.LoginException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main extends ListenerAdapter {
    public static void main(String[] args) {
        String botToken = System.getenv("ZENNY_TOKEN");
        JDABuilder builder = JDABuilder.createDefault(botToken);
        builder.addEventListeners(new MyListener())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT);

        builder.build();
    }

}