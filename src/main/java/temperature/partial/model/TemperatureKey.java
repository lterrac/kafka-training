package temperature.partial.model;

public class TemperatureKey {
    private String location;

    public TemperatureKey(String location) {
        this.location = location;
    }

    //TODO create empty constructor and hashcode


    public TemperatureKey() {
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "TemperatureKey " +
                "'location '" + location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        temperature.solutions.model.TemperatureKey that = (temperature.solutions.model.TemperatureKey) o;

        return location.equals(that.getLocation());
    }

}
