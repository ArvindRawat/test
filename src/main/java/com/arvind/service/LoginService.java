package com.arvind.service;

import com.arvind.entity.ConsumerLoginStage;

public interface LoginService {

	public ConsumerLoginStage userLogin(String userName,String password);

}
