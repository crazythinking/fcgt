package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.refunds.RefundsRequestData;

@KeyDefinition(
		name = "退款报文体LoanAccountBean"
	)
public interface RefundsRequestDataKey extends ContextKey<RefundsRequestData>{

}
