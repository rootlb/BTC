package com.btcc.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.btcc.model.Ticker;

public interface TickerDao extends PagingAndSortingRepository<Ticker,String>{

}
