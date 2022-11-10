// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorErrors.TemperatureLimit;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final CANSparkMax motorL = MotorControllerFactory.createSparkMax(Constants.leftDriveMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax motorR = MotorControllerFactory.createSparkMax(Constants.rightDriveMotorPort, TemperatureLimit.NEO);

  private final DifferentialDrive differentialDrive = new DifferentialDrive(motorL, motorR);
 
  private final RelativeEncoder encoderL = motorL.getEncoder();
  private final RelativeEncoder encoderR = motorR.getEncoder();
  
  private final Joystick leftJoy, rightJoy;

  public DriveTrain(Joystick leftJoy, Joystick rightJoy) {
    motorR.setInverted(true);

    encoderL.setPositionConversionFactor(Constants.wheelCircumference);
    encoderR.setPositionConversionFactor(Constants.wheelCircumference);

    this.leftJoy = leftJoy;
    this.rightJoy = rightJoy;
  }

  public double getDistance() {
    return Math.max(encoderL.getPosition(), encoderR.getPosition());
  }

  public void drive(double leftSpeed, double rightSpeed) {
      differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void forward(double speed) {
    differentialDrive.tankDrive(speed, speed);
  }

  public void stop() {
    differentialDrive.tankDrive(0, 0);
  }

  @Override
  public void periodic() {
    drive(leftJoy.getY() * -1, rightJoy.getY() * -1);
  }

  @Override
  public void simulationPeriodic() {

  }
}