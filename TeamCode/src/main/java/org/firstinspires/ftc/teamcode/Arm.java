package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Arm", group="Iterative Opmode")
public class Arm extends OpMode {

    private Servo servo;
    private static final double close_pos = 0;

    private static double target = 1;
    private boolean moved = false;
    private boolean last_gamepad1_dpad_up = false;
    private boolean last_gamepad1_dpad_down = false;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("ArmServo");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            moved = true;
        } else if (gamepad1.b) {
            moved = false;
        }

        if (moved) {
            servo.setPosition(target);
        } else {
            servo.setPosition(close_pos);
        }

        if (gamepad1.dpad_down && !last_gamepad1_dpad_down) {
            target -= 0.05;
        } else if (gamepad1.dpad_up && !last_gamepad1_dpad_up) {
            target += 0.05;
        }

        telemetry.addData("Servo", servo.getPosition());
        telemetry.update();

        last_gamepad1_dpad_up = gamepad1.dpad_up;
        last_gamepad1_dpad_down = gamepad1.dpad_down;
    }
}
