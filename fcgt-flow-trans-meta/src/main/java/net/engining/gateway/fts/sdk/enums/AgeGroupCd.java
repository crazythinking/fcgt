package net.engining.gateway.fts.sdk.enums;

import net.engining.pg.support.meta.EnumInfo;

/**
 * 账龄组代码：
 * Normality - 正常
 * Attention - 逾期
 * Secondary - 次级
 * Suspicious - 可疑
 * Loss - 损失
 * Under4M3 - M3（含）以下
 * Above4M3 - M3以上
 * 
 * "D|逾期",
 * "A|非应计",
 * "S|按期结转-逾期",
 * "Z|按期结转-非应计"
 */
@EnumInfo({
	"Normality|正常 账龄0",
	"Attention|关注 账龄1-3",
	"Secondary|次级 账龄4",
	"Suspicious|可疑 账龄5-6",
	"Loss|损失 账龄7-13",
	"Under4M3|M3（含）以下",
	"Above4M3|M3以上"
})
public enum AgeGroupCd {
	/**
	 * 正常，账龄0
	 */
	Normality, 
	
	/**
	 * 关注，账龄1-3（原逾期 D）
	 */
	Attention, 
	
	/**
	 * 次级，账龄4
	 */
	Secondary,
	
	/**
	 * 可疑，账龄5-6
	 */
	Suspicious, 
	
	/**
	 * 损失，账龄7-13
	 */
	Loss, 
	
	/**
	 * M3（含）以下
	 */
	Under4M3, 
	
	/**
	 * M3以上（原非应计 S）
	 */
	Above4M3,
	
	/**
	 * 按期结转-逾期
	 */
	S,
	
	/**
	 * 按期结转-非应计
	 */
	Z
}