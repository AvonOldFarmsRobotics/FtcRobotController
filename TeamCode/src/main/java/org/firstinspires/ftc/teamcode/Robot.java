package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public DcMotor frontLeft, frontRight, backLeft, backRight;

    HardwareMap hwmap;
    public Robot(){

    }

    public void init(HardwareMap hwmap){
        this.hwmap = hwmap;

        frontLeft = this.hwmap.get(DcMotor.class, "frontLeft");
        frontRight = this.hwmap.get(DcMotor.class, "frontRight");
        backLeft = this.hwmap.get(DcMotor.class, "backLeft");
        backRight = this.hwmap.get(DcMotor.class, "backRight");

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
