package com.geosis.messageviewdemo;

public class Message {

    private String m_content;
    private String m_location;
    private int m_year;
    private int m_month;
    private int m_day;

    public Message(String content,String location,int year,int month,int day)
    {
        m_content=content;
        m_location=location;
        m_year=year;
        m_month=month;
        m_day=day;
    }

    public String getContent() {
        return m_content;
    }

    public String getLocation() {
        return m_location;
    }

    public int getYear() {
        return m_year;
    }

    public int getMonth() {
        return m_month;
    }

    public int getDay() {
        return m_day;
    }
}
