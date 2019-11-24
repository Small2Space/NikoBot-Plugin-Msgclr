package com.github.smallru8.plugin.msgclr;

import net.dv8tion.jda.api.entities.MessageChannel;

public class AvoidBotFlooding extends ManualClear{
	
	public AvoidBotFlooding(MessageChannel ch) {
		super(ch);
	}
	
	@Override
	public void ClearBotMsg(int num) {
		
	}
}
