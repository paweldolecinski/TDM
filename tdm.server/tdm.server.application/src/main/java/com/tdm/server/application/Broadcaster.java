package com.tdm.server.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.tdm.domain.model.problem.ProblemId;

@Service
public class Broadcaster {

	public enum Message {
		NEW_EXPERT, NEW_RESULT,
	}

	private ChannelService channelService = ChannelServiceFactory
			.getChannelService();
	private MemcacheService memcache = MemcacheServiceFactory
			.getMemcacheService();

	public void publish(ProblemId problemId, Message msg) {

		@SuppressWarnings("unchecked")
		List<String> tokens = (List<String>) memcache.get(problemId
				.getIdString() + "_channel");
		if (tokens == null) {
			return;
		}
		for (String token : tokens) {
			channelService.sendMessage(new ChannelMessage(token, msg.name()));
		}
	}

	public String subscribe(ProblemId problemId, String userName) {
		String channelName = problemId.getIdString() + "_channel";
		String token = channelService.createChannel(problemId.getIdString()
				+ "$" + userName);
		@SuppressWarnings("unchecked")
		List<String> tokens = (List<String>) memcache.get(channelName);
		if (tokens == null) {
			tokens = new ArrayList<String>();
		}
		tokens.add(token);
		memcache.put(channelName, tokens);
		return token;
	}
}
