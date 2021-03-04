package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.lib.MegiddoGamepad;
@TeleOp(name = "Index Test", group="Iterative Opmode")
public class IndexTest extends OpMode {
    private static double power = 1;
    private static double change_rate = 0.1;
    private Shooter shooter;

    MegiddoGamepad Gamepad1;
    MegiddoGamepad Gamepad2;

    @Override
    public void init() {
        shooter = new Shooter(hardwareMap);

        Gamepad1 = new MegiddoGamepad();
        Gamepad2 = new MegiddoGamepad();
    }

    @Override
    public void loop() {
        Gamepad1.update(gamepad1);
        Gamepad2.update(gamepad2);

        if (Gamepad1.a_Pressed()) {
            shooter.toggle(power);
        } else if (shooter.get() != 0) {
            shooter.set(power);
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

        telemetry.addData("power", "%.3f", power);
        telemetry.addData("change rate", "%.3f", change_rate);
        telemetry.update();
    }
}