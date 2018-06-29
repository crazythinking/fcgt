package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.EveryDayAccountingBean;

@KeyDefinition(
		name = "总账交易流水Bean"
	)
public interface EveryDayAccountingBeanKey extends ContextKey<EveryDayAccountingBean>{

}
