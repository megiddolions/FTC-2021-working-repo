package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.lib.MegiddoGamepad;


@TeleOp(name = "Shooter Test", group="Iterative Opmode")
public class ShooterTest extends OpMode {

    private DcMotor motor;
    private static double power = 1;
    private static double change_rate = 0.1;

    MegiddoGamepad Gamepad1;
    MegiddoGamepad Gamepad2;

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("TestMotor");

        Gamepad1 = new MegiddoGamepad();
        Gamepad2 = new MegiddoGamepad();
    }

    @Override
    public void loop() {
        Gamepad1.update(gamepad1);
        Gamepad2.update(gamepad2);

        if (Gamepad1.a_Pressed()) {
            if (motor.getPower() == 0) {
                motor.setPower(power);
            } else {
                motor.setPower(0);
            }
        }

        if (Gamepad1.dpad_up_Pressed()) {
            power += change_rate;
        } else if (Gamepad1.dpad_down_Pressed()) {
            power -= change_rate;
        }

        if (Gamepad1.dpad_left_Pressed()) {
            change_rate *= 2;
        } else if (Gamepad1.dpad_right_Pressed()) {
            change_rate /= 2;
        }

        telemetry.addData("power", power);
        telemetry.addData("change rate", change_rate);
        telemetry.update();
    }
}
