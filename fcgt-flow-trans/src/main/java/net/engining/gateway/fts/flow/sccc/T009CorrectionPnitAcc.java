package net.engining.gateway.fts.flow.sccc;

import org.springframework.stereotype.Component;

import net.engining.control.core.flow.AbstractFlow;
import net.engining.control.core.flow.FlowDefinition;
import net.engining.control.core.invoker.DetermineFinalResult;
import net.engining.control.core.invoker.TransactionSeperator;
import net.engining.gateway.fts.invoker.check.CheckSystemStatusType;
import net.engining.gateway.fts.invoker.comm.WriteInboundJournal;
import net.engining.gateway.fts.invoker.comm.WriteJournalUpdateResult;
import net.engining.gateway.fts.invoker.sccc.CorrectionPnitAccBizCheck;
import net.engining.gateway.fts.invoker.sccc.CorrectionPnitAccPostTxn;

@FlowDefinition(
		code = "T009CorrectionPnitAcc",
		name = "冲正（新网银行）罚息计提",
		desc = "冲正（新网银行）罚息计提",
		invokers = {
				WriteInboundJournal.class,
				TransactionSeperator.class,
				//轮询查询是否是批量状态
				CheckSystemStatusType.class,
				CorrectionPnitAccBizCheck.class,
				CorrectionPnitAccPostTxn.class,
				TransactionSeperator.class,
				DetermineFinalResult.class,
				WriteJournalUpdateResult.class
		},
		response = {
				
		}
	)
@Component
public class T009CorrectionPnitAcc extends AbstractFlow {

}
