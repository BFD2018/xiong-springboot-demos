package com.xjt.cache.mapper;

import com.xjt.cache.domain.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmployeeMapper {
    @Select("select * from employee where id=#{id}")
    Employee getEmpById(Integer id);

    @Insert("INSERT INTO employee(last_name,email,gender,d_id) VALUES (#{lastName},#{email},#{gender},#{dId})")
    public void insertEmp(Employee employee);

    @Update("UPDATE employee SET last_name = #{lastName},email = #{email},gender = #{gender},d_id = #{dId} WHERE id = #{id}")
    public void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    public void deleteEmpById(Integer id);
}
