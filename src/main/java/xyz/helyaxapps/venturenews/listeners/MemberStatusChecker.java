package xyz.helyaxapps.venturenews.listeners;


import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;



public class MemberStatusChecker extends ListenerAdapter
{


    @Override

    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event)
    {

        int count;
        super.onUserOnlineStatusUpdate(event);

        String[] staffMemberId = {"249225518077968384", "276123714859565068"};

        count = 0;
        while (String.valueOf(event.getUser().getId()).equals(staffMemberId[count]))
        {
            count++;
        }

        if (event.getGuild().getMemberById(staffMemberId[count]).getOnlineStatus() == OnlineStatus.OFFLINE)
        {
            event.getGuild().getTextChannelById("356075105509572608").sendMessage(event.getUser().getAsMention() + " You've gone offline").queue();
        }
        else if (event.getGuild().getMemberById(staffMemberId[count]).getOnlineStatus() != OnlineStatus.OFFLINE)
        {
            event.getGuild().getTextChannelById("356075105509572608").sendMessage(event.getUser().getAsMention() + " You've gone online").queue();
        }
    }


}