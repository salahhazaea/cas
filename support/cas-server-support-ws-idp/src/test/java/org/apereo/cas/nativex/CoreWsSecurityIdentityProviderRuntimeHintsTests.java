package org.apereo.cas.nativex;

import org.apereo.cas.ws.idp.services.WSFederationRegisteredService;
import lombok.val;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.predicate.RuntimeHintsPredicates;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is {@link CoreWsSecurityIdentityProviderRuntimeHintsTests}.
 *
 * @author Misagh Moayyed
 * @since 7.0.0
 */
@Tag("Native")
public class CoreWsSecurityIdentityProviderRuntimeHintsTests {
    @Test
    public void verifyHints() {
        val hints = new RuntimeHints();
        new CoreWsSecurityIdentityProviderRuntimeHints().registerHints(hints, getClass().getClassLoader());
        assertTrue(RuntimeHintsPredicates.reflection().onType(WSFederationRegisteredService.class).test(hints));
    }
}
