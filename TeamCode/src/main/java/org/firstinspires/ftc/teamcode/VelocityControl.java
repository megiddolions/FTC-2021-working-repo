package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.Constants.NetworkConstants;
import org.firstinspires.ftc.teamcode.Constants.RuntimeConstants;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

@TeleOp(name = "Shooter Velocity Test", group = "Iterative Opmode")
public class VelocityControl extends OpMode implements Runnable {
    private Socket server = null;
    private DataOutputStream out;
    private Thread debugTread = null;

    private ShooterSubsystem shooter;

    VelocityControl() {
        super();
    }

    @Override
    public void init() {
        if (RuntimeConstants.send_to_port) {
            try {
                server = new Socket(NetworkConstants.computer_ip, NetworkConstants.server_port);
                out = new DataOutputStream(server.getOutputStream());

                debugTread = new Thread(this);
            } catch (IOException e) {
                telemetry.addData("error", e.toString());
                telemetry.update();
            }
        }

        shooter = new ShooterSubsystem(hardwareMap);
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        if (debugTread != null) {
            debugTread.interrupt();
        }
        if (server != null) {
            try {
                server.close();
            } catch (IOException ignored) {}
        }
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {

        super.updateTelemetry(telemetry);
    }

    @Override
    public void run() {

        while (true) {
            try {
                out.write(ByteBuffer.allocate(8 + 8 * 2)
                        .putDouble(getRuntime())
                        .putDouble(shooter.getLeftVelocity())
                        .putDouble(shooter.getRightVelocity())
                        .array());
            } catch (IOException ignored) {
                return;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ignored) {}
        }
    }
}
