package cst438hw2.domain;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CityInfo {

    private int id;
    private String name;
    private String countryCode;
    private String countryName;
    private String district;
    private int population;
    private double temp;
    private String time;

    public CityInfo() {

    }

    public CityInfo(City city) {
        super();
        id = city.getID();
        name = city.getName();
        countryCode = city.getCountry().getCode();
        countryName = city.getCountry().getName();
        district = city.getDistrict();
        population = city.getPopulation();
        double tempKelvin = city.getWeather().getTemp();

        // convert temp from degrees Kelvin to degrees Fahrenheit
        temp = Math.round((tempKelvin - 273.15) * 9.0 / 5.0 + 32.0);
        System.out.println("Kelvin Temp:" + tempKelvin + ", temp:" + temp);

        // convert the time to their local time
        long dt = city.getWeather().getTime();
        int timezone = city.getWeather().getTimezone();
        Date localDate = new Date();
        TimeZone localTimeZone = TimeZone.getDefault();
        int localTimeOffset = localTimeZone.getOffset(localDate.getTime()) / 1000;
        Date date = new Date((dt + timezone - localTimeOffset) * 1000);
        time = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
        System.out.println("dt:" + dt + " timezone:" + timezone + " local time:" + time);
    }

    public CityInfo(int id, String name, String countryCode, String countryName, String district,
            int population, double temp, String time) {
        super();
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.district = district;
        this.population = population;
        this.temp = temp;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CityInfo [id=" + id + ", name=" + name + ", countryCode=" + countryCode
                + ", countryName=" + countryName + ", district=" + district + ", population="
                + population + ", temp=" + temp + ", time=" + time + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((district == null) ? 0 : district.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + population;
        long temp;
        temp = Double.doubleToLongBits(this.temp);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CityInfo other = (CityInfo) obj;
        if (countryCode == null) {
            if (other.countryCode != null)
                return false;
        } else if (!countryCode.equals(other.countryCode))
            return false;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        if (district == null) {
            if (other.district != null)
                return false;
        } else if (!district.equals(other.district))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (population != other.population)
            return false;
        if (Double.doubleToLongBits(temp) != Double.doubleToLongBits(other.temp))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

}
