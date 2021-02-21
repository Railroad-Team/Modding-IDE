package io.github.railroad.mods;

import java.util.function.Supplier;

/**
 * Modding platform types.
 * <p>
 * If we need more stuff here in the future, we can support it. for now, we're just returning the name.
 *
 * @author ChAoS, TheOnlyTails
 */
public interface PlatformType extends Supplier<String> {
    PlatformType FORGE = () -> "forge";
    PlatformType FABRIC = () -> "forge";
}
