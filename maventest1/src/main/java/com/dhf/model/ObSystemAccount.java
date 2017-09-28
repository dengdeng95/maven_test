package com.dhf.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObSystemAccount implements Serializable{
    private Long accountid;

    private BigDecimal totalmoney;

    private String investpersonname;
    
    private int investpersonId;

    private Integer version;

    private String remark1;

    private String remark2;

    private String remark3;
    
    private BigDecimal money;
    
    


	public int getInvestpersonId() {
		return investpersonId;
	}

	public void setInvestpersonId(int investpersonId) {
		this.investpersonId = investpersonId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getInvestpersonname() {
        return investpersonname;
    }

    public void setInvestpersonname(String investpersonname) {
        this.investpersonname = investpersonname == null ? null : investpersonname.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }
}