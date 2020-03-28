package de.ra.coc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    public static final String ARCHER = "archer";

    /**
     *This constructor will set the JSON Web Token and player's tag and retrives the players information
     * from the Clash of Clan server.
     * To get the JSON Web Token see
     * <a href="https://developer.clashofclans.com/">https://developer.clashofclans.com/</a>
     * @param JWTOKEN JSON Web Token.
     * @param PLAYER_TAG Player's tag.
     */
    public COCPlayers(String JWTOKEN, String PLAYER_TAG) {
        COCPlayers.JWTOKEN = JWTOKEN;
        changePlayerTag(PLAYER_TAG);
    }

    /**
     * This method changes the player's tag that is set previously and retrieves the information
     * of the new player.
     * @param PLAYER_TAG Player's tag.
     */
    public void changePlayerTag(String PLAYER_TAG) {
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
            }

            PLAYER_INFORMATION = InputToJson.getJSONObject(input);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
}