package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {

    // Declaring motors on the robot
    public DcMotor frontLeft, frontRight, backLeft, backRight;

    // Declare a hardware map the robot
    HardwareMap hwmap;

    // Constructor (idk for some reason, the thing I copied this from had it)
    public Robot(){

    }

    // Initialized the motors and configures them
    public void init(HardwareMap hwmap){
        // Transfer the hardware map from the outside program into this one
        this.hwmap = hwmap;

        // Load the motor from the hardware map into memory
        frontLeft = this.hwmap.get(DcMotor.class, "frontLeft");
        frontRight = this.hwmap.get(DcMotor.class, "frontRight");
        backLeft = this.hwmap.get(DcMotor.class, "backLeft");
        backRight = this.hwmap.get(DcMotor.class, "backRight");

        // Set the direction of the motors (can be changed later)
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        // Set the mode of the motor (RUN_USING_ENCODER, RUN_WITHOUT_ENCODER, RUN_TO_POSITION)
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Precautionary step to stop the motors before any movement begins
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
