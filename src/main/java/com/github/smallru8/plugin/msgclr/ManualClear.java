package com.github.smallru8.plugin.msgclr;

import java.util.List;

import com.github.smallru8.NikoBot.Core;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;

public class ManualClear {
	
	protected int MAX_RETRIEVE_SIZE = 20;
	protected MessageChannel ch;
	protected List<Message> retrieved;
	public ManualClear(MessageChannel ch) {
		this.ch = ch;
	}
	public void ClearBotMsg(int num) {
		if(num>0) {
			MessageHistory mh= ch.getHistory();
			if(num>MAX_RETRIEVE_SIZE)
				num=MAX_RETRIEVE_SIZE;
			retrieved = mh.retrievePast(num).complete();
			while(!retrieved.isEmpty()) {
				if(retrieved.get(0).getAuthor().getId().equals(Core.botAPI.getSelfUser().getId())) {
					ch.deleteMessageById(retrieved.get(0).getId()).queue();
					System.out.println("[INFO][PLUGIN][MSGCLEAR]:Auto clear a bot message.");
				}
				retrieved.remove(0);
			}
		}
	}
	
}
