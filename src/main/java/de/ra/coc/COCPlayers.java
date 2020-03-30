package de.ra.coc;

import de.ra.exception.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 *
 * The COCPlayers class represents the Clash of Clan's Player.
 * This class requires player's tag and
 * the JSON Web Token to connect to the Clash of Clan server to retrieve the
 * Players information. To get the JSON Web Token see <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
 *
 */
public class COCPlayers {
    private static String JWTOKEN;
    private static String PLAYER_TAG;
    private static JSONObject PLAYER_INFORMATION;

    private static final String API_LINK = "https://api.clashofclans.com/";
    private static final String API_VERSION = "v1";

    /**
     *This constructor will set the JSON Web Token and player's tag and retrives the players information
     * from the Clash of Clan server.
     * To get the JSON Web Token see
     * <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
     * @param JWTOKEN JSON Web Token.
     * @param PLAYER_TAG Player's tag.
     * @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     */
    public COCPlayers(String JWTOKEN, String PLAYER_TAG) throws COCServerConnectionException {
        COCPlayers.JWTOKEN = JWTOKEN;
        changePlayerTag(PLAYER_TAG);
    }

    /**
     * This method changes the player's tag that is set previously and retrieves the information
     * of the new player.
     * @param PLAYER_TAG Player's tag.
     *                   @throws COCServerConnectionException If the connection to Clash of Clan server is failed.
     */
    public void changePlayerTag(String PLAYER_TAG) throws COCServerConnectionException {
        COCPlayers.PLAYER_TAG = PLAYER_TAG;
        JSONObject json = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(API_LINK + API_VERSION + "/players/" + PLAYER_TAG).openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("authorization", "Bearer " + JWTOKEN);

            InputStream input;
            int statusCode = connection.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                input = connection.getInputStream();
            } else {
                input = connection.getErrorStream();
                JSONObject response = InputToJson.getJSONObject(input);
                throw new COCServerConnectionException(statusCode,
                        (String) response.get("reason"),
                        (String) response.get("message"));
            }

