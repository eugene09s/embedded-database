package com.demo.db.dao;

import com.demo.db.config.EmbeddedPostgresConfiguration;
import com.demo.db.config.JdbcConfiguration;
import com.demo.db.model.InstrumentDailyDataModel;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JdbcConfiguration.class)
@ContextConfiguration(classes = EmbeddedPostgresConfiguration.class)
@AutoConfigureEmbeddedDatabase(beanName = "mysqlDataSource", provider = ZONKY, type = POSTGRES)
class DbTest {

  private static final int ORIGINALLY_SIZE_ALL_ELEMENTS = 11;
  @Autowired
  private InstrumentDailyDataModelDao instrumentDailyDataModelDao;
  @Autowired
  private Flyway flyway;

  @BeforeEach
  public void setUp() {
    flyway.migrate();
  }

  @AfterEach
  public void tearDown() {
    flyway.clean();
  }

  @SneakyThrows
  @Test
  void selectAll_returnAllObjects() {
    // When
    System.out.printf("RUN");
    List<InstrumentDailyDataModel> instrumentDailyDataModelList = instrumentDailyDataModelDao.selectAll();

    // Then
    assertTrue(instrumentDailyDataModelList.size() > 0);
  }

  @SneakyThrows
  @Test
  void selectByQuoteId_shouldFoundAndGet() {
    // When
    List<InstrumentDailyDataModel> instrumentDailyDataModelList = instrumentDailyDataModelDao.selectByQuoteId(21474838070L);

    // Then
    assertAll(
        () -> assertNotNull(instrumentDailyDataModelList),
        () -> assertEquals(1, instrumentDailyDataModelList.size()),
        () -> assertEquals("B", instrumentDailyDataModelList.get(0).getTape()),
        () -> assertEquals("QWE", instrumentDailyDataModelList.get(0).getVenueCode().trim()),
        () -> assertEquals("HH", instrumentDailyDataModelList.get(0).getTradeClassificationCode()),
        () -> assertEquals(768, instrumentDailyDataModelList.get(0).getTotalTrades())
    );
  }

  @SneakyThrows
  @Test
  void deleteByQuoteId_shouldDeleteTwoElements_whenIfRowExistByQuoteId() {
    // When
    List<InstrumentDailyDataModel> originallyInstrumentDailyDataModelList = instrumentDailyDataModelDao.selectAll();
    boolean isDeletedFirst = instrumentDailyDataModelDao.deleteByQuoteId(21474838070L);
    boolean isDeletedNonExistent = instrumentDailyDataModelDao.deleteByQuoteId(1222222L);
    boolean isDeletedSecond = instrumentDailyDataModelDao.deleteByQuoteId(21474837776L);
    List<InstrumentDailyDataModel> afterDeletionInstrumentDailyDataModelList = instrumentDailyDataModelDao.selectAll();

    // Then
    assertAll(
        () -> assertNotNull(originallyInstrumentDailyDataModelList),
        () -> assertEquals(ORIGINALLY_SIZE_ALL_ELEMENTS, originallyInstrumentDailyDataModelList.size()),
        () -> assertTrue(isDeletedFirst),
        () -> assertTrue(isDeletedSecond),
        () -> assertFalse(isDeletedNonExistent),
        () -> assertEquals(ORIGINALLY_SIZE_ALL_ELEMENTS - 2, afterDeletionInstrumentDailyDataModelList.size())
    );
  }

  @SneakyThrows
  @Test
  void selectAll_shouldGetAllElements_whenSelectingAllElements() {
    // When
    List<InstrumentDailyDataModel> instrumentDailyDataModelList = instrumentDailyDataModelDao.selectAll();

    // Then
    assertNotNull(instrumentDailyDataModelList);
    assertEquals(ORIGINALLY_SIZE_ALL_ELEMENTS, instrumentDailyDataModelList.size());
  }

  @SneakyThrows
  @Test
  void batchSave_shouldSaveBatchElements() {
    // When
    List<InstrumentDailyDataModel> originallyInstrumentDailyDataModelList = instrumentDailyDataModelDao.selectAll();
    InstrumentDailyDataModel firstElement = originallyInstrumentDailyDataModelList.get(0);
    firstElement.setQuoteId(111L);
    InstrumentDailyDataModel secondElement = originallyInstrumentDailyDataModelList.get(1);
    secondElement.setQuoteId(222L);
    List<InstrumentDailyDataModel> newElements = List.of(firstElement, secondElement);

    instrumentDailyDataModelDao.batchSave(newElements);
    List<InstrumentDailyDataModel> currentInstrumentDailyDataModelList = instrumentDailyDataModelDao.selectAll();

    // Then
    assertAll(
        () -> assertNotNull(originallyInstrumentDailyDataModelList),
        () -> assertEquals(ORIGINALLY_SIZE_ALL_ELEMENTS, originallyInstrumentDailyDataModelList.size()),
        () -> assertNotNull(currentInstrumentDailyDataModelList),
        () -> assertEquals(ORIGINALLY_SIZE_ALL_ELEMENTS + 2, currentInstrumentDailyDataModelList.size())
    );
  }
}
