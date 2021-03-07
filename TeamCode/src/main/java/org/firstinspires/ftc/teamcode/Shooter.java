package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        right.setDirection(DcMotorSimple.Direction.REVERSE);

        indexer.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

    public int get_left_encoder() {
        return left.getCurrentPosition();
    }

    public int get_right_encoder() {
        return right.getCurrentPosition();
    }

    public void toggle_index(double power) {
        if (indexer.getPower() == 0) {
            indexer.setPower(power);
        } else {
            indexer.setPower(0);
        }
    }
}