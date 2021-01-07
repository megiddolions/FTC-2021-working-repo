package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp(name = "בדיקת הזנה", group="Iterative Opmode")
public class Feeding extends OpMode {

    DcMotor motor;
    CRServo servo;

    @Override
    public void init() {
        servo = hardwareMap.crservo.get("CRServo");
        motor = hardwareMap.dcMotor.get("motor");
    }

    @Override
    public void loop() {
        servo.setPower(gamepad1.left_stick_y);
        motor.setPower(gamepad1.right_stick_y);
    }
}
