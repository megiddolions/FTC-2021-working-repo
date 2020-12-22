package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@Disabled
@TeleOp(name = "PWM Test (RC)", group="Iterative Opmode")
public class PWMtest extends OpMode {

    private CRServo servo;
    private double power = 1;
    private boolean last_gamepad1_dpad_up = false;
    private boolean last_gamepad1_dpad_down = false;

    @Override
    public void init() {
        servo = hardwareMap.crservo.get("test");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            servo.setPower(power);
        } else if (gamepad1.b) {
            servo.setPower(-power);
        } else {
            servo.setPower(0);
        }

        if (gamepad1.dpad_down && !last_gamepad1_dpad_down) {
            power -= 0.1;
        } else if (gamepad1.dpad_up && !last_gamepad1_dpad_up) {
            power += 0.1;
        }

        telemetry.addData("target power", power);
        telemetry.update();
        last_gamepad1_dpad_up = gamepad1.dpad_up;
        last_gamepad1_dpad_down = gamepad1.dpad_down;
    }
}
