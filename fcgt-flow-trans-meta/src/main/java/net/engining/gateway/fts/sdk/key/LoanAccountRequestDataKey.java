package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.loan.LoanAccountRequestData;

@KeyDefinition(
		name = "贷款发放报文体LoanAccountRequestData"
	)
public interface LoanAccountRequestDataKey extends ContextKey<LoanAccountRequestData>{

}
