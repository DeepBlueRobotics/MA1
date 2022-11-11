// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorErrors.TemperatureLimit;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class DriveTrain extends SubsystemBase {
  CANSparkMax motorL = MotorControllerFactory.createSparkMax(Constants.leftDriveMotorPort, TemperatureLimit.NEO);
  CANSparkMax motorR = MotorControllerFactory.createSparkMax(Constants.rightDriveMotorPort, TemperatureLimit.NEO);

  DifferentialDrive differentialDrive = new DifferentialDrive(motorL, motorR);
 
  
  Joystick leftJoy, rightJoy;

  public DriveTrain(Joystick leftJoy, Joystick rightJoy) {
    this.motorR.setInverted(true);

    this.leftJoy = leftJoy;
    this.rightJoy = rightJoy;
    
  }

  public void drive(double leftSpeed, double rightSpeed) {
      differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
    drive(leftJoy.getY() * -1, rightJoy.getY() * -1);
  }

  @Override
  public void simulationPeriodic() {

    }
  }

