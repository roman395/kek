package org.firstinspires.ftc.teamcode.Test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Modules.Claw;
@TeleOp
@Config
public class DiffTest extends LinearOpMode {
    public static double R = 0.5;
    public static double L = 0.5;
    @Override
    public void runOpMode() throws InterruptedException {
        Claw claw = new Claw(this);
        waitForStart();
        while (opModeIsActive()){
            claw.setRightDif(R);
            claw.setLeftDif(L);
        }
    }
}
