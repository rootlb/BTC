package com.btcc.service;

import java.util.ArrayList;

import com.btcc.model.AccountInfoResponse;
import com.btcc.model.BuyResponse;
import com.btcc.model.Order;
import com.btcc.model.SellResponse;



public interface EntrustService {
	
	/**
	 *����BTC�ҵ� 
	 * @param price ����۸�
	 * @param amount ����
	 * @return ������
	 * @throws Exception
	 */
	public  BuyResponse buyOrder(String price,String amount)throws Exception;
	
	/**
	 * ����BTC�ҵ�
	 * @param price ���ۼ۸�
	 * @param amount ����
	 * @return ������
	 * @throws Exception
	 */
	public  SellResponse sellOrder(String price,String amount)throws Exception;
	
	/**
	 * �����˻���Ϣ
	 * @return
	 * @throws Exception
	 */
	public  AccountInfoResponse getAccountInfo() throws Exception;
	
	/**
	 * �����ҵ�
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean canceOrder(String id) throws Exception;
	
	/**
	 * ��ѯ�ҵ�
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Order> getOrders() throws Exception;
}
