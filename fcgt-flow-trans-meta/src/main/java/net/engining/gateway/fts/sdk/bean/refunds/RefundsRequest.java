package net.engining.gateway.fts.sdk.bean.refunds;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;

import com.google.common.collect.Maps;

import net.engining.pg.web.AsynInd;

/**
 * FIXME 重构，定义header
 * 通用的Web Request
 * 
 * @author luxue
 *
 */
public class RefundsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 服务提供系统标识
	 */
	@NotBlank
	private String svPrId;

	/**
	 * 渠道Id（请求系统标识）
	 */
	@NotBlank
	private String channelId;

	/**
	 * 渠道签名
	 */
	private String channelSign;

	/**
	 * 清算日期（业务日期）
	 */
	private Date clearingDate;

	/**
	 * 请求交易流水号
	 */
	@NotBlank
	private String txnSerialNo;

	/**
	 * 交易请求时间
	 */
	private Date timestamp;

	/**
	 * 异步接口标识
	 */
	@NotBlank
	private AsynInd asynInd;

	/**
	 * 请求业务数据
	 */
	@NotNull
	private RefundsRequestData requestData;

	/**
	 * 其他附加信息
	 */
	private Map<String, Serializable> additionalReqMap;

	/**
	 * @return the svPrId
	 */
	public String getSvPrId() {
		return svPrId;
	}

	/**
	 * @param svPrId
	 *            the svPrId to set
	 */
	public RefundsRequest setSvPrId(String svPrId) {
		this.svPrId = svPrId;
		return this;
	}


	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId
	 *            the channelId to set
	 */
	public RefundsRequest setChannelId(String channelId) {
		this.channelId = channelId;
		return this;
	}

	/**
	 * @return the channelSign
	 */
	public String getChannelSign() {
		return channelSign;
	}

	/**
	 * @param channelSign
	 *            the channelSign to set
	 */
	public RefundsRequest setChannelSign(String channelSign) {
		this.channelSign = channelSign;
		return this;
	}

	/**
	 * @return the clearingDate
	 */
	public Date getClearingDate() {
		return clearingDate;
	}

	/**
	 * @param clearingDate
	 *            the clearingDate to set
	 */
	public RefundsRequest setClearingDate(Date clearingDate) {
		this.clearingDate = clearingDate;
		return this;
	}

	/**
	 * @return the txnSerialNo
	 */
	public String getTxnSerialNo() {
		return txnSerialNo;
	}

	/**
	 * @param txnSerialNo
	 *            the txnSerialNo to set
	 */
	public RefundsRequest setTxnSerialNo(String txnSerialNo) {
		this.txnSerialNo = txnSerialNo;
		return this;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public RefundsRequest setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	/**
	 * @return the asynInd
	 */
	public AsynInd getAsynInd() {
		return asynInd;
	}

	/**
	 * @param asynInd
	 *            the asynInd to set
	 */
	public RefundsRequest setAsynInd(AsynInd asynInd) {
		this.asynInd = asynInd;
		return this;
	}

	/**
	 * @return the requestData
	 */
	public RefundsRequestData getRequestData() {
		return requestData;
	}

	/**
	 * @param requestData
	 *            the requestData to set
	 */
	public RefundsRequest setRequestData(RefundsRequestData requestData) {
		this.requestData = requestData;
		return this;
	}

	public Map<String, Serializable> getAdditionalReqMap() {
		return additionalReqMap;
	}

	public void setAdditionalReqMap(Map<String, Serializable> additionalReqMap) {
		this.additionalReqMap = additionalReqMap;
	}

}
