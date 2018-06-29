package net.engining.gateway.fts.invoker.comm;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.engining.control.api.key.ChannelKey;
import net.engining.control.api.key.ChannelRequestSeqKey;
import net.engining.control.api.key.ChannelSignTokenKey;
import net.engining.control.api.key.SvPrIdKey;
import net.engining.control.api.key.TargetBizDateKey;
import net.engining.control.api.key.TransIdKey;
import net.engining.control.api.key.TxnDateTimeKey;
import net.engining.control.core.flow.FlowContext;
import net.engining.control.core.invoker.Invoker;
import net.engining.control.core.invoker.InvokerDefinition;
import net.engining.control.core.invoker.Skippable;
import net.engining.gateway.entity.enums.TransStatusDef;
import net.engining.gateway.entity.model.CtInboundJournal;
import net.engining.gateway.fts.sdk.key.AsynIndKey;
import net.engining.gateway.fts.sdk.key.OnlineDataKey;

@InvokerDefinition(
	name = "记录网关接收的交易流水",
	requires = {
			SvPrIdKey.class,
			ChannelKey.class,
			OnlineDataKey.class,
			ChannelRequestSeqKey.class,
			AsynIndKey.class
	},
	optional = {
			ChannelSignTokenKey.class,
			TargetBizDateKey.class,
			TxnDateTimeKey.class
	},
	results = {
		TransIdKey.class
	}
)
public class WriteInboundJournal implements Invoker, Skippable {
	@PersistenceContext 
	private EntityManager em;
	
	@Override
	public void invoke(FlowContext ctx) {
		//TODO 插入交易流水表	
		CtInboundJournal ctInboundJournal = new CtInboundJournal();
		ctInboundJournal.setSvPrId(ctx.get(SvPrIdKey.class));
		ctInboundJournal.setTxnSerialNo(ctx.get(ChannelRequestSeqKey.class));
		ctInboundJournal.setChannelId(ctx.get(ChannelKey.class));
		ctInboundJournal.setTgBizDate(ctx.get(TargetBizDateKey.class));
		ctInboundJournal.setTxnDatetime(ctx.get(TxnDateTimeKey.class));
		ctInboundJournal.setAsynInd(ctx.get(AsynIndKey.class));
		ctInboundJournal.setTransStatus(TransStatusDef.P);
		ctInboundJournal.setRequestMsg(ctx.get(OnlineDataKey.class));
		ctInboundJournal.fillDefaultValues();
		em.persist(ctInboundJournal);
		
		ctx.put(TransIdKey.class, ctInboundJournal.getInboundId());
		ctx.put(OnlineDataKey.class, ctInboundJournal.getRequestMsg());
	}

	@Override
	public boolean skippable(Map<String, String> parameters) {
		String skip = parameters.get(FlowContext.CONS_PARAMETERS.SKIP.toString());
		if("Yes".equals(skip)){
			return true;
		}
		return false;
	}

}
