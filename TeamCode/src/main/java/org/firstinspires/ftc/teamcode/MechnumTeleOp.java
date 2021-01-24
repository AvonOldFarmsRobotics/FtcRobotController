package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MechnumTeleOp", group = "MainDrive")
public class MechnumTeleOp extends LinearOpMode {

    Robot robot = new Robot();
    double[][] leftX, leftY, rightX, rightY, motorPowers;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        telemetry.addData("Initialized: ", "Waiting for start...");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){

        }
    }

    private void motorControl(){
        leftX = new double[][]{
                { gamepad1.left_stick_x, gamepad1.left_stick_x },
                { -gamepad1.left_stick_x, -gamepad1.left_stick_x }
        };

        leftY = new double[][]{
                { -gamepad1.left_stick_y, -gamepad1.left_stick_y },
                { -gamepad1.left_stick_y, -gamepad1.left_stick_y }
        };

        rightX = new double[][]{
                { -gamepad1.right_stick_x, gamepad1.right_stick_x },
                { -gamepad1.right_stick_x, gamepad1.right_stick_x }
        };

        rightY = new double[][]{
                { -gamepad1.right_stick_y, -gamepad1.right_stick_y },
                { -gamepad1.right_stick_y, -gamepad1.right_stick_y }
        };

        motorPowers = new double[][]{
                {0.0f, 0.0f},
                {0.0f, 0.0f}
        };

        double max = Double.MIN_VALUE;
        for(int i = 0; i < motorPowers.length; i++){
            for(int j = 0; j < motorPowers[i].length; j++){

                motorPowers[i][j] = leftX[i][j] + leftY[i][j] + rightY[i][j] + rightX[i][j];

                if(motorPowers[i][j] > max){
                    max = motorPowers[i][j];
                }
            }
        }

        for(int i = 0; i < motorPowers.length; i++){
            for(int j = 0; j < motorPowers[i].length; j++){
                motorPowers[i][j] /= max;
            }
        }

        robot.frontLeft.setPower(motorPowers[0][0]);
        robot.frontRight.setPower(motorPowers[0][1]);
        robot.backLeft.setPower(motorPowers[1][0]);
        robot.backRight.setPower(motorPowers[1][1]);
    }
}
