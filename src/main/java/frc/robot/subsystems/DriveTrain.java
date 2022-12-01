// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorErrors.TemperatureLimit;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final CANSparkMax motorL = MotorControllerFactory.createSparkMax(Constants.leftDriveMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax motorR = MotorControllerFactory.createSparkMax(Constants.rightDriveMotorPort, TemperatureLimit.NEO);

  private final DifferentialDrive differentialDrive = new DifferentialDrive(motorL, motorR);
 
  private final RelativeEncoder encoderL = motorL.getEncoder();
  private final RelativeEncoder encoderR = motorR.getEncoder();

  public int mode = 0; // 0 = tank, 1 = arcade
  public double speed = 1;

  public DriveTrain() {
    motorL.setInverted(true);

    encoderL.setPositionConversionFactor(Constants.wheelCircumference);
    encoderR.setPositionConversionFactor(Constants.wheelCircumference);
  }

  public double getDistance() {
    return Math.max(encoderL.getPosition(), encoderR.getPosition());
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    differentialDrive.tankDrive(leftSpeed * speed, rightSpeed * speed);
  }

  public void arcadeDrive(double speed, double rotation) {
    differentialDrive.arcadeDrive(speed * speed, rotation * speed);
  }

  public void resetEncoders() {
    encoderL.setPosition(0);
    encoderR.setPosition(0);
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() { }

}
