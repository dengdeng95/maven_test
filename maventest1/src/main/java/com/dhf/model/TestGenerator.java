package com.dhf.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2017-09-05 16:29:23
 */
@Table(name="test_generator")
public class TestGenerator implements Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	//id
	private Long id;
	@Column(name = "name")
	//用户名
	private String name;
	@Column(name = "transactionMoney")
	//交易金额
	private BigDecimal transactionMoney;
	@Column(name = "recordType")
	//流水类型 （ 1增加 2减少）
	private Integer recordType;
	@Column(name = "created")
	//创建时间
	private Date created;
	@Column(name = "isRequired")
	//是否必填
	private Integer isRequired;
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id=id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public BigDecimal getTransactionMoney(){
		return transactionMoney;
	}
	
	public void setTransactionMoney(BigDecimal transactionMoney){
		this.transactionMoney=transactionMoney;
	}
	public Integer getRecordType(){
		return recordType;
	}
	
	public void setRecordType(Integer recordType){
		this.recordType=recordType;
	}
	public Date getCreated(){
		return created;
	}
	
	public void setCreated(Date created){
		this.created=created;
	}
	public Integer getIsRequired(){
		return isRequired;
	}
	
	public void setIsRequired(Integer isRequired){
		this.isRequired=isRequired;
	}
}