package com.schultzz.twoDimension;

/**
 * Created by ms on 06-04-17.
 */
public class Bee {

    private double _pBest;

    public double[] getCorBest() {
        return _corBest;
    }

    public void setCorBest(double[] _corBest) {
        this._corBest = _corBest;
    }

    private double[] _corBest;
    private double[] _velocity;
    private double _xLoc;
    private double _yLoc;

    public double getPBest() {
        return _pBest;
    }

    public void setPBest(double _pBest) {
        this._pBest = _pBest;
    }

    public double[]get_velocity() {
        return _velocity;
    }

    public void set_velocity(double[] _velocity) {
        this._velocity = _velocity;
    }

    public double get_xLoc() {
        return _xLoc;
    }

    public void set_xLoc(double _xLoc) {
        this._xLoc = _xLoc;
    }

    public double get_yLoc() {
        return _yLoc;
    }

    public void set_yLoc(double _yLoc) {
        this._yLoc = _yLoc;
    }
}
