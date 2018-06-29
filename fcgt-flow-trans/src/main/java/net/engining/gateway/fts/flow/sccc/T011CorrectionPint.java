package net.engining.gateway.fts.flow.sccc;

import org.springframework.stereotype.Component;

import net.engining.control.core.flow.AbstractFlow;
import net.engining.control.core.flow.FlowDefinition;
import net.engining.control.core.invoker.DetermineFinalResult;
import net.engining.control.core.invoker.TransactionSeperator;
import net.engining.gateway.fts.invoker.check.CheckSystemStatusType;
import net.engining.gateway.fts.invoker.comm.WriteInboundJournal;
import net.engining.gateway.fts.invoker.comm.WriteJournalUpdateResult;
import net.engining.gateway.fts.invoker.sccc.CorrectionPintBizCheck;
import net.engining.gateway.fts.invoker.sccc.CorrectionPintPostTxn;

@FlowDefinition(
		code = "T011CorrectionPint",
		name = "冲正罚息结转",
		desc = "冲正罚息结转",
		invokers = {
				WriteInboundJournal.class,
				TransactionSeperator.class,
				//轮询查询是否是批量状态
				CheckSystemStatusType.class,
				CorrectionPintBizCheck.class,
				CorrectionPintPostTxn.class,
				TransactionSeperator.class,
				DetermineFinalResult.class,
				WriteJournalUpdateResult.class
		},
		response = {
				
		}
	)
@Component
public class T011CorrectionPint extends AbstractFlow {

}
