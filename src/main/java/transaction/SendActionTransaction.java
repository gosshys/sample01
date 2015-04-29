package transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import dao.SensorDao;
import entity.Sensor;

public class SendActionTransaction extends TransactionBase {

  private final Logger logger = LoggerFactory.getLogger(SendActionTransaction.class);

  @Override
  public void doExecute() throws Exception {
    SensorDao sensorDao = new SensorDao(getConnection());
    List<Sensor> sensors = sensorDao.getActionRecord();
    logger.info("sensor count:{}", sensors.size());
    for (Sensor sensor : sensors) {
      // TODO actionごとの処理
      logger.info("sensor id:{}", sensor.getId());
      sensorDao.updateSendStatus(sensor.getId());
    }
  }
}
