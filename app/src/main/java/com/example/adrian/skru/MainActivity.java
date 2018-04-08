package com.example.adrian.skru;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Request request = createRequest(this, this);
        Gson gson = new Gson();
        String jsonReq = gson.toJson(request);
        Log.v("Json", "Created json reqest: " + jsonReq);

    }

    private Request createRequest(Context context, Activity activity) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (telephonyManager == null) {
            return null;
        }

        int MY_PERMISSION_ACCESS_COURSE_LOCATION = 1;
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSION_ACCESS_COURSE_LOCATION);
        }

        Request request = new Request();
        request.setHomeMobileCountryCode(Integer.parseInt(telephonyManager.getNetworkCountryIso()));
        request.setHomeMobileNetworkCode(Integer.parseInt(telephonyManager.getSimOperator()));
        request.setCarrier(telephonyManager.getNetworkOperatorName());
        switch (telephonyManager.getNetworkType()) {
            case 13:
                request.setRadioType("lte");
            case 16:
                request.setRadioType("gsm");
            case 4:
                request.setRadioType("cdma");
            default:
                request.setRadioType("wcdma");
        }

        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();
        if (cellInfos == null) {
            return null;
        }
        for (CellInfo info : cellInfos) {
            request.addCellTower(getCellTower(info));
        }

        return request;
    }

    private CellTower getCellTower(CellInfo cellInfo) {

        CellTower cellTower = new CellTower();

        if (cellInfo.getClass().equals(CellInfoLte.class)) {
            CellIdentityLte cellInfoLte = ((CellInfoLte) cellInfo).getCellIdentity();
            cellTower.setCellId(cellInfoLte.getCi());
            cellTower.setLocationAreaCode(cellInfoLte.getTac());
            cellTower.setMobileCountryCode(cellInfoLte.getMcc());
            cellTower.setMobileNetworkCode(cellInfoLte.getMnc());
            cellTower.setSignalStrength(((CellInfoLte) cellInfo).getCellSignalStrength().getDbm());
        } else if (cellInfo.getClass().equals(CellInfoWcdma.class)) {
            CellIdentityWcdma cellInfoWcdma = ((CellInfoWcdma) cellInfo).getCellIdentity();
            cellTower.setCellId(cellInfoWcdma.getCid());
            cellTower.setLocationAreaCode(cellInfoWcdma.getLac());
            cellTower.setMobileCountryCode(cellInfoWcdma.getMcc());
            cellTower.setMobileNetworkCode(cellInfoWcdma.getMnc());
            cellTower.setSignalStrength(((CellInfoWcdma) cellInfo).getCellSignalStrength().getDbm());
        } else if (cellInfo.getClass().equals(CellInfoGsm.class)) {
            CellIdentityGsm cellInfoGsm = ((CellInfoGsm) cellInfo).getCellIdentity();
            cellTower.setCellId(cellInfoGsm.getCid());
            cellTower.setLocationAreaCode(cellInfoGsm.getLac());
            cellTower.setMobileCountryCode(cellInfoGsm.getMcc());
            cellTower.setMobileNetworkCode(cellInfoGsm.getMnc());
            cellTower.setSignalStrength(((CellInfoGsm) cellInfo).getCellSignalStrength().getDbm());
        } else if (cellInfo.getClass().equals(CellInfoCdma.class)) {
            CellIdentityCdma cellInfoCdma = ((CellInfoCdma) cellInfo).getCellIdentity();
            cellTower.setCellId(cellInfoCdma.getBasestationId());
            cellTower.setLocationAreaCode(cellInfoCdma.getNetworkId());
            cellTower.setMobileCountryCode(0);
            cellTower.setMobileNetworkCode(0);
            cellTower.setSignalStrength(((CellInfoCdma) cellInfo).getCellSignalStrength().getDbm());
        }
        return cellTower;
    }


}
