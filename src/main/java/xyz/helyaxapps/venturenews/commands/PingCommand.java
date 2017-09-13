package xyz.helyaxapps.venturenews.commands;


import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;



public class PingCommand extends ListenerAdapter
{


    private long system_time;


    @Override

    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getMessage().getRawContent().equalsIgnoreCase("vn:ping"))
        {
            system_time = System.currentTimeMillis();

            event.getChannel().sendMessage("Pong!").queue( message -> message.editMessageFormat(" :ping_pong:  Pong! ``%dms``", System.currentTimeMillis() - system_time).complete());
        }
    }




}
