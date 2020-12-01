package org.firstinspires.ftc.teamcode;

public class Util {


    public static double clamp(double value, double max, double min) {
        return Math.min(max, Math.max(value, min));
    }

    public static double deadbound(double value, double bound) {
        return Math.abs(value) > bound ? value : 0;
    }
}
