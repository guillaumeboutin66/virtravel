package fr.guillaumeboutin.virtravel.Classes;

import com.google.android.gms.maps.model.LatLng;

import io.realm.RealmObject;

/**
 * Created by guillaumeboutin on 27/03/2017.
 */

public class StepTravel extends RealmObject {
    private int id;
    private int idTravel;
    private String name;
    private String url;
    private Double coordLong;
    private Double coordLatt;

    public StepTravel(){

    }

    public StepTravel(int id, int idTravel, String name, String url, Double coordLong, Double coordLatt){
        this.id = id;
        this.idTravel = idTravel;
        this.name = name;
        this.url = url;
        this.coordLong = coordLong;
        this.coordLatt = coordLatt;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(int idTravel) {
        this.idTravel = idTravel;
    }

    public Double getCoordLong() {
        return coordLong;
    }

    public void setCoordLong(Double coordLong) {
        this.coordLong = coordLong;
    }

    public Double getCoordLatt() {
        return coordLatt;
    }

    public void setCoordLatt(Double coordLatt) {
        this.coordLatt = coordLatt;
    }
}
