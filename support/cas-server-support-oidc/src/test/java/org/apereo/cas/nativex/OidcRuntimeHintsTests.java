package org.apereo.cas.nativex;

import org.apereo.cas.oidc.token.OidcJwtAccessTokenCipherExecutor;
import org.apereo.cas.services.OidcRegisteredService;
import lombok.val;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.predicate.RuntimeHintsPredicates;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This is {@link OidcRuntimeHintsTests}.
 *
 * @author Misagh Moayyed
 * @since 7.0.0
 */
@Tag("Native")
public class OidcRuntimeHintsTests {
    @Test
    public void verifyHints() {
        val hints = new RuntimeHints();
        new OidcRuntimeHints().registerHints(hints, getClass().getClassLoader());
        assertTrue(RuntimeHintsPredicates.reflection().onType(OidcRegisteredService.class).test(hints));
        assertTrue(RuntimeHintsPredicates.reflection().onType(OidcJwtAccessTokenCipherExecutor.class).test(hints));
    }
}
