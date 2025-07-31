package org.firstinspires.ftc.teamcode.Addition;

import org.opencv.core.Mat;

public class TurretAngelCalculate {
    double distanceCalc(double point1x, double point1y, double point2x, double point2y) {
        return Math.sqrt(Math.pow(point2x - point1x, 2) + Math.pow((point2y - point1y), 2));
    }
    double angelCalc(double centerX, double centerY, double forwardX, double forwardY, double targetX, double targetY) {
        double k0 = (centerY - forwardY) - (centerX - forwardX);
        double b0 = centerY - k0 * centerX;
        double k1 = -1 / k0;
        double b1 = targetY + targetX / k0;
        double intersectionY = k0 * (b1 - b0) + b0;
        double intersectionX = (b1 - b0) / (k0 - k1);
        double leg = distanceCalc(centerX, centerY, intersectionX, intersectionY);
        double hypotenuse = distanceCalc(centerX, centerY, targetX, targetY);
        if (hypotenuse > leg) {
            double angelRad = Math.asin(leg / hypotenuse);
            double angelCel = 180 / Math.PI * angelRad;
            return angelCel;
        }
        return 0;
    }
}

