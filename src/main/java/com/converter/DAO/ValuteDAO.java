package com.converter.DAO;

import com.converter.DAO.ValuteMapper;
import com.converter.models.History;
import com.converter.models.Valute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class ValuteDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ValuteDAO(JdbcTemplate jdbcTemplate){ this.jdbcTemplate=jdbcTemplate;}



    public Valute takeByKey(Long key){return jdbcTemplate.query("SELECT * FROM currency_converter WHERE key=?",new Object[]{key},new ValuteMapper())
            .stream().findAny().orElse(null);}
    public List<Valute> index(String data){return jdbcTemplate.query("SELECT * FROM currency_converter  WHERE data=?",new Object[]{data},new ValuteMapper()) ;}

    public List<Valute> show(String data,String char_code){
        return jdbcTemplate.query("SELECT * FROM currency_converter WHERE data=? and  char_code=?",new Object[]{data,char_code},new ValuteMapper());
    }

    public void save(Valute valute){
        jdbcTemplate.update("INSERT INTO currency_converter VALUES (?,?,?,?,?,?,?)"
                , valute.getID(),valute.getNumCode(),valute.getCharCode(),
                valute.getNominal(),valute.getName(),valute.getValue(),valute.getData());
    }
    public void delete2(){
        jdbcTemplate.update("DELETE FROM history");
    }

    public void delete(){
        jdbcTemplate.update("DELETE  FROM currency_converter ");

    }
    public List<History> showHistory(String data,String valuateForChange,String targetCharCode){
        return jdbcTemplate.query("SELECT * FROM history WHERE date=? and valuate_for_change=? and target=?",new Object[]{data,valuateForChange,targetCharCode},new HistoryMapper());
    }
    public void save(History history){
        jdbcTemplate.update("INSERT INTO history VALUES (?,?,?,?,?,?)",
                history.getValuateForChange(),history.getTarget(),history.getAmount()
                ,history.getTargetAmount(),history.getDate(),history.getKey());
    }




}
