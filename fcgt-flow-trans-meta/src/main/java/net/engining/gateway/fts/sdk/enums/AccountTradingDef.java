package net.engining.gateway.fts.sdk.enums;

public enum AccountTradingDef {
	/** 贷款发放记账 */	D,
	/** 授额提降额记账*/	S,
	/** 费用收取记账*/	F,
	/** 还款记账*/	H,
	/** 退款记账*/	T,
	/** 利息计提*/	INTEACC,
	/** 罚息计提*/	PINTACC,
//	/** 余额成份结转*/ TRANSFO,
	/** 批量还款*/	BATREPA,
	/** 冲正还款记账*/  C_T,
	/** 冲正利息计提*/  C_INTEACC,
	/** 冲正罚息计提*/  C_PINTACC,
	/** 冲正本金结转*/  C_LBAL,
	/** 冲正利息结转*/  C_INTE,
	/** 冲正罚息结转*/  C_PINT;

}
