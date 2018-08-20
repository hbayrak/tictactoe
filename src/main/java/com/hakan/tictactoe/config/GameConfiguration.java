package com.hakan.tictactoe.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * A configuration object responsible for specifying the properties of a game to start.
 * 
 * @author hbayrak
 *
 */
public class GameConfiguration {

    private static final String DEFAULT_CONFIG_FILE   = "game-configuration";
    private static final String SIZE_PROPERTY         = "playfield-size";
    private static final String PLAYER_CHARS_PROPERTY = "players";

    private static final int    MIN_PLAYFIELD_SIZE    = 3;
    private static final int    MAX_PLAYFIELD_SIZE    = 10;

    private static final int    PLAYER_COUNT          = 3;

    private ResourceBundle      resource;

    /**
     * 
     * Constructs the GameConfiguration object
     * 
     * @param configFileName
     *            the file name to get the configuration properties in resources folder.
     *            {@link #DEFAULT_CONFIG_FILE} will used if configFileName is null
     * 
     * @exception IllegalArgumentException
     *                if the specified parameter not exists in resources folder
     * 
     */
    public GameConfiguration(String configFileName) {

        configFileName = configFileName == null ? DEFAULT_CONFIG_FILE : configFileName;

        try {
            this.resource = ResourceBundle.getBundle(configFileName);
        } catch (MissingResourceException e) {
            throw new IllegalArgumentException(configFileName + " is not found under resources.");
        }

    }

    /**
     * {@link #SIZE_PROPERTY} will used to get the size value
     * 
     * @return the playfield size specified by the configuration
     */
    public int getPlayFieldSize() {

        String sizeString = resource.getString(SIZE_PROPERTY);

        int size = 0;
        try {
            size = sizeString.length() != 0 ? Integer.parseInt(sizeString) : 0;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Playfield size is invalid.", e);
        }

        if (size < MIN_PLAYFIELD_SIZE || size > MAX_PLAYFIELD_SIZE)
            throw new RuntimeException("Playfield size must be between " + MIN_PLAYFIELD_SIZE + " and " + MAX_PLAYFIELD_SIZE);

        return size;
    }

    /**
     * {@link #PLAYER_CHARS_PROPERTY} will used to get the size value
     * 
     * @return the player characters specified by the configuration
     */
    public List<Character> getPlayerCharacters() {

        List<Character> characters = new ArrayList<>();

        String charString = resource.getString(PLAYER_CHARS_PROPERTY).trim();

        //Expecting a string in format like A,B,C. Other strings are invalid.
        if (charString.length() != PLAYER_COUNT + 2 || charString.split(",").length != PLAYER_COUNT) {
            throw new RuntimeException(charString + " is an invalid player configuration. Please check it.");
        }

        Arrays.stream(charString.split(",")).forEachOrdered(s -> characters.add(s.charAt(0)));

        return characters;
    }

}
