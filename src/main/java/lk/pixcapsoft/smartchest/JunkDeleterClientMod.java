package lk.pixcapsoft.smartchest.client;

import lk.pixcapsoft.smartchest.JunkDeleterMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenHandler;

@Environment(EnvType.CLIENT)
public class JunkDeleterClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Register the screen handler
        HandledScreens.register(JunkDeleterMod.JUNK_DELETER_SCREEN_HANDLER, JunkDeleterChestScreen::new);
    }
}
