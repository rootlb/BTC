package com.btcc.service;

import java.util.ArrayList;

import com.btcc.model.AccountInfoResponse;
import com.btcc.model.Order;



public interface EntrustService {
	
	/**
	 *购买BTC挂单 
	 * @param price 购买价格
	 * @param amount 数量
	 * @return 订单号
	 * @throws Exception
	 */
	public  Order buyOrder(String price,String amount)throws Exception;
	
	/**
	 * 出售BTC挂单
	 * @param price 出售价格
	 * @param amount 数量
	 * @return 订单号
	 * @throws Exception
	 */
	public  Order sellOrder(String price,String amount)throws Exception;
	
	/**
	 * 返回账户信息
	 * @return
	 * @throws Exception
	 */
	public  AccountInfoResponse getAccountInfo() throws Exception;
	
	public boolean canceOrder(String id) throws Exception;
	
	public ArrayList<Order> getOrders() throws Exception;
}
