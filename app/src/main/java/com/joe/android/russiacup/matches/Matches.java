package com.joe.android.russiacup.matches;

import java.util.ArrayList;

public class Matches
{
    private Links _links;

    public Links getLinks() { return this._links; }

    public void setLinks(Links _links) { this._links = _links; }

    private int count;

    public int getCount() { return this.count; }

    public void setCount(int count) { this.count = count; }

    private ArrayList<Fixture> fixtures;

    public ArrayList<Fixture> getFixtures() { return this.fixtures; }

    public void setFixtures(ArrayList<Fixture> fixtures) { this.fixtures = fixtures; }
}
