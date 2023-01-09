package com.demo.db.model;

import lombok.Data;

import java.sql.Date;

@Data
public class InstrumentDailyDataModel {
  private long quoteId;
  private Date date;
  private String tradeClassificationCode;
  private String venueCode;
  private String tape;

  private int totalTrades;
  private long totalVolume;
  private double totalTurnover;
  private double totalTurnoverAUD;
  private double totalTurnoverCAD;
  private double totalTurnoverEUR;
  private double totalTurnoverGBP;
  private double totalTurnoverHKD;
  private double totalTurnoverJPY;
  private double totalTurnoverSGD;
  private double totalTurnoverUSD;

}
