package com.converter.DAO;

import com.converter.models.Valute;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValuteMapper implements RowMapper<Valute> {
    @Override
    public Valute mapRow(ResultSet resultSet, int i) throws SQLException {
        Valute valute=new Valute();
        valute.setID(resultSet.getString("id"));
        valute.setValue(resultSet.getDouble("value"));
        valute.setCharCode(resultSet.getString("char_code"));
        valute.setName(resultSet.getString("name"));
        valute.setNominal(resultSet.getInt("nominal"));
        valute.setNumCode(resultSet.getString("num_code"));
        valute.setData(resultSet.getString("data"));
        valute.setKey(resultSet.getLong("key"));
        return valute;

    }
}
