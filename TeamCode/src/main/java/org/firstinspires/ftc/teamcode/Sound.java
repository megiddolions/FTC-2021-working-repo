package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name = "Sound", group="Iterative Opmode")
public class Sound extends LinearOpMode {

    @Override
    public void runOpMode() {
        int id = hardwareMap.appContext.getResources().getIdentifier("fire", "raw", hardwareMap.appContext.getPackageName());

        telemetry.addData("fire resource", SoundPlayer.getInstance().preload(hardwareMap.appContext, id) ? "Found" : "Not found\n Add fire.wav to /src/main/res/raw" );
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.y) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, id);
                telemetry.addData("playing", "");
                telemetry.update();
            }
        }
    }
}
