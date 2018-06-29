package net.engining.gateway.fts.sdk.key;

import net.engining.control.api.ContextKey;
import net.engining.control.api.KeyDefinition;
import net.engining.gateway.fts.sdk.bean.BizData;

@KeyDefinition(
		name = "辅助核算项数据BizData"
	)
public interface BizDataKey extends ContextKey<BizData>{

}
