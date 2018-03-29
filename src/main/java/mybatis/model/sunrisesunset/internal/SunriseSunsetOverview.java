package mybatis.model.sunrisesunset.internal;

public class SunriseSunsetOverview {

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    double latitude;
    double longitude;
    String day_length;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDay_length() {
        return day_length;
    }

    public void setDay_length(String day_length) {
        this.day_length = day_length;
    }
}
