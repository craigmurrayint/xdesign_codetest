package com.xdesigncodetest.model;

import com.opencsv.bean.CsvBindByName;


public class Munro {

    @CsvBindByName(column = "Running No")
    private int runningNo;
    @CsvBindByName(column = "DoBIH Number")
    private int dobihNumber;
    @CsvBindByName(column = "Streetmap")
    private String stretmap;
    @CsvBindByName(column = "Geograph")
    private String geograph;
    @CsvBindByName(column = "Hill-bagging")
    private String hillBagging;
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "SMC Section")
    private int smcSection;
    @CsvBindByName(column = "RHB Section")
    private String rhbSection;
    @CsvBindByName(column = "_Section")
    private String section;
    @CsvBindByName(column = "Height (m)")
    private double heightInMetre;
    @CsvBindByName(column = "Height (ft)")
    private double heightInFeet;
    @CsvBindByName(column = "Map 1:50")
    private String map150;
    @CsvBindByName(column = "Map 1:25")
    private String map125;
    @CsvBindByName(column = "Grid Ref")
    private String gridRef;
    @CsvBindByName(column = "GridRefXY")
    private String gridRefXY;
    @CsvBindByName(column = "xcoord")
    private int xcoord;
    @CsvBindByName(column = "ycoord")
    private int ycoord;
    @CsvBindByName(column = "1891")
    private String _1891;
    @CsvBindByName(column = "1921")
    private String _1921;
    @CsvBindByName(column = "1933")
    private String _1933;
    @CsvBindByName(column = "1953")
    private String _1953;
    @CsvBindByName(column = "1969")
    private String _1969;
    @CsvBindByName(column = "1974")
    private String _1974;
    @CsvBindByName(column = "1981")
    private String _1981;
    @CsvBindByName(column = "1984")
    private String _1984;
    @CsvBindByName(column = "1990")
    private String _1990;
    @CsvBindByName(column = "1997")
    private String _1997;
    @CsvBindByName(column = "Post 1997")
    private String post1997;
    @CsvBindByName(column = "Comments")
    private String comments;

    public int getRunningNo() {
        return runningNo;
    }

    public void setRunningNo(int runningNo) {
        this.runningNo = runningNo;
    }

    public int getDobihNumber() {
        return dobihNumber;
    }

    public void setDobihNumber(int dobihNumber) {
        this.dobihNumber = dobihNumber;
    }

    public String getStretmap() {
        return stretmap;
    }

    public void setStretmap(String stretmap) {
        this.stretmap = stretmap;
    }

    public String getGeograph() {
        return geograph;
    }

    public void setGeograph(String geograph) {
        this.geograph = geograph;
    }

    public String getHillBagging() {
        return hillBagging;
    }

    public void setHillBagging(String hillBagging) {
        this.hillBagging = hillBagging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSmcSection() {
        return smcSection;
    }

    public void setSmcSection(int smcSection) {
        this.smcSection = smcSection;
    }

    public String getRhbSection() {
        return rhbSection;
    }

    public void setRhbSection(String rhbSection) {
        this.rhbSection = rhbSection;
    }

    public double getHeightInMetre() {
        return heightInMetre;
    }

    public void setHeightInMetre(double heightInMetre) {
        this.heightInMetre = heightInMetre;
    }

    public double getHeightInFeet() {
        return heightInFeet;
    }

    public void setHeightInFeet(double heightInFeet) {
        this.heightInFeet = heightInFeet;
    }

    public String getMap150() {
        return map150;
    }

    public void setMap150(String map150) {
        this.map150 = map150;
    }

    public String getMap125() {
        return map125;
    }

    public void setMap125(String map125) {
        this.map125 = map125;
    }

    public String getGridRef() {
        return gridRef;
    }

    public void setGridRef(String gridRef) {
        this.gridRef = gridRef;
    }

    public String getGridRefXY() {
        return gridRefXY;
    }

    public void setGridRefXY(String gridRefXY) {
        this.gridRefXY = gridRefXY;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public String get_1891() {
        return _1891;
    }

    public void set_1891(String _1891) {
        this._1891 = _1891;
    }

    public String get_1921() {
        return _1921;
    }

    public void set_1921(String _1921) {
        this._1921 = _1921;
    }

    public String get_1933() {
        return _1933;
    }

    public void set_1933(String _1933) {
        this._1933 = _1933;
    }

    public String get_1953() {
        return _1953;
    }

    public void set_1953(String _1953) {
        this._1953 = _1953;
    }

    public String get_1969() {
        return _1969;
    }

    public void set_1969(String _1969) {
        this._1969 = _1969;
    }

    public String get_1974() {
        return _1974;
    }

    public void set_1974(String _1974) {
        this._1974 = _1974;
    }

    public String get_1981() {
        return _1981;
    }

    public void set_1981(String _1981) {
        this._1981 = _1981;
    }

    public String get_1984() {
        return _1984;
    }

    public void set_1984(String _1984) {
        this._1984 = _1984;
    }

    public String get_1990() {
        return _1990;
    }

    public void set_1990(String _1990) {
        this._1990 = _1990;
    }

    public String get_1997() {
        return _1997;
    }

    public void set_1997(String _1997) {
        this._1997 = _1997;
    }

    public String getPost1997() {
        return post1997;
    }

    public void setPost1997(String post1997) {
        this.post1997 = post1997;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
