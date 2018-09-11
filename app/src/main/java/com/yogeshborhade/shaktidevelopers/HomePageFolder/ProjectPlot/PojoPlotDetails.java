package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

/**
 * Created by admin on 3/29/2018.
 */

public class PojoPlotDetails {

    private String plotNumber;
    private String plotEast;
    private String plotSouth;
    private String plotWest;
    private String plotNorth;
    private String plotStatus;
    private String plototalAreasqurefoot;
    private String plototalAreagunthe;
    private String plototalAreaacre;

    public  PojoPlotDetails(String plotNumber, String plotEast , String plotWest, String plotNorth,String plotSouth) {
        this.plotNumber = plotNumber;
        this.plotEast = plotEast;
        this.plotWest = plotWest;
        this.plotNorth = plotNorth;
        this.plotSouth = plotSouth;


    }
    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getPlotEast() {
        return plotEast;
    }

    public void setPlotEast(String plotEast) {
        this.plotEast = plotEast;
    }

    public String getPlotSouth() {
        return plotSouth;
    }

    public void setPlotSouth(String plotSouth) {
        this.plotSouth = plotSouth;
    }

    public String getPlotWest() {
        return plotWest;
    }

    public void setPlotWest(String plotWest) {
        this.plotWest = plotWest;
    }

    public String getPlotNorth() {
        return plotNorth;
    }

    public void setPlotNorth(String plotNorth) {
        this.plotNorth = plotNorth;
    }

    public String getPlotStatus() {
        return plotStatus;
    }

    public void setPlotStatus(String plotStatus) {
        this.plotStatus = plotStatus;
    }

    public String getPlototalAreasqurefoot() {
        return plototalAreasqurefoot;
    }

    public void setPlototalAreasqurefoot(String plototalAreasqurefoot) {
        this.plototalAreasqurefoot = plototalAreasqurefoot;
    }

    public String getPlototalAreagunthe() {
        return plototalAreagunthe;
    }

    public void setPlototalAreagunthe(String plototalAreagunthe) {
        this.plototalAreagunthe = plototalAreagunthe;
    }

    public String getPlototalAreaacre() {
        return plototalAreaacre;
    }

    public void setPlototalAreaacre(String plototalAreaacre) {
        this.plototalAreaacre = plototalAreaacre;
    }



}
