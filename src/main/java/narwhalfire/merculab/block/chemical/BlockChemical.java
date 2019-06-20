package narwhalfire.merculab.block.chemical;

import narwhalfire.merculab.chemical.Chemical;
import net.minecraft.block.Block;

public class BlockChemical extends Block {

    private final Chemical chemical;

    public BlockChemical(Chemical chemical, Properties properties) {
        super(properties);
        this.chemical = chemical;
    }
}
