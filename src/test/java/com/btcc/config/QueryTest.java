package com.btcc.config;

import java.net.URISyntaxException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.btcc.service.QueryService;
import com.btcc.utils.PurchaseOrderSample;

public class QueryTest extends AbstractTests{
	
	@Autowired
	QueryService queryService;
	@Test
	public void test() throws Exception {
		PurchaseOrderSample purchaseOrderSample = new PurchaseOrderSample();
		//purchaseOrderSample.getPurchaseOrders();
	}

}
