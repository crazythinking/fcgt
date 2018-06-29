package net.engining.gateway.fts.invoker.check;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import net.engining.control.api.key.TransIdKey;
import net.engining.control.core.flow.FlowContext;
import net.engining.control.core.invoker.Invoker;
import net.engining.control.core.invoker.InvokerDefinition;
import net.engining.gateway.entity.enums.TransStatusDef;
import net.engining.gateway.entity.model.CtInboundJournal;
import net.engining.gateway.fts.sao.sccc.AccountingSao;

@InvokerDefinition(
		name = "系统状态检查", 
		requires = {
		},
		optional = {
				
		}, 
		results = {
				
		}
		)
public class CheckSystemStatusType implements Invoker {
	
	private static final Logger log = LoggerFactory.getLogger(CheckSystemStatusType.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private AccountingSao accountingSao;

	@Override
	public void invoke(FlowContext ctx) {
		log.info("开始查询系统当前状态====>");
		String sysStatusType = accountingSao.checkStatus();
		
		//如果为非批量状态，将值设置为不可跳过，继续进行下面的操作
		if (sysStatusType.equals("N") ) {
		    log.info("当前系统正处于非批量状态，开始于{}，执行联机交易", new Date());
			ctx.getParameters().put(FlowContext.CONS_PARAMETERS.SKIP.toString(), "No");
		} else if (sysStatusType.equals("B")) {
			//如果是批量状态，设置要跳过的值，并将交易流水表的交易状态设置为批量处理中
		    log.warn("当前系统正处于批量状态中，不可执行联机交易");
			String transId= ctx.get(TransIdKey.class);
			CtInboundJournal ctInboundJournal = em.find(CtInboundJournal.class, transId);
			ctInboundJournal.setTransStatus(TransStatusDef.D);
			ctx.getParameters().put(FlowContext.CONS_PARAMETERS.SKIP.toString(), "Yes");
		} else {
			log.error("查询系统状态失败，请稍后再试！");
			ctx.getParameters().put(FlowContext.CONS_PARAMETERS.SKIP.toString(), "Yes");
		}
	}
}
