/*
 * eGovFrame Web Messager
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author 이영지(슈퍼개발자K3)
 */
package egovframework.com.ext.msg.server;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import egovframework.com.ext.msg.server.config.ChatServerAppConfig;
import egovframework.com.ext.msg.server.model.ChatMessage;
import egovframework.com.ext.msg.server.model.Message;
import egovframework.com.ext.msg.server.model.UsersMessage;
import egovframework.com.ext.msg.server.model.decoder.MessageDecoder;
import egovframework.com.ext.msg.server.model.encoder.MessageEncoder;

import javax.annotation.Resource;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
 * configurator = ChatServerAppConfig.class 웹소켓 EndPoint에서 서비스를 호출하고자 할 경우 선언 필수
 */
@ServerEndpoint(value = "/chat/{room}", encoders = { MessageEncoder.class }, decoders = { MessageDecoder.class }, configurator = ChatServerAppConfig.class)
public class ChatServerEndPoint {
	
//	@Resource("testService")
//	public TestService testService;
//	@Autowired
//	@Qualifier
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatServerEndPoint.class);
	private Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());

	/**
	 * Handshaking 함수
	 * 
	 * @param userSession 사용자 session
	 */
	@OnOpen
	public void handleOpen(Session userSession, @PathParam("room") final String room) throws IOException, EncodeException {
		userSession.getUserProperties().put("room", room);
		chatroomUsers.add(userSession);
	}

	/**
	 * 메시지 전달 함수
	 *
	 * @param incomingMessage 들어오는 메시지
	 * @param userSession     사용자 session
	 * @param room            room Id
	 * @throws IOException
	 * @throws EncodeException
	 */
	@OnMessage
	public void handleMessage(Message incomingMessage, Session userSession, @PathParam("room") final String room) throws IOException, EncodeException {

		ChatMessage incomingChatMessage = (ChatMessage) incomingMessage;
		ChatMessage outgoingChatMessage = new ChatMessage();

		String username = (String) userSession.getUserProperties().get("username");
		if (username == null) {

			username = incomingChatMessage.getMessage();
			if (username != null) {
				userSession.getUserProperties().put("username", username);
			}

			synchronized (chatroomUsers) {
				for (Session session : chatroomUsers) {
					session.getBasicRemote().sendObject(new UsersMessage(getUsers()));
				}
			}
		} else {
			outgoingChatMessage.setName(username);
			outgoingChatMessage.setMessage(incomingChatMessage.getMessage());

			for (Session session : chatroomUsers) {
				session.getBasicRemote().sendObject(outgoingChatMessage);
			}
		}
	}

	// 누군가가 접속 끊을때
	@OnClose
	public void handleClose(Session userSession, @PathParam("room") final String room) throws IOException, EncodeException {
		chatroomUsers.remove(userSession);

		for (Session session : chatroomUsers) {
			session.getBasicRemote().sendObject(new UsersMessage(getUsers()));
		}
	}

	/**
	 * 사용자가 접속 끊기 전 호출되는 함수
	 *
	 * @param session
	 * @param throwable
	 * @param room
	 */
	@OnError
	public void handleError(Session session, Throwable throwable, @PathParam("room") final String room) {
		// Error handling
		LOGGER.info("ChatServerEndPoint (room: " + room + ") occured Exception!");
		LOGGER.info("Exception : " + throwable.getMessage());
	}

	/**
	 * 사용자 정보를 가져오는 함수
	 *
	 * @return
	 */
	private Set<String> getUsers() {
		HashSet<String> returnSet = new HashSet<String>();

		for (Session session : chatroomUsers) {
			if (session.getUserProperties().get("username") != null) {
				returnSet.add(session.getUserProperties().get("username").toString());
			}
		}
		return returnSet;
	}

}
