// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  WPI_TalonSRX motorL = new WPI_TalonSRX(Constants.leftDriveMotorPort);
  WPI_TalonSRX motorR = new WPI_TalonSRX(Constants.rightDriveMotorPort);

  DifferentialDrive drive;

  public DriveTrain() {
    this.motorR.setInverted(true);
    this.drive = new DifferentialDrive(motorL, motorR);
  }

  public void drive(double leftSpeed, double rightSpeed) {
      this.drive.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }
}
