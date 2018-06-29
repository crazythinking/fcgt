package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.limit.AccountLimitRequestData;

@KeyDefinition(
		name = "提降额报文体AccountLimitRequestData"
	)
public interface AccountLimitRequestDataKey extends ContextKey<AccountLimitRequestData>{

}
