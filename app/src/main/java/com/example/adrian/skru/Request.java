package com.example.adrian.skru;

import java.util.ArrayList;

/**
 * Created by adrian on 07.04.2018.
 */

public class Request {
    private int homeMobileCountryCode;
    //The mobile country code (MCC) for the device's home network.
    private int homeMobileNetworkCode;
    //The mobile network code (MNC) for the device's home network.
    private String radioType;
    //The mobile radio type. Supported values are lte, gsm, cdma, and wcdma. While this field is optional, it should be included if a value is available, for more accurate results.
    private String carrier;
    //The carrier name.
    private boolean considerIp;

    public int getHomeMobileCountryCode() {
        return homeMobileCountryCode;
    }

    public int getHomeMobileNetworkCode() {
        return homeMobileNetworkCode;
    }

    public String getRadioType() {
        return radioType;
    }

    public String getCarrier() {
        return carrier;
    }

    public boolean isConsiderIp() {
        return considerIp;
    }

    public ArrayList<CellTower> getCellTowers() {
        return cellTowers;
    }

    //Specifies whether to fall back to IP geolocation if wifi and cell tower signals are not available. Defaults to true. Set considerIp to false to disable fall back.
    private ArrayList<CellTower> cellTowers;

    public Request() {
        considerIp = false;
    }

    public void setHomeMobileCountryCode(int homeMobileCountryCode) {
        this.homeMobileCountryCode = homeMobileCountryCode;
    }

    public void setHomeMobileNetworkCode(int homeMobileNetworkCode) {
        this.homeMobileNetworkCode = homeMobileNetworkCode;
    }

    public void setRadioType(String radioType) {
        this.radioType = radioType;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void addCellTower(CellTower cellTower) {
        cellTowers.add(cellTower);
    }

    //An array of cell tower objects. See the Cell Tower Objects section below.
}
