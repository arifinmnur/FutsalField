package com.riverstream.futsalfield.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ArieDZ_2 on 8/5/2018.
 */
@IgnoreExtraProperties
public class CariLawan {
    private String namaTeamHome;
    private String tempatTanding;
    private String noHp;
    public CariLawan(String namaTeamHome, String tempatTanding, String noHp){

        this.namaTeamHome = namaTeamHome;
        this.tempatTanding = tempatTanding;
        this.noHp=noHp;
    }
    public CariLawan(){

    }

    public String getNamaTeamHome() {
        return namaTeamHome;
    }

    public void setNamaTeamHome(String namaTeamHome) {
        this.namaTeamHome = namaTeamHome;
    }

    public String getTempatTanding() {
        return tempatTanding;
    }

    public void setTempatTanding(String tempatTanding) {
        this.tempatTanding = tempatTanding;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
