package com.demo.db.mapper;

import com.demo.db.model.InstrumentDailyDataModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstrumentDailyDataModelMapper implements RowMapper<InstrumentDailyDataModel> {

  @Override
  public InstrumentDailyDataModel mapRow(ResultSet rs, int rowNum) throws SQLException {
    InstrumentDailyDataModel model = new InstrumentDailyDataModel();
    model.setQuoteId(rs.getLong("QuoteId"));
    model.setDate(rs.getDate("Date"));
    model.setTradeClassificationCode(rs.getString("TC"));
    model.setVenueCode(rs.getString("Venue"));
    model.setTape(rs.getString("tape"));
    model.setTotalTrades(rs.getInt("TradeCount"));
    model.setTotalVolume(rs.getLong("Volume"));
    model.setTotalTurnover(rs.getDouble("Turnover"));
    model.setTotalTurnoverAUD(rs.getDouble("TurnoverAUD"));
    model.setTotalTurnoverCAD(rs.getDouble("TurnoverCAD"));
    model.setTotalTurnoverEUR(rs.getDouble("TurnoverEUR"));
    model.setTotalTurnoverGBP(rs.getDouble("TurnoverGBP"));
    model.setTotalTurnoverHKD(rs.getDouble("TurnoverHKD"));
    model.setTotalTurnoverJPY(rs.getDouble("TurnoverJPY"));
    model.setTotalTurnoverSGD(rs.getDouble("TurnoverSGD"));
    model.setTotalTurnoverUSD(rs.getDouble("TurnoverUSD"));
    return model;
  }
}
