package com.geosis.messageviewdemo;

import com.amap.api.maps.model.LatLng;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String m_time;
    private String m_admin_region;
    private double m_longitude;
    private double m_latitude;
    private int m_rank;
    private int m_description;
    private int m_info_source;
    private String m_remark="空";

    public Message(Date time,String admin_region,LatLng location,int rank,int description,int info_source,String remark)
    {
        m_admin_region=admin_region;
        m_longitude=location.longitude;
        m_latitude=location.latitude;
        m_rank=rank;
        m_description=description;
        m_info_source=info_source;
        m_remark=remark;

        m_time=(time.getYear()+1900)+"-"+time.getMonth()+"-"+time.getDay()+" "+
                time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
    }

    public String getM_time() {
        return m_time;
    }

    public String getM_admin_region() {
        return m_admin_region;
    }

    public LatLng getM_location() {
        return new LatLng(m_latitude,m_longitude);
    }

    public int getM_rank() {
        return m_rank;
    }

    public int getM_description() {
        return m_description;
    }

    public int getM_info_source() {
        return m_info_source;
    }

    public String getM_remark() {
        return m_remark;
    }
}
