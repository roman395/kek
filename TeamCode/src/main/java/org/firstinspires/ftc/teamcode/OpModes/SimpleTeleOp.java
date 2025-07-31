package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Camera;
import org.firstinspires.ftc.teamcode.Modules.Claw;

@TeleOp
@Config

public class SimpleTeleOp extends LinearOpMode {
    public static boolean isCamWorking = false;
    Camera cam;
    Claw claw;
    @Override
    public void runOpMode() throws InterruptedException {
        cam = new Camera(this);
        claw = new Claw(this);
        ElapsedTime timer = new ElapsedTime();
        waitForStart();
        timer.reset();
        while (opModeIsActive()){
            cam.Update();
            if(timer.milliseconds()>=4000 && isCamWorking)
                claw.Update(cam.AngleReturn());

        }
    }
}
