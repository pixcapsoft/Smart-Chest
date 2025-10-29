package lk.pixcapsoft.smartchest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;

public class JunkDeleterChestScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    // Special delete slots (outside the chest inventory)
    private final Slot deleteSlot1;
    private final Slot deleteSlot2;
    private final Slot deleteSlot3;

    public JunkDeleterChestScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(JunkDeleterMod.JUNK_DELETER_SCREEN_HANDLER, syncId);
        this.inventory = inventory;

        checkSize(inventory, 27);
        inventory.onOpen(playerInventory.player);

        // Normal chest slots (3x9 grid)
        int i, j;
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }

        // Special delete slots (on the right side of the GUI)
        this.deleteSlot1 = this.addSlot(new Slot(inventory, 27, 140, 20)); // Position of the first delete slot
        this.deleteSlot2 = this.addSlot(new Slot(inventory, 28, 140, 40)); // Position of the second delete slot
        this.deleteSlot3 = this.addSlot(new Slot(inventory, 29, 140, 60)); // Position of the third delete slot

        // Player inventory slots (standard 3x9 grid)
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Hotbar
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        if (inventory instanceof JunkDeleterChestBlockEntity chest) {
            chest.deleteDuplicates();
        }
    }
}
