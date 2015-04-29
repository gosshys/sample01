package transaction;

import dao.TestData;

public class DataInitializeTransaction extends TransactionBase {

  @Override
  public void doExecute() throws Exception {
    TestData testData = new TestData(getConnection());
    testData.initialize();
  }
}
