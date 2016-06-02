package com.btcc.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.btcc.dao.ProfileDao;
import com.btcc.enums.OrderStatusCode;
import com.btcc.service.EntrustService;

public class EntrustTest extends AbstractTests{
	@Autowired
	private EntrustService entrustService;

	@Test
	public void test() throws Exception {
		//fail("Not yet implemented");
//		entrustService.buyOrder("1234", "0.001");
//		profileDao.save(entrustService.getAccountInfo().getResult().getProfile());
//		entrustService.canceOrder("470186561");
	//	entrustService.getOrders();
		double sum = 1;
		entrustService.buyOrder("3598", "0.01");
		while(sum > 0){		
			if(entrustService.getOrders().get(0).getStatus().equals(OrderStatusCode.queryStatus(2))){
				System.out.println(sum);
				entrustService.sellOrder("3590", "0.01");
				if(entrustService.getOrders().get(0).getStatus().equals(OrderStatusCode.queryStatus(2))){
					entrustService.buyOrder("3598", "0.01");
					System.out.println(sum);
					sum-=0.01;
				}
			}
			
			
		}
		
		
		
	}

}
