package lk.pixcapsoft.smartchest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class JunkDeleterMod implements ModInitializer {
    public static final String MOD_ID = "junkdeleter";
    
    public static final Block JUNK_DELETER_CHEST = new JunkDeleterChestBlock();
    public static final BlockEntityType<JunkDeleterChestBlockEntity> JUNK_DELETER_CHEST_ENTITY;
    public static final ScreenHandlerType<JunkDeleterChestScreenHandler> JUNK_DELETER_SCREEN_HANDLER;
    
    static {
        JUNK_DELETER_CHEST_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(MOD_ID, "junk_deleter_chest"),
            FabricBlockEntityTypeBuilder.create(
                JunkDeleterChestBlockEntity::new,
                JUNK_DELETER_CHEST
            ).build()
        );
        
        JUNK_DELETER_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER,
            new Identifier(MOD_ID, "junk_deleter_chest"),
            new ExtendedScreenHandlerType<>(JunkDeleterChestScreenHandler::new)
        );
    }
    
    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "junk_deleter_chest"), JUNK_DELETER_CHEST);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "junk_deleter_chest"), 
            new BlockItem(JUNK_DELETER_CHEST, new Item.Settings()));
        
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.add(JUNK_DELETER_CHEST);
        });
        
        JunkDeleterRecipes.register();
    }
}