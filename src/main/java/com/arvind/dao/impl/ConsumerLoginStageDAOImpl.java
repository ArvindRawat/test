package com.arvind.dao.impl;

import java.beans.Transient;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arvind.dao.ConsumerLoginStageDAO;
import com.arvind.dao.base.BaseDao;
import com.arvind.entity.ConsumerLoginStage;




@Repository
public class ConsumerLoginStageDAOImpl extends BaseDao<ConsumerLoginStage> implements ConsumerLoginStageDAO {
	
	@Override
	public ConsumerLoginStage userLogin(String userName) {
		Criteria criteria=getSession().createCriteria(ConsumerLoginStage.class);
		criteria.add(Restrictions.eq("consumerEmail", userName.trim()));
		return (ConsumerLoginStage) criteria.uniqueResult();
}
	/*@Override
	public ConsumerLoginStage userLogin(String userName) {
		System.out.println("In userLogin metod of ConsumerLoginStageDAOImpl "+userName);
		Session session=getSession();
		Criteria createCriteria = session.createCriteria(ConsumerLoginStage.class);
		return (ConsumerLoginStage) createCriteria.uniqueResult();
		return null;
	}*/

	
	

}
