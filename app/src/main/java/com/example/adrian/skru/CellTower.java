package com.example.adrian.skru;

/**
 * Created by adrian on 07.04.2018.
 */

public class CellTower {
    private int cellId;
    private int locationAreaCode;
    private int mobileCountryCode;
    private int mobileNetworkCode;
    private int signalStrength;

    public int getCellId() {
        return cellId;
    }

    public int getLocationAreaCode() {
        return locationAreaCode;
    }

    public int getMobileCountryCode() {
        return mobileCountryCode;
    }

    public int getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public void setLocationAreaCode(int locationAreaCode) {
        this.locationAreaCode = locationAreaCode;
    }

    public void setMobileCountryCode(int mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public void setMobileNetworkCode(int mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }
}
/*

 cellId (required): Unique identifier of the cell. On GSM, this is the Cell ID (CID);
 CDMA networks use the Base Station ID (BID). WCDMA networks use the UTRAN/GERAN Cell Identity
 (UC-Id), which is a 32-bit value concatenating the Radio Network Controller (RNC) and Cell ID.
 Specifying only the 16-bit Cell ID value in WCDMA networks may return inaccurate results.
 locationAreaCode (required): The Location Area Code (LAC) for GSM and WCDMA networks. The Network
 ID (NID) for CDMA networks.
 mobileCountryCode (required): The cell tower's Mobile Country Code (MCC).
 mobileNetworkCode (required): The cell tower's Mobile Network Code. This is the MNC for GSM
 and WCDMA; CDMA uses the System ID (SID).
 The following optional fields are not currently used, but may be included if values are available.

 age: The number of milliseconds since this cell was primary. If age is 0, the cellId represents
 a current measurement.
 signalStrength: Radio signal strength measured in dBm.
 timingAdvance: The timing advance value.
 */