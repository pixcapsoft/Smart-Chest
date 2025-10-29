package lk.pixcapsoft.smartchest;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JunkDeleterChestBlock extends ChestBlock {

    public JunkDeleterChestBlock() {
        super(Settings.create().strength(2.5F), 
              () -> JunkDeleterMod.JUNK_DELETER_CHEST_ENTITY);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new JunkDeleterChestBlockEntity(pos, state);
    }

    @Override
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, net.minecraft.util.Hand hand, net.minecraft.util.hit.BlockHitResult hit) {
        // Open screen handler when right-clicked
        if (!world.isClient) {
            player.openHandledScreen((JunkDeleterChestBlockEntity) world.getBlockEntity(pos));
        }
    }
}
