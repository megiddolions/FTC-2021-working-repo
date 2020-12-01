package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Shooter {
    protected final DcMotor left;
    protected final DcMotor right;

    public Shooter(DcMotor left, DcMotor right) {
        this.left = left;
        this.right = right;
    }

    public void set(double power) {
        left.setPower(power);
        right.setPower(power);
    }

    public void setLeft(double power) {
        left.setPower(power);
    }

    public void setRight(double power) {
        right.setPower(power);
    }

    public DcMotor getRightMotor() {
        return right;
    }

    public DcMotor getLeftMotor() {
        return left;
    }

    public double get() {
        return left.getPower();
    }
}
