package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.resultBack.ResponseData;

@KeyDefinition(
		name = "联机交易结果"
	)
public interface ResponseDataKey extends ContextKey<ResponseData>{

}
