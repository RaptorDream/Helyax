package xyz.helyaxapps.venturenews.listeners;


<<<<<<< HEAD
import net.dv8tion.jda.*;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MemberStatusChecker extends ListenerAdapter
{


    @Override
    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {
        super.onUserOnlineStatusUpdate(event);

       if(event.getGuild().getMemberById("249225518077968384").getOnlineStatus() == OnlineStatus.OFFLINE) {
            event.getGuild().getTextChannelById("356075105509572608").sendMessage("You've gone offline!").queue();
       }
=======

import java.util.EventListener;

import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;


public class MemberStatusChecker implements EventListener
{


    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event)
    {

        if (event.getGuild().getMemberById("276123714859565068").getOnlineStatus() == OnlineStatus.OFFLINE)
        {
            event.getGuild().getTextChannelById("356075105509572608").sendMessage("oui");
        }

>>>>>>> 4a5c922e4befd5e783b758a6004fd191d178cf91
    }
}
