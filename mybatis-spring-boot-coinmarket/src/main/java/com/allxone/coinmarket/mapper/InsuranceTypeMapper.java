package com.allxone.coinmarket.mapper;

import com.allxone.coinmarket.model.InsuranceType;
import com.allxone.coinmarket.model.InsuranceTypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InsuranceTypeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	long countByExample(InsuranceTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int deleteByExample(InsuranceTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int insert(InsuranceType row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int insertSelective(InsuranceType row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	List<InsuranceType> selectByExampleWithBLOBs(InsuranceTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	List<InsuranceType> selectByExample(InsuranceTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	InsuranceType selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int updateByExampleSelective(@Param("row") InsuranceType row, @Param("example") InsuranceTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int updateByExampleWithBLOBs(@Param("row") InsuranceType row, @Param("example") InsuranceTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int updateByExample(@Param("row") InsuranceType row, @Param("example") InsuranceTypeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int updateByPrimaryKeySelective(InsuranceType row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int updateByPrimaryKeyWithBLOBs(InsuranceType row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table insurance_type
	 * @mbg.generated  Fri Mar 01 15:24:59 ICT 2024
	 */
	int updateByPrimaryKey(InsuranceType row);
}