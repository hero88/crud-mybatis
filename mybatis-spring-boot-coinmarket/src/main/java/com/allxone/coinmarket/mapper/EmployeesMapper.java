package com.allxone.coinmarket.mapper;

import com.allxone.coinmarket.dto.response.EmployeeDTO;
import com.allxone.coinmarket.model.Employees;
import com.allxone.coinmarket.model.EmployeesExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface EmployeesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    long countByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    int deleteByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    int insertSelective(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    List<Employees> selectByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    Employees selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    int updateByExampleSelective(@Param("row") Employees row, @Param("example") EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    int updateByExample(@Param("row") Employees row, @Param("example") EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    int updateByPrimaryKeySelective(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Thu Feb 22 09:13:49 ICT 2024
     */
    int updateByPrimaryKey(Employees row);
    List<Employees> getEmployees(@Param("offset")int page,@Param("limit")int size);
}