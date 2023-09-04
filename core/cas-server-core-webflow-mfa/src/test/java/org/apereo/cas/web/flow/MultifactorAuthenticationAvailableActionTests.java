package org.apereo.cas.web.flow;

import org.apereo.cas.BaseCasWebflowMultifactorAuthenticationTests;
import org.apereo.cas.authentication.DefaultMultifactorAuthenticationFailureModeEvaluator;
import org.apereo.cas.authentication.mfa.TestMultifactorAuthenticationProvider;
import org.apereo.cas.configuration.model.support.mfa.BaseMultifactorAuthenticationProviderProperties.MultifactorAuthenticationProviderFailureModes;
import org.apereo.cas.services.RegisteredServiceTestUtils;
import org.apereo.cas.web.support.WebUtils;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.test.MockRequestContext;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is {@link MultifactorAuthenticationAvailableActionTests}.
 *
 * @author Misagh Moayyed
 * @since 6.2.0
 */
@Tag("WebflowMfaActions")
@Execution(ExecutionMode.SAME_THREAD)
class MultifactorAuthenticationAvailableActionTests {

    @Nested
    class DefaultTests extends BaseCasWebflowMultifactorAuthenticationTests {
        @Autowired
        @Qualifier(CasWebflowConstants.ACTION_ID_MFA_CHECK_AVAILABLE)
        private Action mfaAvailableAction;

        @Test
        void verifyOperations() throws Throwable {
            val context = new MockRequestContext();
            val request = new MockHttpServletRequest();
            val response = new MockHttpServletResponse();
            context.setExternalContext(new ServletExternalContext(new MockServletContext(), request, response));

            val service = RegisteredServiceTestUtils.getRegisteredService();
            servicesManager.save(service);
            WebUtils.putRegisteredService(context, service);

            WebUtils.putAuthentication(RegisteredServiceTestUtils.getAuthentication(), context);

            val provider = TestMultifactorAuthenticationProvider.registerProviderIntoApplicationContext(applicationContext);
            WebUtils.putMultifactorAuthenticationProvider(context, provider);

            val event = mfaAvailableAction.execute(context);
            assertEquals(CasWebflowConstants.TRANSITION_ID_YES, event.getId());
        }
    }

    @Nested
    class FailureModeNoneTests extends BaseCasWebflowMultifactorAuthenticationTests {
        @Autowired
        @Qualifier(CasWebflowConstants.ACTION_ID_MFA_CHECK_AVAILABLE)
        private Action mfaAvailableAction;

        @Test
        void verifyOperations() throws Throwable {
            val context = new MockRequestContext();
            val request = new MockHttpServletRequest();
            val response = new MockHttpServletResponse();
            context.setExternalContext(new ServletExternalContext(new MockServletContext(), request, response));

            val service = RegisteredServiceTestUtils.getRegisteredService();
            servicesManager.save(service);
            WebUtils.putRegisteredService(context, service);

            WebUtils.putAuthentication(RegisteredServiceTestUtils.getAuthentication(), context);

            val provider = TestMultifactorAuthenticationProvider.registerProviderIntoApplicationContext(applicationContext);
            provider.setAvailable(false);
            provider.setFailureMode(MultifactorAuthenticationProviderFailureModes.NONE);
            provider.setFailureModeEvaluator(new DefaultMultifactorAuthenticationFailureModeEvaluator(casProperties));
            WebUtils.putMultifactorAuthenticationProvider(context, provider);

            val event = mfaAvailableAction.execute(context);
            assertEquals(CasWebflowConstants.TRANSITION_ID_YES, event.getId());
        }
    }
}
