package com.btcc.service;

import com.btcc.model.Order;



public interface EntrustService {
	public  Order buyOrder(String price,String amount)throws Exception;
	public  Order sellOrder(String price,String amount)throws Exception;
}
