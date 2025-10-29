package lk.pixcapsoft.smartchest;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import lk.pixcapsoft.smartchest.JunkDeleterChestScreenHandler;
import lk.pixcapsoft.smartchest.JunkDeleterMod;

public class JunkDeleterChestBlockEntity extends LockableContainerBlockEntity implements NamedScreenHandlerFactory {

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(27, ItemStack.EMPTY); // Example: 27 slots

    public JunkDeleterChestBlockEntity(BlockPos pos, BlockState state) {
        super(JunkDeleterMod.JUNK_DELETER_CHEST_ENTITY, pos, state);
    }

    @Override
    protected Text getContainerName() {
        return Text.literal("Junk Deleter Chest");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId,
            net.minecraft.entity.player.PlayerInventory playerInventory) {
        return new JunkDeleterChestScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return items;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        for (int i = 0; i < list.size(); i++) {
            items.set(i, list.get(i));
        }
        deleteDuplicates();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, items);
    }

    public void deleteDuplicates() {
        // Check the special slots (slots 27, 28, and 29)
        ItemStack deleteItem1 = items.get(27); // Delete trigger for slot 1
        ItemStack deleteItem2 = items.get(28); // Delete trigger for slot 2
        ItemStack deleteItem3 = items.get(29); // Delete trigger for slot 3

        // Remove duplicates for each of the special items
        deleteDuplicatesForItem(deleteItem1);
        deleteDuplicatesForItem(deleteItem2);
        deleteDuplicatesForItem(deleteItem3);
    }

    private void deleteDuplicatesForItem(ItemStack deleteItem) {
        if (deleteItem.isEmpty())
            return;

        // Iterate through the inventory and remove duplicates
        for (int i = 0; i < items.size(); i++) {
            ItemStack stack = items.get(i);
            if (!stack.isEmpty() && ItemStack.areItemsAndComponentsEqual(deleteItem, stack)) {
                // Only remove duplicates, not the item in the delete slot
                if (i != 27 && i != 28 && i != 29) {
                    items.set(i, ItemStack.EMPTY);
                }
            }
        }
    }

    public void markDirtyAndCheck() {
        super.markDirty();
        deleteDuplicates();
    }
}
