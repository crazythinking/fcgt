package net.engining.gateway.fts.flow.sccc;

import org.springframework.stereotype.Component;

import net.engining.control.core.flow.AbstractFlow;
import net.engining.control.core.flow.FlowDefinition;
import net.engining.control.core.invoker.DetermineFinalResult;
import net.engining.control.core.invoker.TransactionSeperator;
import net.engining.gateway.fts.invoker.check.CheckSystemStatusType;
import net.engining.gateway.fts.invoker.comm.WriteInboundJournal;
import net.engining.gateway.fts.invoker.comm.WriteJournalUpdateResult;
import net.engining.gateway.fts.invoker.sccc.LoanAccountBizCheck;
import net.engining.gateway.fts.invoker.sccc.LoanAccountPostTxn;

@FlowDefinition(
		code = "T003LoanAccount",
		name = "贷款发放记账交易",
		desc = "贷款发放记账交易",
		invokers = {
				WriteInboundJournal.class,
				TransactionSeperator.class,
				//轮询查询是否是批量状态
				CheckSystemStatusType.class,
				LoanAccountBizCheck.class,
				LoanAccountPostTxn.class,
				TransactionSeperator.class,
				DetermineFinalResult.class,
				WriteJournalUpdateResult.class
		},
		response = {
				
		}
	)
@Component
public class T003LoanAccount extends AbstractFlow{

}
