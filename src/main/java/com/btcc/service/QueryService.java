package com.btcc.service;

import java.net.URISyntaxException;

public interface QueryService {
	/**
	 * 查看即时行情
	 * @throws Exception
	 */
	public void queryTicker()throws URISyntaxException;
	public void queryTrade() throws URISyntaxException;
	public void queryBalance();
	public void queryGrouporder();
}
