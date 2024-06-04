package com.mrcrayfish.goldenhopper;

import com.mrcrayfish.goldenhopper.core.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

/**
 * Author: MrCrayfish
 */
@Mod(Constants.MOD_ID)
public class GoldenHopper
{
    public GoldenHopper(IEventBus bus)
    {
        bus.addListener(this::onCreativeTabBuilding);
    }

    private void onCreativeTabBuilding(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey().equals(CreativeModeTabs.REDSTONE_BLOCKS))
        {
            event.accept(ModItems.GOLDEN_HOPPER::get);
            event.accept(ModItems.GOLDEN_HOPPER_MINECART::get);
        }
    }
}
