package com.joe.android.russiacup.Players;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Players {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("players")
    @Expose
    private List<Player> players = null;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}