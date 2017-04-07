package com.schultzz.fourDimension;

/**
 * Created by ms on 06-04-17.
 */
public class Bee {

    private double _pBest;
    private double[] _corBest;
    private double[] _velocity;
    private double[] _location;

    public double[] getLocation() {
        return _location;
    }

    public void setLocation(double[] _location) {
        this._location = _location;
    }

    public double[] getCorBest() {
        return _corBest;
    }

    public void setCorBest(double[] _corBest) {
        this._corBest = _corBest;
    }

    public double getPBest() {
        return _pBest;
    }

    public void setPBest(double _pBest) {
        this._pBest = _pBest;
    }

    public double[] get_velocity() {
        return _velocity;
    }

    public void set_velocity(double[] _velocity) {
        this._velocity = _velocity;
    }


}
