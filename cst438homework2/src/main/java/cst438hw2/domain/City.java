package cst438hw2.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "city")
public class City {

    @Id
    private int ID;
    private String name;
    private String district;
    private int population;

    @Transient
    TempAndTime weather;

    @ManyToOne
    @JoinColumn(name = "countrycode", referencedColumnName = "code")
    private Country country;

    public City() {
    }

    public City(int ID, String name, String district, int population, Country country) {
        this.ID = ID;
        this.name = name;
        this.district = district;
        this.population = population;
        this.country = country;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public TempAndTime getWeather() {
        return weather;
    }

    public void setWeather(TempAndTime weather) {
        this.weather = weather;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        City other = (City) obj;
        if (ID != other.ID)
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (district == null) {
            if (other.district != null)
                return false;
        } else if (!district.equals(other.district))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (population != other.population)
            return false;
        if (weather == null) {
            if (other.weather != null)
                return false;
        } else if (!weather.equals(other.weather))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "City [ID=" + ID + ", name=" + name + ", district=" + district + ", population="
                + population + ", weather=" + weather + ", country=" + country + "]";
    }

}
