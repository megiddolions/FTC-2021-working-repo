package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "PWM Test (angle)", group="Iterative Opmode")
public class testAngle extends OpMode {

    private Servo servo;

    private double target = 0.93;
    private boolean moved = false;

    private boolean last_gamepad1_dpad_up = false;
    private boolean last_gamepad1_dpad_down = false;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "Stest");
        servo.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            moved = true;
        } else if (gamepad1.b) {
            moved = false;
        }

        if (gamepad1.dpad_down && !last_gamepad1_dpad_down) {
            target -= 0.01;
        } else if (gamepad1.dpad_up && !last_gamepad1_dpad_up) {
            target += 0.01;
        }

        if (!moved) {
            servo.setPosition(0);
        } else {
            servo.setPosition(target);
        }


        telemetry.addData("servo", servo.getPosition());
        telemetry.addData("target position", target);
        telemetry.update();
        last_gamepad1_dpad_up = gamepad1.dpad_up;
        last_gamepad1_dpad_down = gamepad1.dpad_down;
    }
}
