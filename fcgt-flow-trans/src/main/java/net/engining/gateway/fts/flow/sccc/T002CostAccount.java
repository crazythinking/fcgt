package net.engining.gateway.fts.flow.sccc;

import org.springframework.stereotype.Component;

import net.engining.control.core.flow.AbstractFlow;
import net.engining.control.core.flow.FlowDefinition;
import net.engining.control.core.invoker.DetermineFinalResult;
import net.engining.control.core.invoker.TransactionSeperator;
import net.engining.gateway.fts.invoker.check.CheckSystemStatusType;
import net.engining.gateway.fts.invoker.comm.WriteInboundJournal;
import net.engining.gateway.fts.invoker.comm.WriteJournalUpdateResult;
import net.engining.gateway.fts.invoker.sccc.CostAccountDataCheck;
import net.engining.gateway.fts.invoker.sccc.CostAccountPostTxn;

@FlowDefinition(
		code = "T002CostAccount",
		name = "费用收取记账交易",
		desc = "费用收取记账交易",
		invokers = {
				WriteInboundJournal.class,
				TransactionSeperator.class,
				//轮询查询是否是批量状态
				CheckSystemStatusType.class,
				CostAccountDataCheck.class,
				CostAccountPostTxn.class,
				TransactionSeperator.class,
				DetermineFinalResult.class,
				WriteJournalUpdateResult.class
		},
		response = {
				
		}
	)
@Component
public class T002CostAccount extends AbstractFlow{

}
