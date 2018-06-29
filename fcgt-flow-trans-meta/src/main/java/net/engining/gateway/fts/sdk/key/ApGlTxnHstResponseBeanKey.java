package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.ApGlTxnHstResponseBean;

@KeyDefinition(
		name = "总账交易流水ApGlTxnHstResponseBean"
	)
public interface ApGlTxnHstResponseBeanKey extends ContextKey<ApGlTxnHstResponseBean>{

}
