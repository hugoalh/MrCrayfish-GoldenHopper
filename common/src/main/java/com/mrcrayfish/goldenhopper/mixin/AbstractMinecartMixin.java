package com.mrcrayfish.goldenhopper.mixin;

import com.mrcrayfish.goldenhopper.core.ModItems;
import com.mrcrayfish.goldenhopper.entity.vehicle.GoldenHopperMinecart;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Author: MrCrayfish
 */
@Mixin(AbstractMinecart.class)
public class AbstractMinecartMixin
{
    @Inject(method = "createMinecart", at = @At(value = "HEAD"), cancellable = true)
    private static void goldenHopper$CreateMinecart(ServerLevel level, double x, double y, double z, AbstractMinecart.Type type, ItemStack stack, Player player, CallbackInfoReturnable<AbstractMinecart> cir)
    {
        if(stack.getItem() == ModItems.GOLDEN_HOPPER_MINECART.get())
        {
            AbstractMinecart minecart = new GoldenHopperMinecart(level, x, y, z);
            EntityType.createDefaultStackConfig(level, stack, player).accept(minecart);
            cir.setReturnValue(minecart);
        }
    }
}
