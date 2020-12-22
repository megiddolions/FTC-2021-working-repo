package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name = "Shooter Test", group="Iterative Opmode")
public class ShooterTest extends OpMode {

    private MegiddoShooter shooter;

    
    @Override
    public void init() {
        shooter = new MegiddoShooter(this);
    }

    @Override
    public void loop() {
        shooter.set(Util.deadbound(-gamepad1.left_stick_y, 0.15));

        telemetry.addData("Left", shooter.getLeftMotor().getPower());
        telemetry.addData("Right", shooter.getRightMotor().getPower());
        telemetry.update();
    }
}
