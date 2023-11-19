package MODEL;

import java.sql.Date;
import java.sql.Time;

public class Match {
    private String div; // division
    private Date date;
    private Time time;
    private String homeTeamAbv;
    private String awayTeamAbv;
    private int fthg; // full time home goals
    private int ftag; // full time away goals
    private String ftr; // full time result
    private int hthg; // halftime home goals
    private int htag; // halftime away goals
    private String htr; // halftime result
    private String referee;
    private int hs; // home team shots
    private int as; // away team shots
    private int hst; // home team shots on goal
    private int ast; // away team shots on goal
    private int hf; // home team fouls
    private int af; // away team fouls
    private int hc; // home team corners
    private int ac; // away team corners
    private int hy; // home team yellow cards
    private int ay; // away team yellow cards
    private int hr; // home team red cards
    private int ar; // away team red cards

    // Constructor
    public Match(String div, Date date, Time time, String homeTeamAbv, String awayTeamAbv, int fthg, int ftag,
            String ftr, int hthg, int htag, String htr, String referee, int hs, int as, int hst, int ast, int hf,
            int af, int hc, int ac, int hy, int ay, int hr, int ar) {
        this.div = div;
        this.date = date;
        this.time = time;
        this.homeTeamAbv = homeTeamAbv;
        this.awayTeamAbv = awayTeamAbv;
        this.fthg = fthg;
        this.ftag = ftag;
        this.ftr = ftr;
        this.hthg = hthg;
        this.htag = htag;
        this.htr = htr;
        this.referee = referee;
        this.hs = hs;
        this.as = as;
        this.hst = hst;
        this.ast = ast;
        this.hf = hf;
        this.af = af;
        this.hc = hc;
        this.ac = ac;
        this.hy = hy;
        this.ay = ay;
        this.hr = hr;
        this.ar = ar;
    }

    // Getters and setters 
	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getHomeTeamAbv() {
		return homeTeamAbv;
	}

	public void setHomeTeamAbv(String homeTeamAbv) {
		this.homeTeamAbv = homeTeamAbv;
	}

	public String getAwayTeamAbv() {
		return awayTeamAbv;
	}

	public void setAwayTeamAbv(String awayTeamAbv) {
		this.awayTeamAbv = awayTeamAbv;
	}

	public int getFthg() {
		return fthg;
	}

	public void setFthg(int fthg) {
		this.fthg = fthg;
	}

	public int getFtag() {
		return ftag;
	}

	public void setFtag(int ftag) {
		this.ftag = ftag;
	}

	public String getFtr() {
		return ftr;
	}

	public void setFtr(String ftr) {
		this.ftr = ftr;
	}

	public int getHthg() {
		return hthg;
	}

	public void setHthg(int hthg) {
		this.hthg = hthg;
	}

	public int getHtag() {
		return htag;
	}

	public void setHtag(int htag) {
		this.htag = htag;
	}

	public String getHtr() {
		return htr;
	}

	public void setHtr(String htr) {
		this.htr = htr;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public int getHs() {
		return hs;
	}

	public void setHs(int hs) {
		this.hs = hs;
	}

	public int getAs() {
		return as;
	}

	public void setAs(int as) {
		this.as = as;
	}

	public int getHst() {
		return hst;
	}

	public void setHst(int hst) {
		this.hst = hst;
	}

	public int getAst() {
		return ast;
	}

	public void setAst(int ast) {
		this.ast = ast;
	}

	public int getHf() {
		return hf;
	}

	public void setHf(int hf) {
		this.hf = hf;
	}

	public int getAf() {
		return af;
	}

	public void setAf(int af) {
		this.af = af;
	}

	public int getHc() {
		return hc;
	}

	public void setHc(int hc) {
		this.hc = hc;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public int getHy() {
		return hy;
	}

	public void setHy(int hy) {
		this.hy = hy;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}

	public int getHr() {
		return hr;
	}

	public void setHr(int hr) {
		this.hr = hr;
	}

	public int getAr() {
		return ar;
	}

	public void setAr(int ar) {
		this.ar = ar;
	}
	
	// toString to print the values of all Match attributes
    @Override
    public String toString() {
        return "Match{" +
                "div='" + div + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", homeTeamAbv='" + homeTeamAbv + '\'' +
                ", awayTeamAbv='" + awayTeamAbv + '\'' +
                ", fthg=" + fthg +
                ", ftag=" + ftag +
                ", ftr='" + ftr + '\'' +
                ", hthg=" + hthg +
                ", htag=" + htag +
                ", htr='" + htr + '\'' +
                ", referee='" + referee + '\'' +
                ", hs=" + hs +
                ", as=" + as +
                ", hst=" + hst +
                ", ast=" + ast +
                ", hf=" + hf +
                ", af=" + af +
                ", hc=" + hc +
                ", ac=" + ac +
                ", hy=" + hy +
                ", ay=" + ay +
                ", hr=" + hr +
                ", ar=" + ar +
                '}';
    }

}
