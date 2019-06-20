package narwhalfire.merculab.block.labware;

import com.google.common.collect.Maps;
import narwhalfire.merculab.state.properties.BlockStateProperties;
import narwhalfire.merculab.state.properties.LabBenchSide;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LabBenchBlock extends Block implements IForgeBlock {

    public static final EnumProperty<LabBenchSide> NORTH = BlockStateProperties.NORTH_BENCH;
    public static final EnumProperty<LabBenchSide> SOUTH = BlockStateProperties.SOUTH_BENCH;
    public static final EnumProperty<LabBenchSide> EAST = BlockStateProperties.EAST_BENCH;
    public static final EnumProperty<LabBenchSide> WEST = BlockStateProperties.WEST_BENCH;
    protected static final Map<Direction, EnumProperty<LabBenchSide>> DIRECTION_TO_PROPERTY_MAP
            = Util.make(Maps.newEnumMap(Direction.class), (map) -> {
        map.put(Direction.NORTH, NORTH);
        map.put(Direction.SOUTH, SOUTH);
        map.put(Direction.EAST, EAST);
        map.put(Direction.WEST, WEST);
    });
    protected final VoxelShape[] shapeParts;

    public LabBenchBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState()
                                 .with(NORTH, LabBenchSide.NULL)
                                 .with(SOUTH, LabBenchSide.NULL)
                                 .with(EAST, LabBenchSide.NULL)
                                 .with(WEST, LabBenchSide.NULL));
        this.shapeParts = makeShapes(1.0, 0.0, 15.0, 2.0);
    }

    private VoxelShape[] makeShapes(double benchV, double benchH, double cabinetV, double cabinetH) {
        double cabinetH2 = 16.0 - cabinetH;
        double benchH2 = 16.0 - benchH;
        benchV = benchV + cabinetV;
        VoxelShape benchtop = Block.makeCuboidShape(benchH, cabinetV, benchH, benchH2, benchV, benchH2);
        VoxelShape cabinetC = Block.makeCuboidShape(cabinetH, 0.0, cabinetH, cabinetH2, cabinetV, cabinetH2);
        VoxelShape cabinetN = Block.makeCuboidShape(cabinetH, 0.0, 0.0, cabinetH2, cabinetV, cabinetH);
        VoxelShape cabinetS = Block.makeCuboidShape(cabinetH, 0.0, cabinetH2, cabinetH2, cabinetV, 16.0);
        VoxelShape cabinetE = Block.makeCuboidShape(cabinetH2, 0.0, cabinetH, 16.0, cabinetV, cabinetH2);
        VoxelShape cabinetW = Block.makeCuboidShape(0.0, 0.0, cabinetH, cabinetH, cabinetV, cabinetH2);
        VoxelShape cabinetNW = Block.makeCuboidShape(0.0, 0.0, 0.0, cabinetH, cabinetV, cabinetH);
        VoxelShape cabinetNE = Block.makeCuboidShape(cabinetH2, 0.0, 0.0, 16.0, cabinetV, cabinetH);
        VoxelShape cabinetSW = Block.makeCuboidShape(0.0, 0.0, cabinetH2, cabinetH, cabinetV, 16.0);
        VoxelShape cabinetSE = Block.makeCuboidShape(cabinetH2, 0.0, cabinetH2, 16.0, cabinetV, 16.0);

        return new VoxelShape[] {benchtop, cabinetC, cabinetN, cabinetS, cabinetE, cabinetW, cabinetNW, cabinetNE,
                                 cabinetSW, cabinetSE};

    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction direction, BlockState directionState, IWorld worldIn, BlockPos currentPos, BlockPos directionPos) {
        return direction.getAxis().getPlane() == Direction.Plane.HORIZONTAL
               ? stateIn.with(DIRECTION_TO_PROPERTY_MAP.get(direction), getAttachmentPref(worldIn, currentPos, direction))
               : super.updatePostPlacement(stateIn, direction, directionState, worldIn, currentPos, directionPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext) {
        VoxelShape voxelShape = VoxelShapes.or(shapeParts[0], shapeParts[1]);
        for (VoxelShape vs : getSideShapes(state)) {
            voxelShape = VoxelShapes.or(voxelShape, vs);
        }
        for (VoxelShape vs : getCornerShapes(state)) {
            voxelShape = VoxelShapes.or(voxelShape, vs);
        }
        return voxelShape;
    }

    @Override
    public boolean onBlockActivated(BlockState p_220051_1_, World p_220051_2_, BlockPos p_220051_3_, PlayerEntity p_220051_4_, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
        return super.onBlockActivated(p_220051_1_, p_220051_2_, p_220051_3_, p_220051_4_, p_220051_5_, p_220051_6_);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IBlockReader blockReader = context.getWorld();
        BlockPos blockPos = context.getPos();
        return super.getStateForPlacement(context)
                    .with(NORTH, getAttachmentPref(blockReader, blockPos, Direction.NORTH))
                    .with(SOUTH, getAttachmentPref(blockReader, blockPos, Direction.SOUTH))
                    .with(EAST, getAttachmentPref(blockReader, blockPos, Direction.EAST))
                    .with(WEST, getAttachmentPref(blockReader, blockPos, Direction.WEST));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST);
    }

    private VoxelShape[] getSideShapes(BlockState blockState) {
        List<VoxelShape> shapes = new ArrayList<>();
        if (blockState.get(NORTH) != LabBenchSide.NULL) shapes.add(shapeParts[2]);
        if (blockState.get(SOUTH) != LabBenchSide.NULL) shapes.add(shapeParts[3]);
        if (blockState.get(EAST) != LabBenchSide.NULL) shapes.add(shapeParts[4]);
        if (blockState.get(WEST) != LabBenchSide.NULL) shapes.add(shapeParts[5]);
        return shapes.toArray(new VoxelShape[] {});
    }

    private VoxelShape[] getCornerShapes(BlockState blockState) {
        List<VoxelShape> shapes = new ArrayList<>();
        if (shouldPlaceCorner(blockState, Direction.NORTH, Direction.WEST)) shapes.add(shapeParts[6]);
        if (shouldPlaceCorner(blockState, Direction.NORTH, Direction.EAST)) shapes.add(shapeParts[7]);
        if (shouldPlaceCorner(blockState, Direction.SOUTH, Direction.WEST)) shapes.add(shapeParts[8]);
        if (shouldPlaceCorner(blockState, Direction.SOUTH, Direction.EAST)) shapes.add(shapeParts[9]);
        return shapes.toArray(new VoxelShape[] {});
    }

    private boolean shouldPlaceCorner(BlockState blockState, Direction side1, Direction side2) {
        return !side1.getAxis().isVertical() && !side2.getAxis().isVertical() && side1.getOpposite() != side2 &&
               side1 != side2 && blockState.get(DIRECTION_TO_PROPERTY_MAP.get(side1)).getMeta() +
                                 blockState.get(DIRECTION_TO_PROPERTY_MAP.get(side2)).getMeta() > 2;
    }

    private LabBenchSide getAttachmentPref(IBlockReader blockReader, BlockPos blockPos, Direction direction) {
        BlockPos adjPos = blockPos.offset(direction);
        BlockState adjState = blockReader.getBlockState(adjPos);
        if (adjState.getBlock() instanceof LabBenchBlock) {
            return LabBenchSide.BENCH;
        }
        if (cannotAttach(adjState.getBlock())) {
            return LabBenchSide.NULL;
        }
        if (Block.hasSolidSide(adjState, blockReader, blockPos, direction)) {
            return LabBenchSide.FULL;
        }

        return LabBenchSide.NULL;
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        return false;
    }
}
