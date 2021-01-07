package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp(name = "Encoder\nTest", group="Iterative Opmode")
public class EncoderTest extends OpMode {
    private DcMotor encoder;
    private CRServo servo;

    private static double target_power = 0.5;
    private boolean active = false;

    private boolean last_gamepad1_dpad_up = false;
    private boolean last_gamepad1_dpad_down = false;
    private boolean last_gamepad1_x = false;

    @Override
    public void init() {
        encoder = hardwareMap.dcMotor.get("EncoderMotor");
        servo = hardwareMap.crservo.get("CRServo");

        encoder.setMode(RunMode.STOP_AND_RESET_ENCODER);
        encoder.setMode(RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_down && !last_gamepad1_dpad_down) {
            target_power -= 0.05;
        } else if (gamepad1.dpad_up && !last_gamepad1_dpad_up) {
            target_power += 0.05;
        }

        if (gamepad1.a) {
            active = true;
        } else if (gamepad1.b) {
            active = false;
        }

        servo.setPower(active ? target_power : 0);

        if (gamepad1.y) {
            encoder.setMode(RunMode.STOP_AND_RESET_ENCODER);
            encoder.setMode(RunMode.RUN_USING_ENCODER);
        }

        if (gamepad1.x && !last_gamepad1_x) {
            target_power = -target_power;
        }

        Telemetry();

        last_gamepad1_dpad_up = gamepad1.dpad_up;
        last_gamepad1_dpad_down = gamepad1.dpad_down;
        last_gamepad1_x = gamepad1.x;
    }

    private void Telemetry() {
        telemetry.addData("encoder", encoder.getCurrentPosition());
        telemetry.addData("target power", target_power);
        telemetry.addData("active", active);
        telemetry.update();
    }
}
