package com.btcc.utils;

import java.net.URISyntaxException;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.btcc.model.BTC;
import com.btcc.model.Ticker;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

public class ClienUtil {
	/**
	 * 与BTCC服务器建立连接
	 * @return 返回创建连接
	 * @throws URISyntaxException
	 */
	public  Socket getSocket() throws URISyntaxException{
		IO.Options opt = new IO.Options();
		opt.reconnection = true;
		String  url = "https://websocket.btcchina.com";
		final Socket socket = IO.socket(url,opt);
		socket.on(Socket.EVENT_CONNECT,new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				System.out.println("connected success!");
			socket.emit("subscribe", "marketdata_cnybtc");
			}
		});
		return socket;
	}
}
