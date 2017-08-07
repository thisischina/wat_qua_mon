package com.hd.ibus.pojo;

/**
 * 设备session信息
 * Created by ThinkPad on 2017-07-28.
 */
public class SessionData {

    private String DTUID;//设备id

    private String DSCADDR;

    private String KEEPALIVE;//心跳包参数

    private String UARTCFG;

    private String DEBUGMODE;

    private String DTUFILTER;

    public String getDSCADDR() {
        return DSCADDR;
    }

    public void setDSCADDR(String DSCADDR) {
        this.DSCADDR = DSCADDR;
    }

    public String getDTUID() {
        return DTUID;
    }

    public void setDTUID(String DTUID) {
        this.DTUID = DTUID;
    }

    public String getKEEPALIVE() {
        return KEEPALIVE;
    }

    public void setKEEPALIVE(String KEEPALIVE) {
        this.KEEPALIVE = KEEPALIVE;
    }

    public String getUARTCFG() {
        return UARTCFG;
    }

    public void setUARTCFG(String UARTCFG) {
        this.UARTCFG = UARTCFG;
    }

    public String getDEBUGMODE() {
        return DEBUGMODE;
    }

    public void setDEBUGMODE(String DEBUGMODE) {
        this.DEBUGMODE = DEBUGMODE;
    }

    public String getDTUFILTER() {
        return DTUFILTER;
    }

    public void setDTUFILTER(String DTUFILTER) {
        this.DTUFILTER = DTUFILTER;
    }
}
