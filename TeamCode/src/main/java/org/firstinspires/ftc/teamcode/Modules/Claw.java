package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
@Config

public class Claw {
    Servo upDownL;
    Servo upDownR;
    Servo rightLeft;
    Servo difL;
    Servo difR;
    Servo claw;
    LinearOpMode linearOpMode;
    double currentRotClaw,currentRotArm;
    public static double ZEROARMPOSE = 0.3;
    public static double ZEROCLAWROTPOSE = 0.4;
    public static double ZEROCLAWPOSE = -0.15;
    double LEFTMAX = 0.65;
    public static double ZEROHORIZONTALPOS = 0.51;
    double RIGHTMAX = 0.37;
    public static double DegreeTest = 0;
    public static double CLAWCLOSE = 0.62;
    public Claw(LinearOpMode linearOpMode){
        this.linearOpMode = linearOpMode;
        upDownR = linearOpMode.hardwareMap.get(Servo.class,"right_vertical_servo");
        upDownL = linearOpMode.hardwareMap.get(Servo.class,"left_vertical_servo");
        rightLeft = linearOpMode.hardwareMap.get(Servo.class,"horizontal_servo");
        difL = linearOpMode.hardwareMap.get(Servo.class,"left_diff_servo");
        difR = linearOpMode.hardwareMap.get(Servo.class,"right_diff_servo");
        claw = linearOpMode.hardwareMap.get(Servo.class,"claw_servo");

        upDownL.setDirection(Servo.Direction.REVERSE);
        difL.setDirection(Servo.Direction.REVERSE);
        currentRotClaw = 0;
        currentRotArm = 0;
    }
    public void Update(double shiftAngel){
        difR.setPosition(ZEROCLAWPOSE+ZEROCLAWROTPOSE);
        difL.setPosition(ZEROCLAWPOSE-ZEROCLAWROTPOSE);
        upDownL.setPosition(ZEROARMPOSE);
        upDownR.setPosition(ZEROARMPOSE);
        rightLeft.setPosition(DegreeToServo(shiftAngel));
        claw.setPosition(CLAWCLOSE);
    }
    public void setRightDif(double pos){difR.setPosition(pos);}
    public void setLeftDif(double pos){difL.setPosition(pos);}
    public void RotateClaw(double angle){}
    public double DegreeToServo(double angle){return 0.37+(angle+45)/90*0.28;}

}
