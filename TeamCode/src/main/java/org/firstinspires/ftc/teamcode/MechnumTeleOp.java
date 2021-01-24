package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// Tag, name, and grouping of the program
@TeleOp(name = "MechnumTeleOp", group = "MainDrive")
public class MechnumTeleOp extends LinearOpMode {

    // Add robot configuration to code
    Robot robot = new Robot();

    // Declare variables to store and calculate data for movement
    double[][] leftX, leftY, rightX, rightY, motorPowers;

    // Runs when the init button is pressed
    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize robot configuration
        robot.init(hardwareMap);

        // Tell the driver phone that the robot understands it's initialized
        telemetry.addData("Initialized: ", "Waiting for start...");
        telemetry.update();

        // Wait until the start button is pressed
        waitForStart();

        // Main program loop that controls the robot
        while(opModeIsActive()){

            // Get input data and manipulate it to move the robot in the way we want to
            motorControl();

            // Set the powers of the motor based on the calculated values from the motorPowers matrix
            robot.frontLeft.setPower(motorPowers[0][0]);
            robot.frontRight.setPower(motorPowers[0][1]);
            robot.backLeft.setPower(motorPowers[1][0]);
            robot.backRight.setPower(motorPowers[1][1]);
        }
    }

    // Controls and manipulates the robot from the given controller inputs
    private void motorControl(){

        // Strafe (front wheels going against back wheels)
        leftX = new double[][]{
                { gamepad1.left_stick_x, gamepad1.left_stick_x },
                { -gamepad1.left_stick_x, -gamepad1.left_stick_x }
        };

        // Forward (or backward)
        leftY = new double[][]{
                { -gamepad1.left_stick_y, -gamepad1.left_stick_y },
                { -gamepad1.left_stick_y, -gamepad1.left_stick_y }
        };

        // Turn (left and right)
        rightX = new double[][]{
                { -gamepad1.right_stick_x, gamepad1.right_stick_x },
                { -gamepad1.right_stick_x, gamepad1.right_stick_x }
        };

        // Forward (for the other joystick)
        rightY = new double[][]{
                { -gamepad1.right_stick_y, -gamepad1.right_stick_y },
                { -gamepad1.right_stick_y, -gamepad1.right_stick_y }
        };

        // Initialize empty motor power array
        motorPowers = new double[][]{
                {0.0f, 0.0f},
                {0.0f, 0.0f}
        };

        // Initialize a variable to store max value from
        // the motorPowers (this will become important later)
        double max = Double.MIN_VALUE;

        // Add all the matrices together and find the max value in the motorPowers at the same time
        for(int i = 0; i < motorPowers.length; i++){
            for(int j = 0; j < motorPowers[i].length; j++){

                motorPowers[i][j] = leftX[i][j] + leftY[i][j] + rightY[i][j] + rightX[i][j];

                if(motorPowers[i][j] > max){
                    max = motorPowers[i][j];
                }
            }
        }

        // Softmax the motorPower matrix
        // Basically, this maps the range of the numbers in the motorPowers matrix to values from 0 to 1
        for(int i = 0; i < motorPowers.length; i++){
            for(int j = 0; j < motorPowers[i].length; j++){
                motorPowers[i][j] /= max;
            }
        }
    }
}
