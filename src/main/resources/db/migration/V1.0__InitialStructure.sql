DROP SCHEMA IF EXISTS msr CASCADE;

CREATE SCHEMA msr;

CREATE TABLE "msr"."instrument_daily_data_model" (
    "RIC" CHAR(32) NOT NULL,
    "Date" DATE NOT NULL,
    "TC" CHAR(2) NOT NULL,
    "Venue" CHAR(4) NOT NULL,
    "Country" CHAR(2) NOT NULL,
    "AssetClass" CHAR(20) NOT NULL,
    "Currency" CHAR(3) NOT NULL,
    "Tape" CHAR(1) NOT NULL,
    "TradeCount" INT NOT NULL,
    "Volume" DOUBLE PRECISION NOT NULL,
    "Turnover" DOUBLE PRECISION NOT NULL,
    "TurnoverAUD" DOUBLE PRECISION NOT NULL,
    "TurnoverCAD" DOUBLE PRECISION NOT NULL,
    "TurnoverEUR" DOUBLE PRECISION NOT NULL,
    "TurnoverGBP" DOUBLE PRECISION NOT NULL,
    "TurnoverHKD" DOUBLE PRECISION NOT NULL,
    "TurnoverJPY" DOUBLE PRECISION NOT NULL,
    "TurnoverSGD" DOUBLE PRECISION NOT NULL,
    "TurnoverUSD" DOUBLE PRECISION NOT NULL,
    CONSTRAINT "instrument_daily_data_model_pkey" PRIMARY KEY ("RIC", "Tape", "Date", "TC", "Venue")
);

CREATE INDEX idx_instrument_daily_data_model_Country ON "msr"."instrument_daily_data_model"("Country");
CREATE INDEX idx_instrument_daily_data_model_AssetClass ON "msr"."instrument_daily_data_model"("AssetClass");
CREATE INDEX idx_instrument_daily_data_model_Currency ON "msr"."instrument_daily_data_model"("Currency");


