package net.engining.gateway.fts.sao.sccc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.engining.gateway.fts.sao.sccc.impl.AccountingSaoImpl;
import net.engining.gateway.fts.sdk.bean.EveryDayAccountingBean;
import net.engining.gateway.fts.sdk.bean.resultBack.ResponseData;

/**
 * 远程调用微服务
 * @author luxue
 *
 */
// Hystrix控制熔断，当调用远程服务不可用达到阀值时（默认5秒20次），调用本地实现类的重写方法
@FeignClient(value = "sccc-accounting-sv", fallback = AccountingSaoImpl.class)
public interface AccountingSao {

	/**
	 * 查询系统状态
	 * @return
	 */
	@RequestMapping(value = "/accounting/checkStatus", method = RequestMethod.POST)
	String checkStatus();
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 费用收取业务数据检查
	 */
	@RequestMapping(value = "/accounting/costAccount", method = RequestMethod.POST)
	String costAccount(@RequestBody EveryDayAccountingBean bean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 费用收取记账
	 */
	@RequestMapping(value = "/accounting/costAccountTxn", method = RequestMethod.POST)
	ResponseData costAccountTxn(@RequestBody EveryDayAccountingBean costAccountBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 贷款发放业务数据检查
	 */
	@RequestMapping(value = "/accounting/loanAccount", method = RequestMethod.POST)
	String loanAccount(@RequestBody EveryDayAccountingBean bean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 贷款发放
	 */
	@RequestMapping(value = "/accounting/loanAccountTxn", method = RequestMethod.POST)
	ResponseData loanAccountTxn(@RequestBody EveryDayAccountingBean loanAccountBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 额度业务数据检查
	 */
	@RequestMapping(value = "/accounting/accountLimit", method = RequestMethod.POST)
	String accountLimit(@RequestBody EveryDayAccountingBean accountLimitBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 额度
	 */
	@RequestMapping(value = "/accounting/accountLimitTxn", method = RequestMethod.POST)
	ResponseData accountLimitTxn(@RequestBody EveryDayAccountingBean accountLimitBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 退款
	 */
	@RequestMapping(value = "/accounting/refundsAccount", method = RequestMethod.POST)
	String refundsAccount(@RequestBody EveryDayAccountingBean refundsAccountingBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 退款
	 */
	@RequestMapping(value = "/accounting/refundsAccountTxn", method = RequestMethod.POST)
	ResponseData refundsAccountTxn(@RequestBody EveryDayAccountingBean refundsAccountingBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 还款数据校验
	 */
	@RequestMapping(value = "/accounting/repaymentAccount", method = RequestMethod.POST)
	String repaymentAccount(@RequestBody EveryDayAccountingBean repaymentAccountingBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 联机交易数据入账
	 */
	@RequestMapping(value = "/accounting/repaymentAccountTxn", method = RequestMethod.POST)
	ResponseData repaymentAccountTxn(@RequestBody EveryDayAccountingBean repaymentAccountingBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 冲正还款数据校验
	 */
	@RequestMapping(value = "/accounting/correctionRepayment", method = RequestMethod.POST)
	String correctionRepayment(@RequestBody EveryDayAccountingBean correctionRepaymentBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 冲正还款交易数据入账
	 */
	@RequestMapping(value = "/accounting/correctionRepaymentTxn", method = RequestMethod.POST)
	ResponseData correctionRepaymentTxn(@RequestBody EveryDayAccountingBean correctionRepaymentBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 冲正利息计提数据校验
	 */
	@RequestMapping(value = "/accounting/correctionInteAcc", method = RequestMethod.POST)
	String correctionInteAcc(@RequestBody EveryDayAccountingBean correctionRepaymentBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 冲正利息计提交易数据入账
	 */
	@RequestMapping(value = "/accounting/correctionInteAccTxn", method = RequestMethod.POST)
	ResponseData correctionInteAccTxn(@RequestBody EveryDayAccountingBean correctionRepaymentBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 冲正罚息计提数据校验
	 */
	@RequestMapping(value = "/accounting/correctionPnitAcc", method = RequestMethod.POST)
	String correctionPnitAcc(@RequestBody EveryDayAccountingBean correctionRepaymentBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 冲正罚息计提交易数据入账
	 */
	@RequestMapping(value = "/accounting/correctionPnitAccTxn", method = RequestMethod.POST)
	ResponseData correctionPnitAccTxn(@RequestBody EveryDayAccountingBean correctionRepaymentBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 冲正余额结转数据校验
	 */
	@RequestMapping(value = "/accounting/correctionTransfo", method = RequestMethod.POST)
	String correctionTransfo(@RequestBody EveryDayAccountingBean correctionRepaymentBean);
	
	/**
	 * 调用微服务sccc-accounting/queryOuterLedgerTxnHst
	 * @return
	 * 冲正余额结转交易数据入账
	 */
	@RequestMapping(value = "/accounting/correctionTransfoTxn", method = RequestMethod.POST)
	ResponseData correctionTransfoTxn(@RequestBody EveryDayAccountingBean correctionRepaymentBean);
}
