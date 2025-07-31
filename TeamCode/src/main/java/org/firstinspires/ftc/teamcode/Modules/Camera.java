package org.firstinspires.ftc.teamcode.Modules;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLFieldMap;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Camera{
    public static int i ;
    LinearOpMode linearOpMode;
    Limelight3A limelight;
    Servo perekidR;
    Servo perekidL;
    double focalLenght = 640/(2*Math.tan(Math.toRadians(54.505/2)));
    double sampleHeight = 8.5;//cm
    double sampleWight = 3.7;//cm
    double sampleDepth = 3.7;//cm
    double LOOKPOSE = 0.33;

    public Camera(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
        FtcDashboard dash = FtcDashboard.getInstance();
        linearOpMode.telemetry = new MultipleTelemetry(linearOpMode.telemetry,dash.getTelemetry());
        limelight = linearOpMode.hardwareMap.get(Limelight3A.class,"limelight");
        perekidR = linearOpMode.hardwareMap.get(Servo.class,"right_scor_perkid");
        perekidL = linearOpMode.hardwareMap.get(Servo.class,"left_scor_perkid");
        perekidL.setDirection(Servo.Direction.REVERSE);
        limelight.setPollRateHz(100);
        limelight.start();
        limelight.pipelineSwitch(0);
    }
    double distance,tx,ty, shiftAngle;
    public void Update() {
        perekidR.setPosition(LOOKPOSE);
        perekidL.setPosition(LOOKPOSE);

        LLResult result = limelight.getLatestResult();
        if(result!=null && result.getTa()>0.005){
            double[] output = result.getPythonOutput();
            double wight = output[3];
            double heigh = output[4];
            double angle = output[5];
            distance = ((wight>heigh) ? sampleWight *focalLenght/wight: sampleHeight*focalLenght/heigh)*Math.cos(Math.toRadians(45));
            ty = result.getTy();
            shiftAngle = ty*3.08;

            linearOpMode.telemetry.addData("angle", angle);
            linearOpMode.telemetry.addData("shiftAngle",shiftAngle);
            linearOpMode.telemetry.addData("tx",ty);
            linearOpMode.telemetry.addData("ty",result.getTy());
        }
        else{
            shiftAngle = 0;
        }
        linearOpMode.telemetry.update();
    }
    public double AngleReturn(){return shiftAngle;}
}
