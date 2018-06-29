package net.engining.gateway.dispacher.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.engining.gateway.api.DemoSyncDispatcher;

@Service
public class DemoSyncDispatcherImpl implements DemoSyncDispatcher {
	private static final Logger logger = LoggerFactory.getLogger(DemoSyncDispatcherImpl.class);

	@Override
	public String onMessage(String req) {
		logger.info("mq sync sucess, req:" + req);
		return "mq sync res";
	}

}
