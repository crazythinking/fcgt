package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.repayment.RepaymentRequestData;

@KeyDefinition(
		name = "还款报文体LoanAccountBean"
	)
public interface RepaymentRequestDataKey extends ContextKey<RepaymentRequestData>{

}
