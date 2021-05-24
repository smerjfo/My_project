package com.converter.DAO;

import com.converter.models.History;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryMapper implements RowMapper<History> {
    @Override
    public History mapRow(ResultSet resultSet, int i) throws SQLException {
        History history=new History();
        history.setAmount(resultSet.getDouble("amount"));
        history.setValuateForChange(resultSet.getString("valuate_for_change"));
        history.setTarget(resultSet.getString("target"));
        history.setTargetAmount(resultSet.getDouble("target_amount"));
        history.setDate(resultSet.getString("date"));
        return history;
    }
}
