package xyz.helyaxapps.venturenews;


import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.json.JSONObject;
import xyz.helyaxapps.venturenews.commands.PingCommand;
import xyz.helyaxapps.venturenews.listeners.MemberStatusChecker;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;



public class Main
{


    private static JDA jda;
    private static String config;


    public static void main(String[] args)
    {

        System.out.println(Instant.now() + " Starting...");
        
        try
        {
            config = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator + "conf" + File.separator + "bot.json")));
        } catch (IOException e) {
        //In case an error occurs while reading the file
        System.out.println("JSON file read failed");
        e.printStackTrace();
        }

        JSONObject configData = new JSONObject(config);

        final String BOT_TOKEN = configData.getString("botToken");    //All of the "config" data stored in a JSON object
        final String BOT_GAME = configData.getString("game");
        final String BOT_VERSION = configData.getString("botVersion");
        final String BOT_PREFIX = configData.getString("botPrefix");

        try
        {

            jda = new JDABuilder(AccountType.BOT)
                    .addEventListener(new PingCommand(), new MemberStatusChecker())
                    .setToken(BOT_TOKEN)
                    .setGame(Game.of(BOT_GAME))
                    .buildAsync();

            System.out.println(Instant.now().toString());
            System.out.println("APP STATUS: RUNNING UNDER VERSION: " + BOT_VERSION);     //Version of the running bot
            System.out.println("INVITE LINK: " + getJda().asBot().getInviteUrl());       //Invite URL
            System.out.println("BOT PREFIX: \" " + BOT_PREFIX + " \"");                  //Bot Prefix set by devs'
            System.out.println("APP INFO: " + getJda().asBot().getApplicationInfo());

        } catch (LoginException | IllegalArgumentException | RateLimitedException e) {


            System.out.println(Instant.now() + " Failed to build a new instance of JDA");
            System.out.println(Instant.now().toString() + " Failed to build a new instance of JDA");
            e.printStackTrace();
        }


    }

    /**
     *@return The JDA object instance used by the bot
     */
    private static JDA getJda()
    {
        return jda;
    }

}
