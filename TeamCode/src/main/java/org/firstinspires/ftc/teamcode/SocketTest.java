package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

@TeleOp(name = "Socket Test", group="Iterative Opmode")
public class SocketTest extends OpMode  {
    Socket server;
    DataOutputStream out;

    @Override
    public void init() {
        try {
            server = new Socket("192.168.49.72", 5038);
//            out    = new DataOutputStream(server.getOutputStream());
//            out.write(new byte[]{'a', 'b'});
//            out.close();
            server.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            telemetry.addData("error", e.toString());
            telemetry.update();
        }
    }

    @Override
    public void loop() {

    }
}
