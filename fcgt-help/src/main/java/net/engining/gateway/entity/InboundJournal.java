package net.engining.gateway.entity;

import java.util.Date;

/**
 * InboundJournal Entity
 */
public class InboundJournal extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String creditApplyId;
	private String channelId;
	private String requestUuid;
	private String prodId;
	private Date applyDate;
	private String applyType;
	private String applyPriority;
	private Date transDate;
	private String transStatus;
	private String transResult;
	private String requestMessage;
	private String responseMessage;
	private Date requestTime;
	private Date responseTime;
	private String responseStatus;
	private String responseDesc;
	private String msgId;
	private Date inqueueTime;
	private Date outqueueTime;
	private int retryCount;
	private Date nowDate;
	private Date beginDate1;
	private Date endDate1;
	private Date beginDate2;
	private Date endDate2;
	private int dayTotalSum;
	private int monthTotalSum;
	private int yearTotalSum;
	private int daySum;
	private int monthSum;
	private int yearSum;

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public Date getBeginDate1() {
		return beginDate1;
	}

	public void setBeginDate1(Date beginDate1) {
		this.beginDate1 = beginDate1;
	}

	public Date getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}

	public Date getBeginDate2() {
		return beginDate2;
	}

	public void setBeginDate2(Date beginDate2) {
		this.beginDate2 = beginDate2;
	}

	public Date getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}

	public int getDayTotalSum() {
		return dayTotalSum;
	}

	public void setDayTotalSum(int dayTotalSum) {
		this.dayTotalSum = dayTotalSum;
	}

	public int getMonthTotalSum() {
		return monthTotalSum;
	}

	public void setMonthTotalSum(int monthTotalSum) {
		this.monthTotalSum = monthTotalSum;
	}

	public int getYearTotalSum() {
		return yearTotalSum;
	}

	public void setYearTotalSum(int yearTotalSum) {
		this.yearTotalSum = yearTotalSum;
	}

	public int getDaySum() {
		return daySum;
	}

	public void setDaySum(int daySum) {
		this.daySum = daySum;
	}

	public int getMonthSum() {
		return monthSum;
	}

	public void setMonthSum(int monthSum) {
		this.monthSum = monthSum;
	}

	public int getYearSum() {
		return yearSum;
	}

	public void setYearSum(int yearSum) {
		this.yearSum = yearSum;
	}

	public InboundJournal() {
		super();
	}

	/**
	 * <p>
	 * CREDIT_APPLY_ID
	 * </p>
	 * <p>
	 * 信审申请号
	 * </p>
	 */
	public String getCreditApplyId() {
		return creditApplyId;
	}

	/**
	 * <p>
	 * CREDIT_APPLY_ID
	 * </p>
	 * <p>
	 * 信审申请号
	 * </p>
	 */
	public void setCreditApplyId(String creditApplyId) {
		this.creditApplyId = creditApplyId;
	}

	/**
	 * <p>
	 * CHANNEL_ID
	 * </p>
	 * <p>
	 * 渠道号
	 * </p>
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * <p>
	 * CHANNEL_ID
	 * </p>
	 * <p>
	 * 渠道号
	 * </p>
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * <p>
	 * REQUEST_UUID
	 * </p>
	 * <p>
	 * 交易渠道请求流水号
	 * </p>
	 */
	public String getRequestUuid() {
		return requestUuid;
	}

	/**
	 * <p>
	 * REQUEST_UUID
	 * </p>
	 * <p>
	 * 交易渠道请求流水号
	 * </p>
	 */
	public void setRequestUuid(String requestUuid) {
		this.requestUuid = requestUuid;
	}

	/**
	 * <p>
	 * PROD_ID
	 * </p>
	 * <p>
	 * 产品代码
	 * </p>
	 */
	public String getProdId() {
		return prodId;
	}

	/**
	 * <p>
	 * PROD_ID
	 * </p>
	 * <p>
	 * 产品代码
	 * </p>
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	/**
	 * <p>
	 * APPLY_DATE
	 * </p>
	 * <p>
	 * 申请日期
	 * </p>
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * <p>
	 * APPLY_DATE
	 * </p>
	 * <p>
	 * 申请日期
	 * </p>
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * <p>
	 * APPLY_TYPE
	 * </p>
	 * <p>
	 * 申请类型
	 * </p>
	 */
	public String getApplyType() {
		return applyType;
	}

	/**
	 * <p>
	 * APPLY_TYPE
	 * </p>
	 * <p>
	 * 申请类型
	 * </p>
	 */
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	/**
	 * <p>
	 * APPLY_PRIORITY
	 * </p>
	 * <p>
	 * 申请优先级
	 * </p>
	 */
	public String getApplyPriority() {
		return applyPriority;
	}

	/**
	 * <p>
	 * APPLY_PRIORITY
	 * </p>
	 * <p>
	 * 申请优先级
	 * </p>
	 */
	public void setApplyPriority(String applyPriority) {
		this.applyPriority = applyPriority;
	}

	/**
	 * <p>
	 * TRANS_DATE
	 * </p>
	 * <p>
	 * 交易日期
	 * </p>
	 */
	public Date getTransDate() {
		return transDate;
	}

	/**
	 * <p>
	 * TRANS_DATE
	 * </p>
	 * <p>
	 * 交易日期
	 * </p>
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	/**
	 * <p>
	 * TRANS_STATUS
	 * </p>
	 * <p>
	 * 交易状态
	 * </p>
	 */
	public String getTransStatus() {
		return transStatus;
	}

	/**
	 * <p>
	 * TRANS_STATUS
	 * </p>
	 * <p>
	 * 交易状态
	 * </p>
	 */
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	/**
	 * <p>
	 * TRANS_RESULT
	 * </p>
	 * <p>
	 * 交易状态描述
	 * </p>
	 */
	public String getTransResult() {
		return transResult;
	}

	/**
	 * <p>
	 * TRANS_RESULT
	 * </p>
	 * <p>
	 * 交易状态描述
	 * </p>
	 */
	public void setTransResult(String transResult) {
		this.transResult = transResult;
	}

	/**
	 * <p>
	 * REQUEST_MESSAGE
	 * </p>
	 * <p>
	 * 请求报文
	 * </p>
	 */
	public String getRequestMessage() {
		return requestMessage;
	}

	/**
	 * <p>
	 * REQUEST_MESSAGE
	 * </p>
	 * <p>
	 * 请求报文
	 * </p>
	 */
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	/**
	 * <p>
	 * RESPONSE_MESSAGE
	 * </p>
	 * <p>
	 * 响应报文
	 * </p>
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * <p>
	 * RESPONSE_MESSAGE
	 * </p>
	 * <p>
	 * 响应报文
	 * </p>
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * <p>
	 * REQUEST_TIME
	 * </p>
	 * <p>
	 * 请求时间
	 * </p>
	 */
	public Date getRequestTime() {
		return requestTime;
	}

	/**
	 * <p>
	 * REQUEST_TIME
	 * </p>
	 * <p>
	 * 请求时间
	 * </p>
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * <p>
	 * RESPONSE_TIME
	 * </p>
	 * <p>
	 * 响应时间
	 * </p>
	 */
	public Date getResponseTime() {
		return responseTime;
	}

	/**
	 * <p>
	 * RESPONSE_TIME
	 * </p>
	 * <p>
	 * 响应时间
	 * </p>
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * <p>
	 * RESPONSE_STATUS
	 * </p>
	 * <p>
	 * 响应码
	 * </p>
	 */
	public String getResponseStatus() {
		return responseStatus;
	}

	/**
	 * <p>
	 * RESPONSE_STATUS
	 * </p>
	 * <p>
	 * 响应码
	 * </p>
	 */
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	/**
	 * <p>
	 * RESPONSE_DESC
	 * </p>
	 * <p>
	 * 响应描述
	 * </p>
	 */
	public String getResponseDesc() {
		return responseDesc;
	}

	/**
	 * <p>
	 * RESPONSE_DESC
	 * </p>
	 * <p>
	 * 响应描述
	 * </p>
	 */
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	/**
	 * <p>
	 * MSG_ID
	 * </p>
	 * <p>
	 * 消息ID号
	 * </p>
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * <p>
	 * MSG_ID
	 * </p>
	 * <p>
	 * 消息ID号
	 * </p>
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * <p>
	 * INQUEUE_TIME
	 * </p>
	 * <p>
	 * 入队时间
	 * </p>
	 */
	public Date getInqueueTime() {
		return inqueueTime;
	}

	/**
	 * <p>
	 * INQUEUE_TIME
	 * </p>
	 * <p>
	 * 入队时间
	 * </p>
	 */
	public void setInqueueTime(Date inqueueTime) {
		this.inqueueTime = inqueueTime;
	}

	/**
	 * <p>
	 * OUTQUEUE_TIME
	 * </p>
	 * <p>
	 * 出队时间
	 * </p>
	 */
	public Date getOutqueueTime() {
		return outqueueTime;
	}

	/**
	 * <p>
	 * OUTQUEUE_TIME
	 * </p>
	 * <p>
	 * 出队时间
	 * </p>
	 */
	public void setOutqueueTime(Date outqueueTime) {
		this.outqueueTime = outqueueTime;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

}
