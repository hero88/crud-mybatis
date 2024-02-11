package com.allxone.mybatisprojectbackend.enumaration.handler;

import com.allxone.mybatisprojectbackend.enumaration.Role;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Role.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class RoleTypeHandler extends BaseTypeHandler<Role> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public Role getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value != null ? Role.valueOf(value) : null;
    }

    @Override
    public Role getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value != null ? Role.valueOf(value) : null;
    }

    @Override
    public Role getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value != null ? Role.valueOf(value) : null;
    }
}
