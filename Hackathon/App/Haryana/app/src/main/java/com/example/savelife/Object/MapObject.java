package com.example.savelife.Object;

public class MapObject {

    private String Map_address1 = "";
    private String Map_address2 = "";
    private String Map_address3 = "";
    private String Map_city = "";
    private String Map_state = "";
    private String Map_zip = "";


    public MapObject(String map_address1) {
        Map_address1 = map_address1;
    }

    public String getMap_address1() {
        return Map_address1;
    }

    public void setMap_address1(String map_address1) {
        Map_address1 = map_address1;
    }

    public String getMap_address2() {
        return Map_address2;
    }

    public void setMap_address2(String map_address2) {
        Map_address2 = map_address2;
    }

    public String getMap_address3() {
        return Map_address3;
    }

    public void setMap_address3(String map_address3) {
        Map_address3 = map_address3;
    }

    public String getMap_city() {
        return Map_city;
    }

    public void setMap_city(String map_city) {
        Map_city = map_city;
    }

    public String getMap_state() {
        return Map_state;
    }

    public void setMap_state(String map_state) {
        Map_state = map_state;
    }

    public String getMap_zip() {
        return Map_zip;
    }

    public void setMap_zip(String map_zip) {
        Map_zip = map_zip;
    }
}
