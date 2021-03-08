package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.Constants.NetworkConstants;
import org.firstinspires.ftc.teamcode.Constants.RuntimeConstants;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@TeleOp(name = "Shooter Velocity Test", group = "Iterative Opmode")
public class VelocityControl extends OpMode implements Runnable {
    private Socket server = null;
    private DataOutputStream out;
    private Thread debugTread = null;

    private ShooterSubsystem shooter;

    @Override
    public void init() {

        shooter = new ShooterSubsystem(hardwareMap);
        if (RuntimeConstants.send_to_port) {
            try {
                server = new Socket(NetworkConstants.computer_ip, NetworkConstants.server_port);
                out = new DataOutputStream(server.getOutputStream());

                debugTread = new Thread(this);

                debugTread.start();
            } catch (IOException e) {
                telemetry.addData("error", e.toString());
                telemetry.update();
            }
        }
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
        Socket server;
        DataOutputStream out;
        DataInputStream in;
        // not including time var
        int vars_count = 2;
        try {
            server = new Socket(NetworkConstants.computer_ip, NetworkConstants.server_port);
            out = new DataOutputStream(server.getOutputStream());
//            in = new DataInputStream(server.getInputStream());

            out.write(ByteBuffer.allocate(4).putInt(vars_count).array());

            while (true) {
                out.write(ByteBuffer.allocate(8 + 8 * 2)
                        .putDouble(getRuntime())
                        .putDouble(shooter.getLeftVelocity())
                        .putDouble(shooter.getRightVelocity())
                        .array());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.toString());
            telemetry.addData("error", e.toString());
            telemetry.update();
        }
    }
}
