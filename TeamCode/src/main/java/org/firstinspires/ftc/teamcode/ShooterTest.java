package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Shooter Test")
public class ShooterTest extends OpMode {

    private MegiddoShooter shooter;
    private Servo leftServo;
    private Servo rightServo;

    
    @Override
    public void init() {
        shooter = new MegiddoShooter(this);

        leftServo = hardwareMap.get(Servo.class, "LeftServo");
        rightServo = hardwareMap.get(Servo.class, "RightServo");

        leftServo.setDirection(Servo.Direction.REVERSE);
        rightServo.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        shooter.set(Util.deadbound(-gamepad1.left_stick_y, 0.15));

        if (gamepad1.a) {
            moveServos(1);
        } else {
            moveServos(0);
        }

        telemetry.addData("Left", shooter.getLeftMotor().getPower());
        telemetry.addData("Right", shooter.getRightMotor().getPower());
        telemetry.update();
    }

    private void moveServos(double power) {
        leftServo.setPosition(power);
        rightServo.setPosition(power);
    }
}
