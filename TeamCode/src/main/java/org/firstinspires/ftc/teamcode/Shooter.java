package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.SubsystemBase;

public class Shooter extends SubsystemBase {
    private final DcMotor left;
    private final DcMotor right;
    private final DcMotor indexer;

    Shooter(HardwareMap hardwareMap) {
        left = hardwareMap.dcMotor.get("LeftShooter");
        right = hardwareMap.dcMotor.get("RightShooter");
        indexer = hardwareMap.dcMotor.get("IndexMotor");
    }

    public void set(double power) {
        left.setPower(power);
        right.setPower(power);
    }

    public void index(double power) {
        indexer.setPower(power);
    }

    public double get() {
        return left.getPower();
    }

    public void toggle(double power) {
        if (get() == 0) {
            set(power);
        } else {
            set(0);
        }
    }
}