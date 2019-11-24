package com.github.smallru8.plugin.msgclr;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.github.smallru8.NikoBot.Core;
import com.github.smallru8.NikoBot.event.Event.MessageEvent;
import com.github.smallru8.NikoBot.plugins.PluginsInterface;

public class MsgClr implements PluginsInterface{

	public static AutoClear ac;
	public void onDisable() {
		// TODO Auto-generated method stub
		EventBus.getDefault().unregister(this);
	}

	public void onEnable() {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
		ac = new AutoClear();
	}
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageRecved(MessageEvent e) {
		if(ac.clear(e.msg))
			System.out.println("[INFO][PLUGIN][MSGCLEAR]:Auto clear a cmd message.");
		if(!e.msg.getAuthor().isBot()) {
			if(e.msg.getContentRaw().startsWith("/msgclr off")&&Core.PM.userId(e.msg)) 
				ac.flag = false;
			else if(e.msg.getContentRaw().startsWith("/msgclr on")&&Core.PM.userId(e.msg))
				ac.flag = true;
			else if(e.msg.getContentRaw().startsWith("/clr")&&Core.PM.userId(e.msg)) {
				String[] cmd = e.msg.getContentRaw().split(" ");
				if(cmd.length>=2) {
					ManualClear mc = new ManualClear(e.msg.getChannel());
					int n;
					try {
						n = Integer.parseInt(cmd[1]);
					}catch(NumberFormatException nfe) {
						e.msg.getChannel().sendMessage("Invlid argument.").queue();
						n = 0;
					}
					mc.ClearBotMsg(n);
				}
			}
		}
	}
	public String pluginsName() {
		// TODO Auto-generated method stub
		return "MsgClear";
	}

}
