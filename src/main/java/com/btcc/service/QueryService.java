package com.btcc.service;

import java.net.URISyntaxException;

public interface QueryService {
	/**
	 * �鿴��ʱ����
	 * @throws Exception
	 */
	public void queryTicker()throws URISyntaxException;
	public void queryTrade() throws URISyntaxException;
	public void queryBalance();
	public void queryGrouporder();
}
