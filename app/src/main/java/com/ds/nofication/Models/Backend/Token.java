package com.ds.nofication.Models.Backend;

public class Token {
    private String key;

    /**
     * Get the current token key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the current token key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Create a token with the key given
     * @param token The token to set as key.
     */
    public Token(String token) {
        key = token;
    }

    /**
     * Creates a token where key is default string.
     */
    public Token() {
    }
}
