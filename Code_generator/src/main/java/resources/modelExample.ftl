package ${package!}.model;

import java.util.ArrayList;
import java.util.List;
<#if columnsType??>
<#list columnsType as ct>
<#if ct=="BigDecimal" >
import java.math.BigDecimal;
<#elseif ct=='Date'>
import java.util.Date;
</#if>
</#list>
</#if>

/**
 * @author:      ${author!}
 * @version:     V1.0 
 * @Date:        ${date!}
 */
public class ${model!}Example {
	
	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    public ${model!}Example() {
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
		
		<#if columns??>
		<#list columns as list>
		public Criteria and${(list.column)!}IsNull() {
            addCriterion("${(list.column)!} is null");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}IsNotNull() {
            addCriterion("${(list.column)!} is not null");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}EqualTo(${(list.javatype)!} value) {
            addCriterion("${(list.column)!} =", value, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}NotEqualTo(${(list.javatype)!} value) {
            addCriterion("${(list.column)!} <>", value, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}GreaterThan(${(list.javatype)!} value) {
            addCriterion("${(list.column)!} >", value, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}GreaterThanOrEqualTo(${(list.javatype)!} value) {
            addCriterion("${(list.column)!} >=", value, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}LessThan(${(list.javatype)!} value) {
            addCriterion("${(list.column)!} <", value, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}LessThanOrEqualTo(${(list.javatype)!} value) {
            addCriterion("${(list.column)!} <=", value, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}In(List<${(list.javatype)!}> values) {
            addCriterion("${(list.column)!} in", values, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}NotIn(List<${(list.javatype)!}> values) {
            addCriterion("${(list.column)!} not in", values, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}Between(${(list.javatype)!} value1, ${(list.javatype)!} value2) {
            addCriterion("${(list.column)!} between", value1, value2, "${(list.column)!}");
            return (Criteria) this;
        }

        public Criteria and${(list.column)!}NotBetween(${(list.javatype)!} value1, ${(list.javatype)!} value2) {
            addCriterion("${(list.column)!} not between", value1, value2, "${(list.column)!}");
            return (Criteria) this;
        }
		</#list>
		</#if>
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