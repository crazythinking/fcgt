package net.engining.gateway.constants;

public enum DelFlagDef {
    /** 否 */	N("否"),
    /** 是 */	Y("是");

    private String delFlagDef;

    private DelFlagDef(String delFlagDef) {
        this.delFlagDef = delFlagDef;
    }

    public String getDelFlagDef() {
        return delFlagDef;
    }
}