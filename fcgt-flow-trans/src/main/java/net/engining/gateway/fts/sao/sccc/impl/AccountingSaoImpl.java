package net.engining.gateway.fts.sao.sccc.impl;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import net.engining.gateway.fts.sao.sccc.AccountingSao;
import net.engining.gateway.fts.sdk.bean.EveryDayAccountingBean;
import net.engining.gateway.fts.sdk.bean.resultBack.ResponseData;
import net.engining.pg.support.core.exception.ErrorCode;
/**
 * 调用微服务失败时的本地实现
 * 
 * @author luxue
 *
 */
@Component
public class AccountingSaoImpl implements AccountingSao {

	@Override
	public String costAccount(EveryDayAccountingBean costAccountBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData costAccountTxn(EveryDayAccountingBean costAccountBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String loanAccount(EveryDayAccountingBean loanAccountBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData loanAccountTxn(EveryDayAccountingBean loanAccountBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String accountLimit(EveryDayAccountingBean accountLimitBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData accountLimitTxn(EveryDayAccountingBean accountLimitBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String refundsAccount(EveryDayAccountingBean refundsAccountingBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData refundsAccountTxn(EveryDayAccountingBean refundsAccountingBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String repaymentAccount(EveryDayAccountingBean repaymentAccountingBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData repaymentAccountTxn(EveryDayAccountingBean repaymentAccountingBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String correctionRepayment(EveryDayAccountingBean correctionRepaymentBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData correctionRepaymentTxn(EveryDayAccountingBean correctionRepaymentBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String correctionTransfo(EveryDayAccountingBean correctionRepaymentBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData correctionTransfoTxn(EveryDayAccountingBean correctionRepaymentBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String correctionInteAcc(EveryDayAccountingBean correctionRepaymentBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData correctionInteAccTxn(EveryDayAccountingBean correctionRepaymentBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String correctionPnitAcc(EveryDayAccountingBean correctionRepaymentBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		String result = JSON.toJSONString(response);
		return result;
	}

	@Override
	public ResponseData correctionPnitAccTxn(EveryDayAccountingBean correctionRepaymentBean) {
		ResponseData response = new ResponseData();
		response.setReturnCode(ErrorCode.SystemError.toString());
		response.setReturnDesc("系统繁忙，请稍后再试");
		return response;
	}

	@Override
	public String checkStatus() {
		return "查询系统状态失败！";
	}

//	 @Override
//	 public WebCommonResponse<EveryDayAccountingBean> queryOuterLedgerTxnHst(EveryDayAccountingBean apGlTxn) {
//		 ResponseData response = new ResponseData();
//		 response.setReturnCode(ErrorCode.SystemError.toString());
//		 response.setReturnDesc("系统繁忙，请稍后再试");
//		 return new 
//		WebCommonResponseBuilder<EveryDayAccountingBean>().build().setStatusCode(WebCommonResponse.CODE_UNKNOW_FAIL).setStatusDesc(WebCommonResponse.DESC_UNKNOW_FAIL);
//	 }
	

}
