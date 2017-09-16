/*
 * Copyright 2017 DATCHANAMOURTY Rohitkumar (rdatchane.19@gmail.com) & PHIMANESONE Alex
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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


/**
 * @author RaptorDream (rdatchane.19@gmail.com)
 * @author SkyriaS
 *
 * @since 1.0.0
 */
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
