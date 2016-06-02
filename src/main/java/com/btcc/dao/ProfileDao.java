package com.btcc.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.btcc.model.Profile;
public interface ProfileDao extends PagingAndSortingRepository<Profile,String>{

}
