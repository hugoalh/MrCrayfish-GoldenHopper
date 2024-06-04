package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.goldenhopper.Constants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/**
 * Author: MrCrayfish
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration
{
    @SubscribeEvent
    private static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        generator.addProvider(event.includeServer(), new NeoForgeRecipeGen(output));
        generator.addProvider(event.includeServer(), new NeoForgeLootTableGen(output));
        generator.addProvider(event.includeServer(), new NeoForgeBlockTagGen(output, event.getLookupProvider(), event.getExistingFileHelper()));
    }
}