            PLAYER_INFORMATION = InputToJson.getJSONObject(input);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the players clan information in JSONObject format.
     * @return Player's clan information in JSONObject format.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public JSONObject getPlayerClanInformation() throws JSONException {
        return PLAYER_INFORMATION.getJSONObject("clan");
    }

    /**
     * This method returns the player's clan name.
     * @return Player's clan name.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanName() throws JSONException {
        return (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .get("name");
    }

    /**
     * This method returns the URL to the player's clan small badge.
     * @return URL to the player's clan small badge.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanSmallBadgeUrl() throws JSONException {
        return (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .getJSONObject("badgeUrls")
                    .get("small");
    }

    /**
     * This method returns the URL to the player's clan medium badge.
     * @return URL to the player's clan medium badge.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanMediumBadgeUrl() throws JSONException {
        return (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .getJSONObject("badgeUrls")
                    .get("medium");
    }

    /**
     * This method returns the URL to the player's clan large badge.
     * @return URL to the player's clan large badge.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanLargeBadgeUrl() throws JSONException {
        return (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .getJSONObject("badgeUrls")
                    .get("large");
    }

    /**
     * This method returns the player's clan tag.
     * @return Player's clan tag.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerClanTag() throws JSONException {
        return (String) PLAYER_INFORMATION.getJSONObject("clan")
                    .get("tag");
    }

    /**
     * This method returns the player's clan level.
     * @return Player's clan level.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerClanLevel() throws JSONException {
        return (Integer) PLAYER_INFORMATION.getJSONObject("clan")
                    .get("clanLevel");
    }

    /**
     * This method returns the player's name.
     * @return Player's name.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerName() throws JSONException {
        return (String) PLAYER_INFORMATION.get("name");
    }

    /**
     * This method returns the player's experience level.
     * @return Player's experience level.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerExperienceLevel() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("expLevel");
    }

    /**
     * This method returns the player's best trophies in home village.
     * @return Player's best trophies in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerBestTrophies() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("bestTrophies");
    }

    /**
     * This method returns the player's best trophies in builder base.
     * @return Player's best trophies in builder base.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerBestVersusTrophies() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("bestVersusTrophies");
    }

    /**
     * This method returns the number of defence won in home village.
     * @return Number of defence won in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerDefenceWin() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("defenseWins");
    }

    /**
     * This method returns the player's tag.
     * @return Player's tag.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerTag() throws JSONException {
        return (String) PLAYER_INFORMATION.get("tag");
    }

    /**
     * This method returns the player's current trophies in home village.
     * @return Player's current trophies in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerCurrentTrophies() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("trophies");
    }

    /**
     * This method returns the player's current trophies in builder base.
     * @return Player's current trophies in builder base.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerCurrentVersusTrophies() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("versusTrophies");
    }

    /**
     * This method returns the player's role in the clan.
     * @return Player's role in the clan.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public String getPlayerRole() throws JSONException {
        return (String) PLAYER_INFORMATION.get("role");
    }

    /**
     * This method returns the player's town hall level in home village.
     * @return Player's town hall level in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerTownHallLevel() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("townHallLevel");
    }

    /**
     * This method returns the player's builder hall level in builder base.
     * @return Player's builder hall level in builder base.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerBuilderHallLevel() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("builderHallLevel");
    }

    /**
     * This method returns the number of battle won in builder base.
     * @return Number of battle won in builder base.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerVersusBattleWin() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("versusBattleWinCount");
    }

    /**
     * This method returns the number of attacks won in home village.
     * @return Number of attacks won in home village.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerAttackWin() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("attackWins");
    }

    /**
     * This method returns the number of troops donation by the player.
     * @return Number of troops donation by the player.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerTroopsDonationCount() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("donations");
    }

    /**
     * This method returns the number of troops received by the other clan member.
     * @return Number of troops received by the other clan member.
     * @throws org.json.JSONException If the processing of JSONObject is failed.
     */
    public Integer getPlayerTroopsReceivedCount() throws JSONException {
        return (Integer) PLAYER_INFORMATION.get("donationsReceived");
    }

    private Integer getIndex(String category, String key) throws JSONException {
        JSONArray troops = PLAYER_INFORMATION.getJSONArray(category);
        for(int index = 0; index < PLAYER_INFORMATION.length(); index++) {
            JSONObject troop = (JSONObject) troops.get(index);
            if( troop.get("name").equals(key))
                return index;
        }

        if(Arrays.asList(Troop.troops).contains(key))
            return 100;
        return 500;
    }

    /**
     * This method return the player's current level of the given troop name.
     * @param troopName Troop name.
     * @return Current level of the troop.
     * @throws JSONException If the processing of JSONObject is failed.
     * @throws JSONException If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public Integer getPlayerTroopLevel(String troopName) throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        return (Integer) getPlayerSingleTroopInfo(troopName).get("level");
    }

    /**
     * This method returns the Player's current troops information in JSONArray format.
     * @return Player's current troops information in JSONArray format.
     * @throws JSONException If the processing of JSONObject is failed.
     */
    public JSONArray getPlayerTroopsInformation() throws JSONException {
        return PLAYER_INFORMATION.getJSONArray("troops");
    }

    /**
     * This method return the maximum level of a troop unlocked by the player.
     * @param troopName Troop name.
     * @return Maximum level of a troop unlocked by the player.
     * @throws JSONException If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public Integer getPlayerTroopMaxLevel(String troopName) throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        return (Integer) getPlayerSingleTroopInfo(troopName).get("maxLevel");
    }

    /**
     * This method returns the village name of the given troop.
     * @param troopName Troop name.
     * @return Village name of the given troop.
     * @throws JSONException If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public String getPlayerTroopVillage(String troopName) throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        return (String) getPlayerSingleTroopInfo(troopName).get("village");
    }

    /**
     * This method returns the village name of the given troop.
     * @param troopName Troop name.
     * @return Village name of the given troop.
     * @throws JSONException If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public String getPlayerTroopName(String troopName) throws JSONException, TroopNotUnlockedException, IllegalTroopNameException {
        return (String) getPlayerSingleTroopInfo(troopName).get("name");
    }

    /**
     * This method returns the complete information of a single troop in JSONObject format.
     * @param troopName Troop name.
     * @return Complete information of a single troop.
     * @throws JSONException If the processing of JSONObject is failed.
     * @throws TroopNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalTroopNameException If the provided the troop name is not valid.
     */
    public JSONObject getPlayerSingleTroopInfo(String troopName) throws JSONException, IllegalTroopNameException, TroopNotUnlockedException {
        Integer index = getIndex("troops", troopName);

        if(index == 100)
            throw new TroopNotUnlockedException(troopName,
                    (String) PLAYER_INFORMATION.get("name"));

        if(index == 500)
            throw new IllegalTroopNameException(troopName);

        return (JSONObject) PLAYER_INFORMATION
                .getJSONArray("troops")
                .get(index);
    }

    /**
     * This method returns the complete information of a single spell in JSONObject format.
     * @param spellName Spell name.
     * @return Complete information of a single spell.
     * @throws JSONException If the processing of JSONObject is failed.
     * @throws SpellNotUnlockedException If the provided troop name is not unlocked by the player.
     * @throws IllegalSpellNameException If the provided the troop name is not valid.
     */
    public JSONObject getPlayerSingleSpellInfo(String spellName) throws JSONException, SpellNotUnlockedException, IllegalSpellNameException {
        Integer index = getIndex("spells", spellName);

        if(index == 100)
            throw new SpellNotUnlockedException(spellName,
                    (String) PLAYER_INFORMATION.get("name"));

        if(index == 500)
            throw new IllegalSpellNameException(spellName);

        return (JSONObject) PLAYER_INFORMATION
                .getJSONArray("troops")
                .get(index);
    }
}