package de.ra.exception.notUnlockedException;

/**
 * This class is a throwable exception to indicate that the given hero name is not
 * yet unlocked by the player.
 *
 * @author Ranjith Krishnamurthy
 */
public class HeroNotUnlockedException extends ItemNotUnlockedException {
    /**
     * Constructs a new HeroNotUnlockedException with the detailed message.
     *
     * @param heroName   Hero name. Example: Barbarian King, Archer Queen etc.
     *                   See the list of all heroes {@link de.ra.coc.COCData.Heroes}
     * @param playerName Player name.
     */
    public HeroNotUnlockedException(String heroName, String playerName) {
        super(heroName, playerName);
    }
}
