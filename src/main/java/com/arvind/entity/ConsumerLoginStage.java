package com.arvind.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="consumer_login_stage")
public class ConsumerLoginStage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7474008479356289551L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="consumer_stage_id")
	private int consumerStageId;
	
	@Column(name="consumer_email")
	private String consumerEmail;
	
	@Column(name="enc_password")
	private String encPassword;

	public int getConsumerStageId() {
		return consumerStageId;
	}

	public void setConsumerStageId(int consumerStageId) {
		this.consumerStageId = consumerStageId;
	}

	public String getConsumerEmail() {
		return consumerEmail;
	}

	public void setConsumerEmail(String consumerEmail) {
		this.consumerEmail = consumerEmail;
	}

	public String getEncPassword() {
		return encPassword;
	}

	public void setEncPassword(String encPassword) {
		this.encPassword = encPassword;
	}
	
	
	
}
