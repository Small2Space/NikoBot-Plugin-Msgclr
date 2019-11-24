package com.github.smallru8.plugin.msgclr;

import java.util.ArrayList;

import com.github.smallru8.NikoBot.CfgReader;
import com.github.smallru8.NikoBot.Core;

import net.dv8tion.jda.api.entities.Message;

public class AutoClear {
	
	public boolean flag = true;
	ArrayList<String> clrLs;
	public AutoClear() {
		clrLs = new ArrayList<String>();
		searchCmd();
	}
	private void searchCmd() {
		for(String s:Core.pluginsMGR.helpLs) {
			s = s.replaceAll(" ", "");
			String[] stmpa = s.split("\n");
			for(String s2:stmpa) {
				if(s2.startsWith("/")) {
					clrLs.add(s2.split(":")[0]);
				}
			}
		}
		String[] sa = CfgReader.readCfg("cfg/help");
		for(String s:sa) {
			s = s.replaceAll("	", "");
			s = s.replaceAll(" ", "");
			if(s.startsWith("/")) {
				clrLs.add(s.split(":")[0]);
			}
		}
	}
	public boolean clear(Message msg) {
		if(flag) {
			String cmdHead = msg.getContentRaw().split(" ")[0];
			if(cmdHead.startsWith("/"))
				for(String s:clrLs) {
					if(s.startsWith(cmdHead)) {
						msg.getChannel().deleteMessageById(msg.getId()).queue();
						return true;
					}
				}
		}
		return false;
	}
}
