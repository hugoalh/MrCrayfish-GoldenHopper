package com.mrcrayfish.goldenhopper.inventory.slot;

import com.mojang.datafixers.util.Pair;
import com.mrcrayfish.goldenhopper.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

/**
 * Author: MrCrayfish
 */
public class FilterSlot extends Slot
{
    private static final ResourceLocation EMPTY_SLOT_FILTER = new ResourceLocation(Constants.MOD_ID, "item/empty_slot_filter");

    public FilterSlot(Container container, int index, int x, int y)
    {
        super(container, index, x, y);
    }

    @Override
    public int getMaxStackSize()
    {
        return 1;
    }

    @Nullable
    @Override
    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon()
    {
        return Pair.of(InventoryMenu.BLOCK_ATLAS, EMPTY_SLOT_FILTER);
    }
}
