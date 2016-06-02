package com.btcc.service;

import java.util.ArrayList;

import com.btcc.model.AccountInfoResponse;
import com.btcc.model.BuyResponse;
import com.btcc.model.Order;
import com.btcc.model.SellResponse;



public interface EntrustService {
	
	/**
	 *购买BTC挂单 
	 * @param price 购买价格
	 * @param amount 数量
	 * @return 订单号
	 * @throws Exception
	 */
	public  BuyResponse buyOrder(String price,String amount)throws Exception;
	
	/**
	 * 出售BTC挂单
	 * @param price 出售价格
	 * @param amount 数量
	 * @return 订单号
	 * @throws Exception
	 */
	public  SellResponse sellOrder(String price,String amount)throws Exception;
	
	/**
	 * 返回账户信息
	 * @return
	 * @throws Exception
	 */
	public  AccountInfoResponse getAccountInfo() throws Exception;
	
	/**
	 * 撤销挂单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean canceOrder(String id) throws Exception;
	
	/**
	 * 查询挂单
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Order> getOrders() throws Exception;
}
