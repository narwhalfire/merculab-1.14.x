package narwhalfire.merculab.block.labware;

import narwhalfire.merculab.init.MercuLab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;

import javax.annotation.Nullable;

public class BlockJar extends Block implements IForgeBlock {

    public BlockJar(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return MercuLab.TileEntityTypes.JAR.create();
    }

}
