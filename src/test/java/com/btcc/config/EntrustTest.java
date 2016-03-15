package com.btcc.config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.btcc.service.EntrustService;

public class EntrustTest extends AbstractTests{
	@Autowired
	private EntrustService entrustService;

	@Test
	public void test() throws Exception {
		//fail("Not yet implemented");
		entrustService.buyOrder("1234", "0.001");
	}

}
