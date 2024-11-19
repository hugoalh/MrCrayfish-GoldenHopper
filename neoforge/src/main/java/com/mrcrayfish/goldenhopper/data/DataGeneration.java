package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.goldenhopper.Constants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

/**
 * Author: MrCrayfish
 */
@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneration
{
    @SubscribeEvent
    private static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new CommonRecipeProvider.Runner(output, lookupProvider));
        generator.addProvider(event.includeServer(), new CommonLootTableProvider(output, lookupProvider));
        CommonBlockTagsProvider blockTagGen = generator.addProvider(event.includeServer(), new CommonBlockTagsProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), new CommonItemTagsProvider(output, lookupProvider, blockTagGen.contentsGetter()));
    }
}
