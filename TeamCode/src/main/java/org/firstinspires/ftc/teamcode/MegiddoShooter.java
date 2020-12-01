package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

class MegiddoShooter extends Shooter {
    private final OpMode opMode;

    MegiddoShooter(OpMode opMode) {
        super(opMode.hardwareMap.get(DcMotor.class, "LeftShooter"), opMode.hardwareMap.get(DcMotor.class, "RightShooter"));
        this.opMode = opMode;
        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}
