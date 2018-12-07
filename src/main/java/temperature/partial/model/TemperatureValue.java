package temperature.partial.model;

public class TemperatureValue {

    private int value;
    private long timestamp;


    //TODO create empty constructor and hashcode


    public TemperatureValue(int value, long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TemperatureValue " +
                ", value: " + value +
                ", timestamp: " + timestamp;
    }

    public TemperatureValue() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
