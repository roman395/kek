package org.firstinspires.ftc.teamcode.Learning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

@TeleOp(name = "GEY")
public class easy extends LinearOpMode {
    DcMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class, "motor");
        waitForStart();
        while (opModeIsActive()) {
            motor.setPower(0.7);
        }
    }

    private PIDCoefficients pid = new PIDCoefficients(1, 1, 1);
    double target = 500;
    double lastError = 0;
    double lastTime = 0;
    double integral = 0;

    public void PID(DcMotor mot) {
        double error = target - mot.getCurrentPosition();
        double curTime = System.currentTimeMillis();
        double proportional = error * pid.p;
        double deltaTime = curTime - lastTime;
        double differential = (error - lastError) / deltaTime * pid.d;
        integral += error * deltaTime * pid.i;
        double output = proportional + differential + integral;

        mot.setPower(output);

        lastError = error;
        lastTime = curTime;

    }
}
