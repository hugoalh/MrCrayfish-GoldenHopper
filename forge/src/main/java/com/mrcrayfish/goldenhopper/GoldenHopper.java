package com.mrcrayfish.goldenhopper;

import com.mrcrayfish.goldenhopper.client.ClientHandler;
import com.mrcrayfish.goldenhopper.core.ModItems;
import com.mrcrayfish.goldenhopper.data.CommonBlockTagsProvider;
import com.mrcrayfish.goldenhopper.data.CommonItemTagsProvider;
import com.mrcrayfish.goldenhopper.data.CommonLootTableProvider;
import com.mrcrayfish.goldenhopper.data.CommonRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.CompletableFuture;

/**
 * Author: MrCrayfish
 */
@Mod(Constants.MOD_ID)
public class GoldenHopper
{
    public GoldenHopper(FMLJavaModLoadingContext context)
    {
        IEventBus bus = context.getModEventBus();
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onGatherData);
        bus.addListener(this::onCreativeTabBuilding);
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(ClientHandler::init);
    }

    private void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new CommonRecipeProvider.Runner(output, lookupProvider));
        generator.addProvider(event.includeServer(), new CommonLootTableProvider(output, lookupProvider));
        CommonBlockTagsProvider blockTagGen = generator.addProvider(event.includeServer(), new CommonBlockTagsProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), new CommonItemTagsProvider(output, lookupProvider, blockTagGen.contentsGetter()));
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
