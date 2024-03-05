package com.allxone.coinmarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

public class TaxInformationExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public TaxInformationExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<>();
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

		protected void addCriterionForJDBCDate(String condition, Date value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()), property);
		}

		protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdIsNull() {
			addCriterion("employee_id is null");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdIsNotNull() {
			addCriterion("employee_id is not null");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdEqualTo(Long value) {
			addCriterion("employee_id =", value, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdNotEqualTo(Long value) {
			addCriterion("employee_id <>", value, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdGreaterThan(Long value) {
			addCriterion("employee_id >", value, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdGreaterThanOrEqualTo(Long value) {
			addCriterion("employee_id >=", value, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdLessThan(Long value) {
			addCriterion("employee_id <", value, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdLessThanOrEqualTo(Long value) {
			addCriterion("employee_id <=", value, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdIn(List<Long> values) {
			addCriterion("employee_id in", values, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdNotIn(List<Long> values) {
			addCriterion("employee_id not in", values, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdBetween(Long value1, Long value2) {
			addCriterion("employee_id between", value1, value2, "employeeId");
			return (Criteria) this;
		}

		public Criteria andEmployeeIdNotBetween(Long value1, Long value2) {
			addCriterion("employee_id not between", value1, value2, "employeeId");
			return (Criteria) this;
		}

		public Criteria andTaxRateIsNull() {
			addCriterion("tax_rate is null");
			return (Criteria) this;
		}

		public Criteria andTaxRateIsNotNull() {
			addCriterion("tax_rate is not null");
			return (Criteria) this;
		}

		public Criteria andTaxRateEqualTo(BigDecimal value) {
			addCriterion("tax_rate =", value, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateNotEqualTo(BigDecimal value) {
			addCriterion("tax_rate <>", value, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateGreaterThan(BigDecimal value) {
			addCriterion("tax_rate >", value, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("tax_rate >=", value, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateLessThan(BigDecimal value) {
			addCriterion("tax_rate <", value, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateLessThanOrEqualTo(BigDecimal value) {
			addCriterion("tax_rate <=", value, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateIn(List<BigDecimal> values) {
			addCriterion("tax_rate in", values, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateNotIn(List<BigDecimal> values) {
			addCriterion("tax_rate not in", values, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("tax_rate between", value1, value2, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("tax_rate not between", value1, value2, "taxRate");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionIsNull() {
			addCriterion("tax_exemption is null");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionIsNotNull() {
			addCriterion("tax_exemption is not null");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionEqualTo(Boolean value) {
			addCriterion("tax_exemption =", value, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionNotEqualTo(Boolean value) {
			addCriterion("tax_exemption <>", value, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionGreaterThan(Boolean value) {
			addCriterion("tax_exemption >", value, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionGreaterThanOrEqualTo(Boolean value) {
			addCriterion("tax_exemption >=", value, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionLessThan(Boolean value) {
			addCriterion("tax_exemption <", value, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionLessThanOrEqualTo(Boolean value) {
			addCriterion("tax_exemption <=", value, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionIn(List<Boolean> values) {
			addCriterion("tax_exemption in", values, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionNotIn(List<Boolean> values) {
			addCriterion("tax_exemption not in", values, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionBetween(Boolean value1, Boolean value2) {
			addCriterion("tax_exemption between", value1, value2, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andTaxExemptionNotBetween(Boolean value1, Boolean value2) {
			addCriterion("tax_exemption not between", value1, value2, "taxExemption");
			return (Criteria) this;
		}

		public Criteria andCreatedAtIsNull() {
			addCriterion("created_at is null");
			return (Criteria) this;
		}

		public Criteria andCreatedAtIsNotNull() {
			addCriterion("created_at is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedAtEqualTo(Date value) {
			addCriterion("created_at =", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtNotEqualTo(Date value) {
			addCriterion("created_at <>", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtGreaterThan(Date value) {
			addCriterion("created_at >", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
			addCriterion("created_at >=", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtLessThan(Date value) {
			addCriterion("created_at <", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
			addCriterion("created_at <=", value, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtIn(List<Date> values) {
			addCriterion("created_at in", values, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtNotIn(List<Date> values) {
			addCriterion("created_at not in", values, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtBetween(Date value1, Date value2) {
			addCriterion("created_at between", value1, value2, "createdAt");
			return (Criteria) this;
		}

		public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
			addCriterion("created_at not between", value1, value2, "createdAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtIsNull() {
			addCriterion("updated_at is null");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtIsNotNull() {
			addCriterion("updated_at is not null");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtEqualTo(Date value) {
			addCriterion("updated_at =", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtNotEqualTo(Date value) {
			addCriterion("updated_at <>", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtGreaterThan(Date value) {
			addCriterion("updated_at >", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
			addCriterion("updated_at >=", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtLessThan(Date value) {
			addCriterion("updated_at <", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
			addCriterion("updated_at <=", value, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtIn(List<Date> values) {
			addCriterion("updated_at in", values, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtNotIn(List<Date> values) {
			addCriterion("updated_at not in", values, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtBetween(Date value1, Date value2) {
			addCriterion("updated_at between", value1, value2, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
			addCriterion("updated_at not between", value1, value2, "updatedAt");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Boolean value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Boolean value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Boolean value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Boolean value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Boolean value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Boolean> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Boolean> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Boolean value1, Boolean value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andDateStartIsNull() {
			addCriterion("date_start is null");
			return (Criteria) this;
		}

		public Criteria andDateStartIsNotNull() {
			addCriterion("date_start is not null");
			return (Criteria) this;
		}

		public Criteria andDateStartEqualTo(Date value) {
			addCriterionForJDBCDate("date_start =", value, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartNotEqualTo(Date value) {
			addCriterionForJDBCDate("date_start <>", value, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartGreaterThan(Date value) {
			addCriterionForJDBCDate("date_start >", value, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("date_start >=", value, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartLessThan(Date value) {
			addCriterionForJDBCDate("date_start <", value, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("date_start <=", value, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartIn(List<Date> values) {
			addCriterionForJDBCDate("date_start in", values, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartNotIn(List<Date> values) {
			addCriterionForJDBCDate("date_start not in", values, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("date_start between", value1, value2, "dateStart");
			return (Criteria) this;
		}

		public Criteria andDateStartNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("date_start not between", value1, value2, "dateStart");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tax_information
	 * @mbg.generated  Tue Mar 05 14:49:58 ICT 2024
	 */
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tax_information
     *
     * @mbg.generated do_not_delete_during_merge Thu Feb 22 09:13:49 ICT 2024
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}