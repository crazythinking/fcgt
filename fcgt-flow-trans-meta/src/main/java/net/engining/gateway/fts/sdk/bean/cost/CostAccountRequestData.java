package net.engining.gateway.fts.sdk.bean.cost;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import net.engining.gateway.fts.sdk.bean.BizData;
import net.engining.gateway.fts.sdk.bean.SubAcctData;
import net.engining.gateway.fts.sdk.bean.UnionData;
import net.engining.pg.web.BaseResponseBean;

public class CostAccountRequestData extends BaseResponseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品参数唯一标识
	 */
	@NotBlank
	private String productId;
	/**
	 * 客户唯一标识
	 */
	@NotBlank
	private String custId;
	/**
	 * 借据号
	 */
	private String iouNo;
	
	/**
	 * 账户编号
	 */
	private Integer acctSeq;
	/**
	 * 是否联合贷
	 */
	private boolean isUnion;
	
	/**
	 * 交易总金额
	 */
	private BigDecimal totalAmt;
	
	/**
	 * 余额成分
	 */
	@NotEmpty
	private List<SubAcctData> subAcctData;
	/**
	 * 业务字段
	 */
	private List<BizData> bizData;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getIouNo() {
		return iouNo;
	}
	public void setIouNo(String iouNo) {
		this.iouNo = iouNo;
	}
	public Integer getAcctSeq() {
		return acctSeq;
	}
	public void setAcctSeq(Integer acctSeq) {
		this.acctSeq = acctSeq;
	}
	public List<SubAcctData> getSubAcctData() {
		return subAcctData;
	}
	public void setSubAcctData(List<SubAcctData> subAcctData) {
		this.subAcctData = subAcctData;
	}
	public List<BizData> getBizData() {
		return bizData;
	}
	public void setBizData(List<BizData> bizData) {
		this.bizData = bizData;
	}
	public Boolean getIsUnion() {
		return isUnion;
	}
	public void setIsUnion(Boolean isUnion) {
		this.isUnion = isUnion;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public void setUnion(boolean isUnion) {
		this.isUnion = isUnion;
	}
	
}
