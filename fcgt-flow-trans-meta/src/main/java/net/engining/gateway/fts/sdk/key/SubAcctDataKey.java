package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.SubAcctData;

@KeyDefinition(
		name = "交易前或后余额成分SubAcctData"
	)
public interface SubAcctDataKey extends ContextKey<SubAcctData>{

}
