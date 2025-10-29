package lk.pixcapsoft.smartchest;

import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import java.util.Map;

public class JunkDeleterRecipes {
    public static void register() {
        // Create shaped recipe: chest surrounded by redstone dust
        RawShapedRecipe pattern = RawShapedRecipe.create(
            Map.of(
                'R', Ingredient.ofItems(Items.REDSTONE),
                'C', Ingredient.ofItems(Items.CHEST)
            ),
            "RRR",
            "RCR",
            "RRR"
        );
        
        ShapedRecipe recipe = new ShapedRecipe(
            "",
            RecipeCategory.MISC,
            pattern,
            new ItemStack(JunkDeleterMod.JUNK_DELETER_CHEST)
        );
        
        Registry.register(
            Registries.RECIPE_SERIALIZER,
            new Identifier(JunkDeleterMod.MOD_ID, "junk_deleter_chest_recipe"),
            recipe.getSerializer()
        );
    }
}