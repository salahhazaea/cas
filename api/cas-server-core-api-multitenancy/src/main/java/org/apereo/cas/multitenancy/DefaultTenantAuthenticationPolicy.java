package org.apereo.cas.multitenancy;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.util.List;

/**
 * This is {@link DefaultTenantAuthenticationPolicy}.
 *
 * @author Misagh Moayyed
 * @since 7.2.0
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DefaultTenantAuthenticationPolicy implements TenantAuthenticationPolicy {
    @Serial
    private static final long serialVersionUID = -9012299259747093234L;

    private List<String> authenticationHandlers;

    private List<String> allowedProviders;
}
