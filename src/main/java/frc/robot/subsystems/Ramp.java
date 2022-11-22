// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorErrors.TemperatureLimit;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Ramp extends SubsystemBase {
  public static final double raisedRampEncoderPos = .25+.1;//default lowered is -.1 rotations
  public static final double loweredRampEncoderPos = 0;
  public static final double stoppingBounds = raisedRampEncoderPos/2 - .02;
  //^ stop this distance away from the borders of rampEncoders


  public static final double RAMP_SPEED = 1;

  private final CANSparkMax motor = MotorControllerFactory.createSparkMax(Constants.rampMotorPort, TemperatureLimit.NEO_550);
  private final RelativeEncoder motorEncoder = motor.getEncoder();
  private boolean moving = false;

  public Ramp() {
    motorEncoder.setPosition(0.0);
  }

  public void moveRamp(float speed) {
    motor.set(speed);
  }

  public void clampedMoveRamp(float speed) {
    motor.set(speed);
    moving = true;
  }

  public void stopRamp() {
    motor.set(0.0);
  }

  @Override
  public void periodic() {
    if (moving && (Math.abs(motorEncoder.getPosition()-stoppingBounds)>stoppingBounds)) {//out of bounds!
      motor.set(0.0);
      moving = false;
    }
  }
}
