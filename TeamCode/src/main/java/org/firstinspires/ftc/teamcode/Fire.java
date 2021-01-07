package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Objects;

@Disabled
@TeleOp(name = "Fire", group="Iterative Opmode")
public class Fire extends OpMode {

    DcMotor motor;
    Direction direction = Direction.FORWARD;
    boolean active = false;
    double power = 0.1;

    boolean lx, ldu, ldd;

    enum Direction {
        FORWARD (1), BACKWARD (-1);
        int power_modifier;
        Direction(int power_modifier) {
            this.power_modifier = power_modifier;
        }
        Direction reverse() {
            switch (this) {
                case FORWARD:
                    return BACKWARD;
                case BACKWARD:
                    return FORWARD;
                default:
                    return null;
            }
        }
    }

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        int id = hardwareMap.appContext.getResources().getIdentifier("fire", "raw", hardwareMap.appContext.getPackageName());

        if (gamepad1.x && !lx) {
            direction = direction.reverse();
        }
        if (gamepad1.y) {
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, id);
        }

        if(gamepad1.a) {
            active = true;
        } else if(gamepad1.b) {
            active = false;
        }

        if (gamepad1.dpad_up && !ldu) {
            power += 0.01;
        } else if (gamepad1.dpad_down && !ldd) {
            power -= 0.01;
        }

        motor.setPower(active ? power * Objects.requireNonNull(direction).power_modifier : 0);
        telemetry.addData("target power", power);
        telemetry.update();

        lx = gamepad1.x;
        ldu = gamepad1.dpad_up;
        ldd = gamepad1.dpad_down;
    }
}
