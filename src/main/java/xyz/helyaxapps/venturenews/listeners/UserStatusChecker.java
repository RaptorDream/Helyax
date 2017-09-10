package xyz.helyaxapps.venturenews.listeners;

import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UserStatusChecker extends ListenerAdapter
{


    @Override
    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {

        if (event.getUser().getId().equals("249225518077968384") || event.getUser().getId().equals("276123714859565068"))
        {
            event.getGuild().getTextChannelById("356075131430240257").sendMessage("You went AFK!").queue();
        }
    }


}
