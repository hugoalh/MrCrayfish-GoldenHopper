package com.mrcrayfish.goldenhopper.platform;

import com.mrcrayfish.goldenhopper.Constants;

import java.util.ServiceLoader;

/**
 * Author: MrCrayfish
 */
public class Services
{
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz)
    {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}