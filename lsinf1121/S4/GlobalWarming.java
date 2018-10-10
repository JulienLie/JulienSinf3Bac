import java.util.List;

abstract class GlobalWarming {


    final int[][] altitude;

    /**
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     */
    public GlobalWarming(int[][] altitude) {
        this.altitude = altitude;
    }

    /**
     *
     * @param waterLevel
     * @return the number of entries in altitude matrix that would be above
     *         the specified waterLevel.
     *         Warning: this is not the waterLevel given in the constructor/
     */
    public abstract int nbSafePoints(int waterLevel);

}
