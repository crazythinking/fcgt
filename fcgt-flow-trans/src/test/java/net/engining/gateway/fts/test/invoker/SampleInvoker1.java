package net.engining.gateway.fts.test.invoker;

import net.engining.control.core.flow.FlowContext;
import net.engining.control.core.invoker.Invoker;
import net.engining.control.core.invoker.InvokerDefinition;
import net.engining.gateway.fts.test.key.IntValue1Key;
import net.engining.gateway.fts.test.key.IntValue2Key;

/**
 * 把 {@link IntValue1Key} 作平方后写入 {@link IntValue2Key}
 * @author binarier
 *
 */
@InvokerDefinition(
	name = "square invoker1",
	requires = IntValue1Key.class,
	results = IntValue2Key.class
)
public class SampleInvoker1 implements Invoker
{
	@Override
	public void invoke(FlowContext ctx)
	{
		int value = ctx.get(IntValue1Key.class);
		value = value * value;
		
		ctx.put(IntValue2Key.class, value);
		
		ctx.getParameters().put("skip", "true");
	}

}
