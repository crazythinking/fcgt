package net.engining.gateway.constants;

/**
 * CE RMQ常量类.
 */
public enum CeRMQConstants implements AbstractCode {

    EXCHG_CE_SIMPLE_CREDIT_APPLY("ce_exchange_simplecreditapply", "简易授信申请交换机"),
    QUEUE_CE_SIMPLE_CREDIT_APPLY_KEY("ce_queue_simplecreditapply_key", "简易授信申请队列"),
    
    EXCHG_CE_OTSIMPLE_CREDIT_APPLY("ce_exchange_otsimplecreditapply", "简易授信申请-先授信后开户交换机"),
    QUEUE_CE_OTSIMPLE_CREDIT_APPLY_KEY("ce_queue_otsimplecreditapply_key", "简易授信申请-先授信后开户队列"),
    
    /**
     * 画像申请队列
     */
    EXCHG_CE_PORTRAIT_APPLY("ce_exchange_portraitapply", "画像申请交换机"),
    QUEUE_CE_PORTRAIT_APPLY_KEY("ce_queue_portraitapply_key", "画像申请队列"),

    /**
     * 通用授信
     */
    EXCHG_CE_COMMON_CREDIT_APPLY("ce_exchange_commoncreditapply", "通用授信申请交换机"),
    QUEUE_CE_COMMON_CREDIT_APPLY_KEY("ce_queue_commoncreditapply_key", "通用授信申请队列"),
    
    /**
     * 自动续约
     */
    EXCHG_CE_AUTO_RENEW_APPLY("ce_exchange_autorenewapply", "自动续约申请交换机"),
    QUEUE_CE_AUTO_RENEW_APPLY_KEY("ce_queue_autorenewapply_key", "自动续约申请队列"),
    
    /**
     * 调额
     */
    EXCHG_CE_ADJUST_AMT_APPLY("ce_exchange_adjustamtapply", "调额申请交换机"),
    QUEUE_CE_ADJUST_AMT_APPLY_KEY("ce_queue_adjustamtapply_key", "调额申请队列"),
    
	/**
	 * 大数据异步通知
	 */
	EXCHG_CE_BDP_REPLY("ce_exchange_bdpreply", "大数据异步通知交换机"),
	QUEUE_CE_BDP_REPLY_KEY("ce_queue_bdpreply_key", "大数据异步通知队列");


    private String value;
    private String chName;

    private CeRMQConstants(String value, String chName) {
        this.value = value;
        this.chName = chName;
    }

    public static CeRMQConstants getEnum(String value) {
        CeRMQConstants[] crc = values();
        for (int i = 0; i < crc.length; i++) {
            if (crc[i].getCode().equals(value)) {
                return crc[i];
            }
        }
        return null;
    }

    public String getCode() {
        return this.value;
    }

    public String getName() {
        return this.chName;
    }
}
