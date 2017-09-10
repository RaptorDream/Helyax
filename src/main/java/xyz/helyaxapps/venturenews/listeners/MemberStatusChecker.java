package xyz.helyaxapps.venturenews.listeners;



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

    }
}
