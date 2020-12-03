package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Arm", group="Iterative Opmode")
public class Arm extends OpMode {

    private Servo servo;
    private static final double open_pos = 0;
    private static final double close_pos = 1;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("ArmServo");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            servo.setPosition(open_pos);
        } else if (gamepad1.b) {
            servo.setPosition(close_pos);
        }
        telemetry.addData("Servo", servo.getPosition());
        telemetry.update();
    }
}
