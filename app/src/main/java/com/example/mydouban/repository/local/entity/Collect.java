package com.example.mydouban.repository.local.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity()
public class Collect {

    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "title")
    private String title;
    @Property(nameInDb = "image")
    private String image;
    @Property(nameInDb = "year")
    private Integer year;
    @Property(nameInDb = "average")
    private Float average;
    @Property(nameInDb = "country")
    private String country;
    @Property(nameInDb = "genres")
    private String genres;
    @Property(nameInDb = "directors")
    private String directors;
    @Property(nameInDb = "casts")
    private String casts;
    @Property(nameInDb = "createTime")
    private String createTime;

    @Generated(hash = 948978298)
    public Collect(Long id, String title, String image, Integer year, Float average,
                   String country, String genres, String directors, String casts,
                   String createTime) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.year = year;
        this.average = average;
        this.country = country;
        this.genres = genres;
        this.directors = directors;
        this.casts = casts;
        this.createTime = createTime;
    }

    public Collect(String title, String image, Integer year, Float average,
                   String country, String genres, String directors, String casts,
                   String createTime) {
        this.title = title;
        this.image = image;
        this.year = year;
        this.average = average;
        this.country = country;
        this.genres = genres;
        this.directors = directors;
        this.casts = casts;
        this.createTime = createTime;
    }

    @Generated(hash = 1726975718)
    public Collect() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getCasts() {
        return casts;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
