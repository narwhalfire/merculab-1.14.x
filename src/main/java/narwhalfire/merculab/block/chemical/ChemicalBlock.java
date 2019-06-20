package narwhalfire.merculab.block.chemical;

import narwhalfire.merculab.chemical.Chemical;
import net.minecraft.block.Block;

public class ChemicalBlock extends Block {

    private final Chemical chemical;

    public ChemicalBlock(Chemical chemical, Properties properties) {
        super(properties);
        this.chemical = chemical;
    }
}
