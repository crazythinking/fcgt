package net.engining.gateway.constants;

/**
 * 交易状态码定义
 */
public enum TranStatus implements AbstractCode {
    NODO("0", "待处理"),
    DOING("1", "处理中"),
    SUCCESS("2", "处理成功"),
    FAIL("3", "处理失败");

    private String value;
    private String chName;

    private TranStatus(String value, String chName) {
        this.value = value;
        this.chName = chName;
    }

    public static TranStatus getEnum(String value) {
        TranStatus[] crc = values();
        for (int i = 0; i < crc.length; i++) {
            if (crc[i].getCode().equals(value)) {
                return crc[i];
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.chName;
    }
}
