package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Modules.Claw;

@TeleOp
public class ClawTest extends LinearOpMode {
    Claw claw;
    @Override
    public void runOpMode() throws InterruptedException {
        claw = new Claw(this);
        waitForStart();
        while (opModeIsActive()){
            claw.Update(0);
        }
    }
}
