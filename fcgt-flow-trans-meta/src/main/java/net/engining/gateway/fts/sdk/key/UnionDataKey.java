package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.UnionData;

@KeyDefinition(
		name = "联合贷数据UnionData"
	)
public interface UnionDataKey extends ContextKey<UnionData>{

}
