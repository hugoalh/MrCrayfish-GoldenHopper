package com.mrcrayfish.goldenhopper.platform;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;

/**
 * Author: MrCrayfish
 */
public class NeoForgePlatformHelper implements IPlatformHelper
{
    @Override
    public TagKey<Item> getGoldIngotsTag()
    {
        return Tags.Items.INGOTS_GOLD;
    }
}
