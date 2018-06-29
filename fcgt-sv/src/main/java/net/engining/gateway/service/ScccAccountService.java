/**
 * 
 */
package net.engining.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.engining.control.sdk.FlowTransProcessorTemplate;
import net.engining.gateway.fts.flow.sdk.T001UpDownLimitRequest;
import net.engining.gateway.fts.flow.sdk.T001UpDownLimitResponse;

/**
 * 核算同步Online交易
 * 
 * @author luxue
 *
 */
@Service
public class ScccAccountService {

	private static final Logger log = LoggerFactory.getLogger(ScccAccountService.class);

	@Autowired
	FlowTransProcessorTemplate flowTransProcessorTemplate;
	
	public void doAsynUpDownLimetAmount() {
		// TODO 从msg设置request
		T001UpDownLimitRequest t001UpDownLimitRequest = new T001UpDownLimitRequest();
		// 调用相应授额|提降额记账交易
		flowTransProcessorTemplate.process(t001UpDownLimitRequest, T001UpDownLimitResponse.class);
	}
}
