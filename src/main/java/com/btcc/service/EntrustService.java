package com.btcc.service;

import java.util.ArrayList;

import com.btcc.model.AccountInfoResponse;
import com.btcc.model.Order;



public interface EntrustService {
	
	/**
	 *����BTC�ҵ� 
	 * @param price ����۸�
	 * @param amount ����
	 * @return ������
	 * @throws Exception
	 */
	public  Order buyOrder(String price,String amount)throws Exception;
	
	/**
	 * ����BTC�ҵ�
	 * @param price ���ۼ۸�
	 * @param amount ����
	 * @return ������
	 * @throws Exception
	 */
	public  Order sellOrder(String price,String amount)throws Exception;
	
	/**
	 * �����˻���Ϣ
	 * @return
	 * @throws Exception
	 */
	public  AccountInfoResponse getAccountInfo() throws Exception;
	
	public boolean canceOrder(String id) throws Exception;
	
	public ArrayList<Order> getOrders() throws Exception;
}
