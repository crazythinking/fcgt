package net.engining.gateway.fts.hack;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class OrganizationInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
//		OrganizationContextHolder.setCurrentOrganizationId("*");
		return invocation.proceed();
	}

}
