package com.mrcrayfish.goldenhopper.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public class ForgeRecipeGen extends RecipeProvider
{
    public ForgeRecipeGen(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        new CommonRecipeProvider(output, RecipeProvider::has, RecipeProvider::has).run();
    }
}
