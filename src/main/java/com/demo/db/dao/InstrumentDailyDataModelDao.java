package com.demo.db.dao;

import com.demo.db.mapper.InstrumentDailyDataModelMapper;
import com.demo.db.model.InstrumentDailyDataModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstrumentDailyDataModelDao {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public void batchSave(List<InstrumentDailyDataModel> instrumentDailyDataModels) {
    String saveQuery = "INSERT INTO msr.instrument_daily_data_model " +
        "(\"Date\", \"TC\", \"Venue\", \"Tape\", \"TradeCount\", \"Volume\", \"Turnover\", \"TurnoverAUD\", " +
        "\"TurnoverCAD\", \"TurnoverEUR\", \"TurnoverGBP\", \"TurnoverHKD\", \"TurnoverJPY\", \"TurnoverSGD\", " +
        "\"TurnoverUSD\", \"QuoteId\") VALUES " +
        "(:date, :tradeClassificationCode, :venueCode, :tape, :totalTrades, :totalVolume, :totalTurnover, " +
        ":totalTurnoverAUD, :totalTurnoverCAD, :totalTurnoverEUR, :totalTurnoverGBP, :totalTurnoverHKD, " +
        ":totalTurnoverJPY, :totalTurnoverSGD, :totalTurnoverUSD, :quoteId)";

    SqlParameterSource[] sqlParameterSources = new SqlParameterSource[instrumentDailyDataModels.size()];
    Iterator<InstrumentDailyDataModel> modelsIterator = instrumentDailyDataModels.iterator();
    for (int i = 0; modelsIterator.hasNext(); i++) {
      InstrumentDailyDataModel instrumentDailyDataModel = modelsIterator.next();

      SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
          .addValue("quoteId", instrumentDailyDataModel.getQuoteId())
          .addValue("date", instrumentDailyDataModel.getDate())
          .addValue("tradeClassificationCode", instrumentDailyDataModel.getTradeClassificationCode())
          .addValue("venueCode", instrumentDailyDataModel.getVenueCode())
          .addValue("tape", instrumentDailyDataModel.getTape())
          .addValue("totalTrades", instrumentDailyDataModel.getTotalTrades())
          .addValue("totalVolume", instrumentDailyDataModel.getTotalVolume())
          .addValue("totalTurnover", instrumentDailyDataModel.getTotalTurnover())
          .addValue("totalTurnoverAUD", instrumentDailyDataModel.getTotalTurnoverAUD())
          .addValue("totalTurnoverCAD", instrumentDailyDataModel.getTotalTurnoverCAD())
          .addValue("totalTurnoverEUR", instrumentDailyDataModel.getTotalTurnoverEUR())
          .addValue("totalTurnoverGBP", instrumentDailyDataModel.getTotalTurnoverGBP())
          .addValue("totalTurnoverHKD", instrumentDailyDataModel.getTotalTurnoverHKD())
          .addValue("totalTurnoverJPY", instrumentDailyDataModel.getTotalTurnoverJPY())
          .addValue("totalTurnoverSGD", instrumentDailyDataModel.getTotalTurnoverSGD())
          .addValue("totalTurnoverUSD", instrumentDailyDataModel.getTotalTurnoverUSD());

      sqlParameterSources[i] = sqlParameterSource;
    }

    jdbcTemplate.batchUpdate(saveQuery, sqlParameterSources);
  }

  public List<InstrumentDailyDataModel> selectAll() {
    String selectAllQuery = "SELECT \"Date\", \"TC\", \"Venue\", \"Tape\", \"TradeCount\", \"Volume\", \"Turnover\",\n" +
        "\"TurnoverAUD\", \"TurnoverCAD\", \"TurnoverEUR\", \"TurnoverGBP\", \"TurnoverHKD\",\n" +
        "\"TurnoverJPY\", \"TurnoverSGD\", \"TurnoverUSD\", \"QuoteId\" " +
        "FROM msr.instrument_daily_data_model";
    return jdbcTemplate.query(selectAllQuery, new InstrumentDailyDataModelMapper());
  }

  public List<InstrumentDailyDataModel> selectByQuoteId(long quoteId) {
    String selectByQuoteIdQuery = "SELECT \"Date\", \"TC\", \"Venue\", \"Tape\", \"TradeCount\", \"Volume\", \"Turnover\",\n" +
        "\"TurnoverAUD\", \"TurnoverCAD\", \"TurnoverEUR\", \"TurnoverGBP\", \"TurnoverHKD\",\n" +
        "\"TurnoverJPY\", \"TurnoverSGD\", \"TurnoverUSD\", \"QuoteId\" " +
        "FROM msr.instrument_daily_data_model " +
        "WHERE \"QuoteId\"=" + quoteId;
    return jdbcTemplate.query(selectByQuoteIdQuery, new InstrumentDailyDataModelMapper());
  }

  public boolean deleteByQuoteId(long quoteId) {
    String deleteByQuoteIdQuery = "DELETE FROM msr.instrument_daily_data_model " +
        "WHERE \"QuoteId\"=:quoteId";
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
        .addValue("quoteId", quoteId);
    return jdbcTemplate.update(deleteByQuoteIdQuery, sqlParameterSource) == 1;
  }
}
