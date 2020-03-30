package de.ra.exception;

/**
 * This class is a throwable exception to indicate that the given troop name is not
 * yet unlocked by the player.
 */
public class TroopNotUnlockedException extends Exception{
    /**
     * Constructs a new TroopNotUnlockedException with the detailed message.
     * @param troopName Troop name.
     * @param playerName Player name.
     */
    public TroopNotUnlockedException(String troopName, String playerName) {
        super("Troop " +
                troopName + " is not yet unlocked by the player " +
                playerName);
    }
}
