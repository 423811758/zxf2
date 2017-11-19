package com.wzd.zxf.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by wzd on 2017/11/19.
 */

public class Fund extends BaseBmob {
    //基金代码
    private String fundcode;
    //基金名称
    private String name;
    private String jzrq;
    private String dwjz;
    //估值
    private String gsz;
    //涨幅
    private String gszzl;
    //估算时间
    private String gztime;

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public String getDwjz() {
        return dwjz;
    }

    public void setDwjz(String dwjz) {
        this.dwjz = dwjz;
    }

    public String getGsz() {
        return gsz;
    }

    public void setGsz(String gsz) {
        this.gsz = gsz;
    }

    public String getGszzl() {
        return gszzl;
    }

    public void setGszzl(String gszzl) {
        this.gszzl = gszzl;
    }

    public String getGztime() {
        return gztime;
    }

    public void setGztime(String gztime) {
        this.gztime = gztime;
    }

    public void findByKey(String key, List<String> values, FindListener<Fund> findListener){
        BmobQuery<Fund> bmobQuery = new BmobQuery<Fund>();
        bmobQuery.addWhereContainsAll(key, values);
        bmobQuery.findObjects(findListener);
    }

    public void findAll(FindListener<Fund> findListener) {
        BmobQuery<Fund> bmobQuery = new BmobQuery<Fund>();
        bmobQuery.findObjects(findListener);
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundcode='" + fundcode + '\'' +
                ", name='" + name + '\'' +
                ", jzrq='" + jzrq + '\'' +
                ", dwjz='" + dwjz + '\'' +
                ", gsz='" + gsz + '\'' +
                ", gszzl='" + gszzl + '\'' +
                ", gztime='" + gztime + '\'' +
                '}';
    }
}
