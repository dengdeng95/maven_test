package com.dhf.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2017-09-05 16:29:23
 */
public class TestGeneratorExample {
	
	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    public TestGeneratorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }
    
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }
    
	protected abstract static class GeneratedCriteria {
		 protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }
		
		public Criteria andidIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andidIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andidEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andidNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andidGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andidGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andidLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andidLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andidIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andidNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andidBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andidNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }
		public Criteria andnameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andnameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andnameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andnameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andnameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andnameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andnameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andnameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andnameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andnameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andnameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andnameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }
		public Criteria andtransactionMoneyIsNull() {
            addCriterion("transactionMoney is null");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyIsNotNull() {
            addCriterion("transactionMoney is not null");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyEqualTo(BigDecimal value) {
            addCriterion("transactionMoney =", value, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyNotEqualTo(BigDecimal value) {
            addCriterion("transactionMoney <>", value, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyGreaterThan(BigDecimal value) {
            addCriterion("transactionMoney >", value, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transactionMoney >=", value, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyLessThan(BigDecimal value) {
            addCriterion("transactionMoney <", value, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transactionMoney <=", value, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyIn(List<BigDecimal> values) {
            addCriterion("transactionMoney in", values, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyNotIn(List<BigDecimal> values) {
            addCriterion("transactionMoney not in", values, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transactionMoney between", value1, value2, "transactionMoney");
            return (Criteria) this;
        }

        public Criteria andtransactionMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transactionMoney not between", value1, value2, "transactionMoney");
            return (Criteria) this;
        }
		public Criteria andrecordTypeIsNull() {
            addCriterion("recordType is null");
            return (Criteria) this;
        }

        public Criteria andrecordTypeIsNotNull() {
            addCriterion("recordType is not null");
            return (Criteria) this;
        }

        public Criteria andrecordTypeEqualTo(Integer value) {
            addCriterion("recordType =", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeNotEqualTo(Integer value) {
            addCriterion("recordType <>", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeGreaterThan(Integer value) {
            addCriterion("recordType >", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("recordType >=", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeLessThan(Integer value) {
            addCriterion("recordType <", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeLessThanOrEqualTo(Integer value) {
            addCriterion("recordType <=", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeIn(List<Integer> values) {
            addCriterion("recordType in", values, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeNotIn(List<Integer> values) {
            addCriterion("recordType not in", values, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeBetween(Integer value1, Integer value2) {
            addCriterion("recordType between", value1, value2, "recordType");
            return (Criteria) this;
        }

        public Criteria andrecordTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("recordType not between", value1, value2, "recordType");
            return (Criteria) this;
        }
		public Criteria andcreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andcreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andcreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andcreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }
		public Criteria andisRequiredIsNull() {
            addCriterion("isRequired is null");
            return (Criteria) this;
        }

        public Criteria andisRequiredIsNotNull() {
            addCriterion("isRequired is not null");
            return (Criteria) this;
        }

        public Criteria andisRequiredEqualTo(Integer value) {
            addCriterion("isRequired =", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredNotEqualTo(Integer value) {
            addCriterion("isRequired <>", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredGreaterThan(Integer value) {
            addCriterion("isRequired >", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("isRequired >=", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredLessThan(Integer value) {
            addCriterion("isRequired <", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredLessThanOrEqualTo(Integer value) {
            addCriterion("isRequired <=", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredIn(List<Integer> values) {
            addCriterion("isRequired in", values, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredNotIn(List<Integer> values) {
            addCriterion("isRequired not in", values, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredBetween(Integer value1, Integer value2) {
            addCriterion("isRequired between", value1, value2, "isRequired");
            return (Criteria) this;
        }

        public Criteria andisRequiredNotBetween(Integer value1, Integer value2) {
            addCriterion("isRequired not between", value1, value2, "isRequired");
            return (Criteria) this;
        }
	}
	public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            	super();
	        }
	    }
	
	    public static class Criterion {
	        private String condition;
	
	        private Object value;
	
	        private Object secondValue;
	
	        private boolean noValue;
	
	        private boolean singleValue;
	
	        private boolean betweenValue;
	
	        private boolean listValue;
	
	        private String typeHandler;
	
	        public String getCondition() {
	            return condition;
	        }
	
	        public Object getValue() {
	            return value;
	        }
	
	        public Object getSecondValue() {
	            return secondValue;
	        }
	
	        public boolean isNoValue() {
	            return noValue;
	        }
	
	        public boolean isSingleValue() {
	            return singleValue;
	        }
	
	        public boolean isBetweenValue() {
	            return betweenValue;
	        }
	
	        public boolean isListValue() {
	            return listValue;
	        }
	
	        public String getTypeHandler() {
	            return typeHandler;
	        }
	
	        protected Criterion(String condition) {
	            super();
	            this.condition = condition;
	            this.typeHandler = null;
	            this.noValue = true;
	        }
	
	        protected Criterion(String condition, Object value, String typeHandler) {
	            super();
	            this.condition = condition;
	            this.value = value;
	            this.typeHandler = typeHandler;
	            if (value instanceof List<?>) {
	                this.listValue = true;
	            } else {
	                this.singleValue = true;
	            }
	        }
	
	        protected Criterion(String condition, Object value) {
	            this(condition, value, null);
	        }
	
	        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
	            super();
	            this.condition = condition;
	            this.value = value;
	            this.secondValue = secondValue;
	            this.typeHandler = typeHandler;
	            this.betweenValue = true;
	        }
	
	        protected Criterion(String condition, Object value, Object secondValue) {
	            this(condition, value, secondValue, null);
	        }
	    }
}