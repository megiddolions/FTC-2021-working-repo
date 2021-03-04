package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.lib.MegiddoGamepad;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

@TeleOp(name = "Index Test", group="Iterative Opmode")
public class IndexTest extends OpMode {
    // UI
    private static int option = 0;
    private static double power = 0.5;
    private static double index_speed = 1;
    private static double change_rate = 0.1;
    private static boolean manual_index = false;
    // Subsystems
    private Shooter shooter;
    // Gamepads
    MegiddoGamepad Gamepad1;
    MegiddoGamepad Gamepad2;
    // Debug
    File log;
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dataFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

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


        if (Gamepad1.dpad_down_Pressed()) {
            option++;
            if (option == 4)
                option = 0;
        } else if (Gamepad1.dpad_up_Pressed()) {
            option--;
            if (option == -1)
                option = 3;
        }

        if (manual_index) {
            shooter.index(gamepad1.right_stick_y);
        } else {
            if (Gamepad1.x_Pressed()) {
                shooter.toggle_index(index_speed);
            }
        }

        // UI
        if (Gamepad1.dpad_left_Pressed()) {
            switch (option) {
                case 0:
                    power += change_rate;
                    break;
                case 1:
                    index_speed += change_rate;
                    break;
                case 2:
                    manual_index = !manual_index;
                    break;
                case 3:
                    change_rate *= 2;
            }
        } else if (Gamepad1.dpad_right_Pressed()) {
            switch (option) {
                case 0:
                    power -= change_rate;
                    break;
                case 1:
                    index_speed -= change_rate;
                    break;
                case 2:
                    manual_index = !manual_index;
                    break;
                case 3:
                    change_rate /= 2;
            }
        }


        telemetry.addData((option == 0 ? "* " : "   ") + "power             ", "%.3f", power);
        telemetry.addData((option == 1 ? "* " : "   ") + "index speed ", "%.3f", index_speed);
        telemetry.addData((option == 2 ? "* " : "   ") + "manual index", manual_index);
        telemetry.addData((option == 3 ? "* " : "   ") + "change rate ", "%.3f", change_rate);
        telemetry.update();
    }
}