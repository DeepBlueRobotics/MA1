// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorErrors.TemperatureLimit;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Ramp extends SubsystemBase {
  public static final double raisedRampEncoderPos = .25;
  public static final double loweredRampEncoderPos = -.1;//default lowered is -.1
  public static final double stoppingBounds = (raisedRampEncoderPos-loweredRampEncoderPos)/2 - .02;
  //stop this distance away from the borders of rampEncoders -----------------------------------^
  public static int currentRampPos = -1;// -1 down, 1 up


  public static double STD_RAMP_SPEED = .05;

  private final CANSparkMax motor = MotorControllerFactory.createSparkMax(Constants.rampMotorPort, TemperatureLimit.NEO_550);
  private final RelativeEncoder motorEncoder = motor.getEncoder();
  private boolean moving = false;

  public Ramp() {
    motorEncoder.setPosition(0.0);

    SmartDashboard.putNumber("Ramp Std Speed", STD_RAMP_SPEED);
  }

  public void moveRamp(double speed) {
    motor.set(speed);
  }

  public void stopRamp(){motor.set(0.0);}

  public boolean outOfBounds(){
    return (Math.abs(motorEncoder.getPosition()-stoppingBounds)>stoppingBounds);
  }

  @Override
  public void periodic() {
    STD_RAMP_SPEED=SmartDashboard.getNumber("Ramp Std Speed", .05);
  }
}
