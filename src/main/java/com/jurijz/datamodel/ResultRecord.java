package com.jurijz.datamodel;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by jurijz on 2/27/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
public class ResultRecord implements Comparable<ResultRecord>, Serializable {

    private String place;
    private String name;
    private double run100m;
    private double longJump;
    private double shotPut;
    private double highJump;
    private double run400m;
    private double run110mHurdles;
    private double discusThrow;
    private double poleVault;
    private double javelinThrow;
    private double run1500m;

    @XmlTransient
    private double[] results;
    private int resultSum;

    public ResultRecord() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRun100m() {
        return run100m;
    }

    public void setRun100m(double run100m) {
        this.run100m = run100m;
    }

    public double getLongJump() {
        return longJump;
    }

    public void setLongJump(double longJump) {
        this.longJump = longJump;
    }

    public double getShotPut() {
        return shotPut;
    }

    public void setShotPut(double shotPut) {
        this.shotPut = shotPut;
    }

    public double getHighJump() {
        return highJump;
    }

    public void setHighJump(double highJump) {
        this.highJump = highJump;
    }

    public double getRun400m() {
        return run400m;
    }

    public void setRun400m(double run400m) {
        this.run400m = run400m;
    }

    public double getRun110mHurdles() {
        return run110mHurdles;
    }

    public void setRun110mHurdles(double run110mHurdles) {
        this.run110mHurdles = run110mHurdles;
    }

    public double getDiscusThrow() {
        return discusThrow;
    }

    public void setDiscusThrow(double discusThrow) {
        this.discusThrow = discusThrow;
    }

    public double getPoleVault() {
        return poleVault;
    }

    public void setPoleVault(double poleVault) {
        this.poleVault = poleVault;
    }

    public double getJavelinThrow() {
        return javelinThrow;
    }

    public void setJavelinThrow(double javelinThrow) {
        this.javelinThrow = javelinThrow;
    }

    public double getRun1500m() {
        return run1500m;
    }

    public void setRun1500m(double run1500m) {
        this.run1500m = run1500m;
    }

    public double[] getResults() {
        return results;
    }

    public void setResults(double[] results) {
        this.results = results;
    }

    public int getResultSum() {
        return resultSum;
    }

    public void setResultSum(int resultSum) {
        this.resultSum = resultSum;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public int compareTo(ResultRecord o) {
        return Integer.compare(resultSum, o.getResultSum());
    }
}
