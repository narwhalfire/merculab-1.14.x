package narwhalfire.merculab.chemical;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class Chemical extends ForgeRegistryEntry<Chemical> {

    public Properties properties;

    public Chemical(Properties properties) {
        this.properties = properties;
    }

    /**
     * Mostly chemical and physical properties intrinsic to the chemical.
     * Most values are ints to avoid inaccuracies in floating point arithmetic
     * and because floating point precision is unnecessary. Molecular masses are
     * calculated from the sum of the rounded atomic masses. Atomic masses are
     * rounded to the nearest whole mg/mol before summing.
     */
    public static class Properties {

        public static final Properties WATER = new Properties(18000, 1000000, 273, 373);
        public static final Properties METHANE = new Properties(16000, 657, 91, 112);
        public static final Properties SODIUM_CHLORIDE = new Properties(58443, 2170000, 1074, 1738);
        public static final Properties FERRIC_OXIDE = new Properties(159687, 5250000, 1812, 0);
        public static final Properties TUNGSTEN_CARBIDE = new Properties(195850, 15630000, 3100, 6270);

        /** mg/mol */
        private int molecularMass;
        /** g/m^3 */
        private int density;
        /** Kelvin */
        private int melting;
        /** Kelvin */
        private int boiling;

        Properties() {

        }

        Properties(int molecularMass, int density, int melting, int boiling) {
            this.molecularMass = molecularMass;
            this.density = density;
            this.melting = melting;
            this.boiling = boiling;
        }
    }
}
