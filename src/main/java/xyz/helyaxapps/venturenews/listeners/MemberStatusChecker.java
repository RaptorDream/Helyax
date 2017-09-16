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
package xyz.helyaxapps.venturenews.listeners;


import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.time.Instant;


public class MemberStatusChecker extends ListenerAdapter {


    @Override

    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {

        super.onUserOnlineStatusUpdate(event);

        MessageEmbed embedin = new EmbedBuilder()                                       //Embed for logged in members

                .setAuthor("Venture News", null, null)
                .setDescription("**" + event.getUser().getName() + "** has logged in")
                //.appendDescription("append")
                .setThumbnail(event.getUser().getAvatarUrl())
                //.appendDescription(null)//+ "\r")
                .setColor(Color.YELLOW)
                .setTimestamp(Instant.now())
                .build();

        MessageEmbed embedout = new EmbedBuilder()                                      //Embed for logged out members

                .setAuthor("Venture News", null, null)
                .setDescription("**" + event.getUser().getName() + "** has logged out")
                //.appendDescription("append")
                .setThumbnail(event.getUser().getAvatarUrl())
                //.appendDescription(null)//+ "\r")
                .setColor(Color.YELLOW)
                .setTimestamp(Instant.now())
                .build();


        String[] staffMemberId = {"319136230803767297", /*SkyriaS*/ "276123714859565068", "173294860063735808", "260485852935880705"};

        /*
        int count = 0;



        while (String.valueOf(event.getUser().getId()).equals(staffMemberId[count])) {
            count++;
        }*/

      for (String IdDetail : staffMemberId) {                             //Checking with all the IDs of the staff members

        if (event.getUser().getId().equals(IdDetail)) {                 //Verify if the event is caused by a member of the staff by checking IDs

            if (event.getGuild().getMemberById(IdDetail).getOnlineStatus() == OnlineStatus.OFFLINE || event.getGuild().getMemberById(IdDetail).getOnlineStatus() == OnlineStatus.INVISIBLE) {   //Verify if the current status of the member is OFFLINE or INVISIBLE

                if (event.getPreviousOnlineStatus() == OnlineStatus.ONLINE || event.getPreviousOnlineStatus() == OnlineStatus.IDLE || event.getPreviousOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {                           //Verify if the previous status of the member was ONLINE, IDLE or DO_NOT_DISTURB

                    event.getGuild().getTextChannelById("322266175717507082").sendMessage(embedout).queue();            //Sending corresponding embed
                }

            } else if (event.getGuild().getMemberById(IdDetail).getOnlineStatus() == OnlineStatus.ONLINE || event.getGuild().getMemberById(IdDetail).getOnlineStatus() == OnlineStatus.IDLE || event.getGuild().getMemberById(IdDetail).getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) { //Verify if the current status of the member is ONLINE, IDLE or DO_NOT_DISTURB

                if (event.getPreviousOnlineStatus() == OnlineStatus.OFFLINE) {                              //Verify if the previous status of the member was OFFLINE

                    event.getGuild().getTextChannelById("322266175717507082").sendMessage(embedin).queue();     //Sending corresponding embed



                } else if (event.getPreviousOnlineStatus() == OnlineStatus.INVISIBLE) {                    //Also verify if the previous status of the member was INVISIBLE


                    event.getGuild().getTextChannelById("322266175717507082").sendMessage(embedin).queue();     //Sending corresponding embed


                }
            }

        }/* else {

            event.getGuild().getTextChannelById("356075105509572608").sendMessage(event.getUser().getAsMention() + " Random Member").queue();
        }*/
    }
  }
}

