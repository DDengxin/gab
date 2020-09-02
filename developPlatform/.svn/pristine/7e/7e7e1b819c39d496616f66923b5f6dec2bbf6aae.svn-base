package com.tengzhi.IM.websocket.server;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.tengzhi.IM.Dwr.controller.Dwrmsg;
import com.tengzhi.IM.business.message.model.SysImMessage;
import com.tengzhi.IM.business.message.service.SysImMessageService;
import com.tengzhi.base.security.common.tool.spring.SpringUtil;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lqy websoket 推送消息，接收消息
 * @param <Map>
 */
@ServerEndpoint("/imserver/{userId}")
@Component
public class WebSocketServer<Map> {
	static Log log = LogFactory.get(WebSocketServer.class);
	private static int onlineCount = 0;
	private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
	private Session session;
	private String userId = "";

	public static synchronized ConcurrentHashMap<String, WebSocketServer> getWebSocketMap() {
		return webSocketMap;
	}

	public static boolean getflag(String userid) {
		return webSocketMap.containsKey(userid);
	}

	private SysImMessageService sysImMessageService;

	/**
	 * 设置用户在线状态
	 * @param userid 用户ID
	 * @param flag 状态
	 * @throws IOException
	 */
	private void setOnlineState(String userid, String flag) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.putOpt("type", "3");
		jsonObject.putOpt("userid", userid);
		jsonObject.putOpt("flag", flag);
		ConcurrentHashMap<String, ScriptSession> dwrsession = Dwrmsg.getJavascriptSessionMap();
		ConcurrentHashMap<String, WebSocketServer> webSocketMap = WebSocketServer.getWebSocketMap();
		for (Entry<String, WebSocketServer> entry : webSocketMap.entrySet()) {
			webSocketMap.get(entry.getKey()).sendMessage(jsonObject.toJSONString(1));
		}
		// 群消息ie9以下的用户
		for (Entry<String, ScriptSession> entry : dwrsession.entrySet()) {
			ScriptBuffer script = new ScriptBuffer();
			script.appendCall("setOnlineState", jsonObject.toJSONString(1));
			dwrsession.get(entry.getKey()).addScript(script);
		}
	}

	/**
	 * 连接建立成功调用的方法，记录websokect在线人数，后期推送需要
	 * 
	 * @param session
	 * @param userId
	 * @throws IOException
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("userId") String userId) throws IOException {
		this.session = session;
		this.userId = userId;
		setOnlineState(userId, "online");
		if (webSocketMap.containsKey(userId)) {
			webSocketMap.remove(userId);
			webSocketMap.put(userId, this);
		} else {
			webSocketMap.put(userId, this);
			addOnlineCount();
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @throws IOException
	 * 
	 */
	@OnClose
	public void onClose() throws IOException {
		if (webSocketMap.containsKey(userId)) {
			webSocketMap.remove(userId);
			subOnlineCount();
			setOnlineState(userId, "offline");
		}
	}

	/**
	 * 接收
	 * 
	 * @param message 消息
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		if (StringUtils.isNotBlank(message)) {
			try {
				JSONObject jsonObject = JSONUtil.parseObj(message);
				jsonObject.putOpt("fromUserId", this.userId);
				String toUserId = jsonObject.getStr("receiver");
				String group = jsonObject.getStr("group");
				// 业务代码处理
				SysImMessage sysImMessage = new SysImMessage();
				sysImMessage.setId(UUIDUtil.uuid());
				sysImMessage.setSenduser(userId);
				sysImMessage.setReceiveuser(toUserId);
				sysImMessage.setGroupid(group);
				sysImMessage.setIsread("0");
				sysImMessage.setType("0");
				sysImMessage.setContent(jsonObject.getStr("msg"));
				sysImMessage.setCreateuser(userId);
				sysImMessage.setCreatedate(new Date());
				String Imgroup = jsonObject.getStr("group");
				// 状态为1，聊天数据
				jsonObject.putOpt("type", "1");
				sysImMessageService = SpringUtil.getBean(SysImMessageService.class);
				sysImMessageService.IMMessageSava(sysImMessage);
				List<java.util.Map<String,Object>> ListuserId=sysImMessageService.friendGroupAndUserList(Imgroup,userId);
				ConcurrentHashMap<String, ScriptSession> dwrsession = Dwrmsg.getJavascriptSessionMap();
				if (StringUtils.isNotBlank(Imgroup)) {
					for (java.util.Map<String,Object> item :ListuserId){
						if(item.get("user_id")!=null){
							String targetId = String.valueOf(item.get("user_id"));
							if (!targetId.equals(toUserId)&&webSocketMap.containsKey(targetId)){
								sysImMessageService.TimflockReadAdd(sysImMessage.getId(),targetId,Imgroup);
								/////////////////////////////////////////////////
								if(webSocketMap.get(targetId)!=null){
									webSocketMap.get(targetId).sendMessage(jsonObject.toJSONString(1));
								}
							}
							// 群消息ie9以下的用户
							if (!targetId.equals(toUserId)&&dwrsession.containsKey(targetId)) {
								sysImMessageService.TimflockReadAdd(sysImMessage.getId(),targetId,Imgroup);
								/////////////////////////////////////////////////
								ScriptBuffer script = new ScriptBuffer();
								script.appendCall("showMessage", jsonObject.toJSONString(1));
								if(dwrsession.get(targetId)!=null){
									dwrsession.get(targetId).addScript(script);
								}
							}
						}
					}
				} else if (StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
					// websoket
					webSocketMap.get(toUserId).
							sendMessage(jsonObject.toJSONString(1));
				} else if (StringUtils.isNotBlank(toUserId) && dwrsession.containsKey(toUserId)) {
					// dwr推送针对ie9以下的
					new Runnable() {
						@Override
						public void run() {
							ScriptBuffer script = new ScriptBuffer();
							script.appendCall("showMessage", jsonObject.toJSONString(1));
							dwrsession.get(toUserId).addScript(script);
						}
					}.run();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("连接出错:用户id" + this.userId + "," + error.getMessage());
		error.printStackTrace();
	}

	/**
	 * 推送
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}

}
