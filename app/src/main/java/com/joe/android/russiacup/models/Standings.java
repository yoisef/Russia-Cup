package com.joe.android.russiacup.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Standings {

    @SerializedName("A")
    @Expose
    private List<A> a = null;
    @SerializedName("B")
    @Expose
    private List<B> b = null;
    @SerializedName("C")
    @Expose
    private List<C> c = null;
    @SerializedName("D")
    @Expose
    private List<D> d = null;
    @SerializedName("E")
    @Expose
    private List<E> e = null;
    @SerializedName("F")
    @Expose
    private List<F> f = null;
    @SerializedName("G")
    @Expose
    private List<G> g = null;
    @SerializedName("H")
    @Expose
    private List<H> h = null;

    public List<A> getA() {
        return a;
    }

    public void setA(List<A> a) {
        this.a = a;
    }

    public List<B> getB() {
        return b;
    }

    public void setB(List<B> b) {
        this.b = b;
    }

    public List<C> getC() {
        return c;
    }

    public void setC(List<C> c) {
        this.c = c;
    }

    public List<D> getD() {
        return d;
    }

    public void setD(List<D> d) {
        this.d = d;
    }

    public List<E> getE() {
        return e;
    }

    public void setE(List<E> e) {
        this.e = e;
    }

    public List<F> getF() {
        return f;
    }

    public void setF(List<F> f) {
        this.f = f;
    }

    public List<G> getG() {
        return g;
    }

    public void setG(List<G> g) {
        this.g = g;
    }

    public List<H> getH() {
        return h;
    }

    public void setH(List<H> h) {
        this.h = h;
    }

}