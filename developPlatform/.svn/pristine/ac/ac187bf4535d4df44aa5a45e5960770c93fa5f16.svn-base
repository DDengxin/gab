package com.tengzhi.IM.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author lqy 开启webSocket
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
	/* javascript连接示例(可跨域)
	function WebSocketTest(){
		if ("WebSocket" in window){
			alert("您的浏览器支持 WebSocket!");
			// 打开一个 web socket
			var ws = new WebSocket("ws://服务器地址:端口/imserver/用户ID");
			ws.onopen = function(){
				// Web Socket 已连接上，使用 send() 方法发送数据
				ws.send("发送数据");
				alert("数据发送中...");
			};
			ws.onmessage = function (evt){
				var received_msg = evt.data;
				alert("数据已接收...");
			};
			ws.onclose = function(){
				// 关闭 websocket
				alert("连接已关闭...");
			};
		}else{
			// 浏览器不支持 WebSocket
			alert("您的浏览器不支持 WebSocket!");
		}
	}
	WebSocketTest();
	*/

}
