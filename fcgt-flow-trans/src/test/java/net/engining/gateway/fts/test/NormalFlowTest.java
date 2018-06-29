package net.engining.gateway.fts.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;

import net.engining.control.api.ContextKey;
import net.engining.control.api.FlowDispatcher;
import net.engining.gateway.fts.test.key.IntValue1Key;
import net.engining.gateway.fts.test.key.IntValue2Key;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/flow00/test-context.xml")
public class NormalFlowTest
{
	@Autowired
	private FlowDispatcher dispatcher;
	
	@Test
	public void simple()
	{
		Map<Class<? extends ContextKey<?>>, Object> req = Maps.newHashMap();
		
		req.put(IntValue1Key.class, 4);
		req.put(IntValue2Key.class, 0);
		
		Map<Class<? extends ContextKey<?>>, Object> resp = dispatcher.process("sample", req);
		
		assertThat((Integer)resp.get(IntValue2Key.class), equalTo(4 * 4));
	}
}
