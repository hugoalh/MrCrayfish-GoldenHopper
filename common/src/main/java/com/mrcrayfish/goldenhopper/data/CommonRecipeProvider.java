package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.goldenhopper.core.ModBlocks;
import com.mrcrayfish.goldenhopper.core.ModItems;
import com.mrcrayfish.goldenhopper.platform.Services;
import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.function.Function;

/**
 * Author: MrCrayfish
 */
public class CommonRecipeProvider
{
    private final RecipeOutput output;
    private final Function<ItemLike, Criterion<?>> hasItem;
    private final Function<TagKey<Item>, Criterion<?>> hasTag;

    public CommonRecipeProvider(RecipeOutput output, Function<ItemLike, Criterion<?>> hasItem, Function<TagKey<Item>, Criterion<?>> hasTag)
    {
        this.output = output;
        this.hasItem = hasItem;
        this.hasTag = hasTag;
    }

    public void run()
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.GOLDEN_HOPPER.get())
            .pattern("ICI")
            .pattern("IHI")
            .pattern("RIR")
            .define('I', Services.PLATFORM.getGoldIngotsTag())
            .define('R', Items.REDSTONE)
            .define('H', Items.HOPPER)
            .define('C', Items.COMPARATOR)
            .unlockedBy("has_gold_ingot", this.hasTag.apply(Services.PLATFORM.getGoldIngotsTag()))
            .unlockedBy("has_redstone", this.hasItem.apply(Items.REDSTONE))
            .unlockedBy("has_comparator", this.hasItem.apply(Items.COMPARATOR))
            .unlockedBy("has_hopper", this.hasItem.apply(Items.HOPPER))
            .save(this.output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, ModItems.GOLDEN_HOPPER_MINECART.get())
            .pattern("A")
            .pattern("B")
            .define('A', ModBlocks.GOLDEN_HOPPER.get())
            .define('B', Items.MINECART)
            .unlockedBy("has_gold_ingot", this.hasTag.apply(Services.PLATFORM.getGoldIngotsTag()))
            .unlockedBy("has_minecart", this.hasItem.apply(Items.MINECART))
            .save(this.output);
    }
}
