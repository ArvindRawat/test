package com.arvind.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arvind.dao.ConsumerLoginStageDAO;
import com.arvind.entity.ConsumerLoginStage;
import com.arvind.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	private ConsumerLoginStage user;
	@Autowired
	ConsumerLoginStageDAO consumerLoginStageDao;

	@Transactional
	public ConsumerLoginStage userLogin(String userName,String password) {
		System.out.println("In userLogin service");
		user=consumerLoginStageDao.userLogin(userName);
		System.out.println("user obj is "+user.getConsumerStageId());
		return user;
		
	}

}
