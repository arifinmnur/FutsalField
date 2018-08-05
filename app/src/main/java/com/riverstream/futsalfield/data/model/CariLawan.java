package com.riverstream.futsalfield.data.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ArieDZ_2 on 8/5/2018.
 */

public class CariLawan {
    private String namaTeamHome, namaTeamAway;
    private List<User> lawanHome;
    private List<User> lawanAway;
    private String tempatTanding;
    private int image;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date tglTanding;

    public CariLawan(String namaTeamHome, String namaTeamAway, List<User> lawanHome, List<User> lawanAway, String tempatTanding) {
        this.namaTeamHome = namaTeamHome;
        this.namaTeamAway = namaTeamAway;
        this.lawanHome = lawanHome;
        this.lawanAway = lawanAway;
        this.tempatTanding = tempatTanding;
    }

    public CariLawan(String namaTeamHome, List<User> lawanHome, String tempatTanding) {
        this.namaTeamHome = namaTeamHome;
        this.lawanHome = lawanHome;
        this.tempatTanding = tempatTanding;
    }

    public List<User> getLawanHome() {
        return lawanHome;
    }

    public void setLawanHome(List<User> lawanHome) {
        this.lawanHome = lawanHome;
    }

    public List<User> getLawanAway() {
        return lawanAway;
    }

    public void setLawanAway(List<User> lawanAway) {
        this.lawanAway = lawanAway;
    }

    public String getTempatTanding() {
        return tempatTanding;
    }

    public void setTempatTanding(String tempatTanding) {
        this.tempatTanding = tempatTanding;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Date getTglTanding() {
        return tglTanding;
    }

    public void setTglTanding(Date tglTanding) {
        this.tglTanding = tglTanding;
    }

    public String getNamaTeamHome() {
        return namaTeamHome;
    }

    public void setNamaTeamHome(String namaTeamHome) {
        this.namaTeamHome = namaTeamHome;
    }

    public String getNamaTeamAway() {
        return namaTeamAway;
    }

    public void setNamaTeamAway(String namaTeamAway) {
        this.namaTeamAway = namaTeamAway;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
