package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Servo Test", group="Iterative Opmode")
public class ServoTest extends OpMode {

    private CRServo leftServo;
    private CRServo rightServo;

    @Override
    public void init() {
        leftServo = hardwareMap.get(CRServo.class, "LeftServo");
        rightServo = hardwareMap.get(CRServo.class, "RightServo");

        leftServo.setDirection(CRServo.Direction.REVERSE);
        rightServo.setDirection(CRServo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            moveServos(1);
        } else {
            moveServos(0);
        }
    }

    private void moveServos(double power) {
        leftServo.setPower(power);
        rightServo.setPower(power);
    }
}
