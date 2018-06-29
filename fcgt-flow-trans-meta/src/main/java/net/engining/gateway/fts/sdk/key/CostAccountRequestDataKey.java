package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.cost.CostAccountRequestData;

@KeyDefinition(
		name = "费用收取报文体CostAccountRequestData"
	)
public interface CostAccountRequestDataKey extends ContextKey<CostAccountRequestData>{

}
