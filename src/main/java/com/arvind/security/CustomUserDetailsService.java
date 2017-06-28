package com.arvind.security;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arvind.dao.ConsumerLoginStageDAO;
import com.arvind.entity.ConsumerLoginStage;
import com.arvind.security.model.CustomUserDetail;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	ConsumerLoginStageDAO consumerLoginStageDao;

	@Transactional
	@Override
	public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("In loadUserByUsername method..");
		ConsumerLoginStage consumerLoginStage=consumerLoginStageDao.userLogin(username);
		if(consumerLoginStage!=null){
			 List<String> userRoles = new ArrayList<String>();
			 return new CustomUserDetail(consumerLoginStage.getConsumerStageId(), consumerLoginStage.getEncPassword(), consumerLoginStage.getConsumerEmail(),userRoles);
	    }
		return null;
	}

}
