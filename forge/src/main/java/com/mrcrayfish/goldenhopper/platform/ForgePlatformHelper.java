package com.mrcrayfish.goldenhopper.platform;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

/**
 * Author: MrCrayfish
 */
public class ForgePlatformHelper implements IPlatformHelper
{
    @Override
    public TagKey<Item> getGoldIngotsTag()
    {
        return Tags.Items.INGOTS_GOLD;
    }
}
