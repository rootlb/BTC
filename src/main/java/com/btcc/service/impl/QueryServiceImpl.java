package com.btcc.service.impl;

import java.net.URISyntaxException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btcc.dao.TickerDao;
import com.btcc.model.BTC;
import com.btcc.model.Ticker;
import com.btcc.model.Trade;
import com.btcc.service.QueryService;
import com.btcc.utils.ClienUtil;
import com.btcc.utils.StringToDoubleUtil;
import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

@Service
public class QueryServiceImpl implements QueryService{
	@Autowired
	private TickerDao tickerDao;
	
	private BTC btc = new BTC();
	private Trade trade = new Trade();
	private ClienUtil clienUtil = new ClienUtil();
	@Override
	public void queryTicker() throws URISyntaxException {
		clienUtil.getSocket().connect().on("ticker",new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Gson gson = new Gson();	
				   JSONObject json = (JSONObject) args[0];
				   btc = gson.fromJson(json.toString(), BTC.class);
				   long start = System.currentTimeMillis();
				  long temp = System.currentTimeMillis();
				   System.out.println("buy: "+btc.getTicker().getBuy()+"£¤"+"    ºÄÊ±:"+(temp-start));
				   tickerDao.save(btc.getTicker());
				   
			} 
		});
	}

	@Override
	public void queryTrade() throws URISyntaxException {
		clienUtil.getSocket().on("trade", new Emitter.Listener(){
			@Override
			public void call(Object...args){
				Gson gson = new Gson();	
				   JSONObject json = (JSONObject) args[0];
				   trade = gson.fromJson(json.toString(), Trade.class);
				   long start = System.currentTimeMillis();
				  long temp = System.currentTimeMillis();
				   //System.out.println("Respones Trade:"+trade.toString()+"    ºÄÊ±:"+(temp-start));	
			} 
		}).on("order", new Emitter.Listener() {
		    @Override
		    public void call(Object... args) {
		    JSONObject json = (JSONObject) args[0];//receive your order feed
		    System.out.println(json);
		    }
		    }).on("account_info", new Emitter.Listener() {
		        @Override
		        public void call(Object... args) {
		        JSONObject json = (JSONObject) args[0];//receive your account_info feed
		        System.out.println(json);
		        }
		        });
		clienUtil.getSocket().connect();
	}

	@Override
	public void queryBalance() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void queryGrouporder() {
		// TODO Auto-generated method stub
		
	}
	
//	public static void main(String arge[]) throws URISyntaxException{
//		new QueryServiceImpl().queryTicker();
//	}
}
