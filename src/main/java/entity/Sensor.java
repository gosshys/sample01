package entity;

public class Sensor {
  private Long id;
  private Long sensorId;
  private String status;

  private String action;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSensorId() {
    return sensorId;
  }

  public void setSensorId(Long sensorId) {
    this.sensorId = sensorId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }
}
