package net.engining.gateway.fts.sdk.bean;

import java.math.BigDecimal;

import net.engining.pg.web.BaseResponseBean;

public class UnionData extends BaseResponseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 子账户类型
	 */
	private String subAcctType;
	/**
	 * 账期
	 */
	private Integer stmHist;
	/**
	 * 我方额度
	 */
	private BigDecimal ownAmt;
	/**
	 * 参与方额度
	 */
	private BigDecimal otherAmt;

	public String getSubAcctType() {
		return subAcctType;
	}

	public void setSubAcctType(String subAcctType) {
		this.subAcctType = subAcctType;
	}

	public Integer getStmHist() {
		return stmHist;
	}

	public void setStmHist(Integer stmHist) {
		this.stmHist = stmHist;
	}

	public BigDecimal getOwnAmt() {
		return ownAmt;
	}

	public void setOwnAmt(BigDecimal ownAmt) {
		this.ownAmt = ownAmt;
	}

	public BigDecimal getOtherAmt() {
		return otherAmt;
	}

	public void setOtherAmt(BigDecimal otherAmt) {
		this.otherAmt = otherAmt;
	}

}
