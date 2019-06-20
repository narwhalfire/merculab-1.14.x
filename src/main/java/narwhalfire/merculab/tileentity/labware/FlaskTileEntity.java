package narwhalfire.merculab.tileentity.labware;

import narwhalfire.merculab.init.MercuLab;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class FlaskTileEntity extends TileEntity {


    public FlaskTileEntity() {
        this(MercuLab.TileEntityTypes.FLASK);
    }

    public FlaskTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
