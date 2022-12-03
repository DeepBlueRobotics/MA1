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
  public static final double loweredRampEncoderPos = 0;//default lowered is -.1
  public static final double threshold = .02;
  //public static final double stoppingBounds = (raisedRampEncoderPos-loweredRampEncoderPos)/2 - threshold;
  //stop this distance away from the borders of rampEncoders -----------------------------------^
  public int currentRampPos = -1;// -1 down, 1 up


  public double speed = 0.01;

  private final CANSparkMax motor = MotorControllerFactory.createSparkMax(Constants.rampMotorPort, TemperatureLimit.NEO_550);
  private final RelativeEncoder motorEncoder = motor.getEncoder();

  public Ramp() {
    motorEncoder.setPosition(0.0);

    SmartDashboard.putNumber("Ramp Speed", speed);
    SmartDashboard.putNumber("Ramp Threshhold", threshold);
    SmartDashboard.putNumber("Ramp Raised Encoder Pos", raisedRampEncoderPos);
    SmartDashboard.putNumber("Ramp Lowered Encoder Pos", loweredRampEncoderPos);
  }

  public void moveRamp(double speed) {
    motor.set(speed);
  }

  public void stopRamp(){motor.set(0.0);}

  public boolean outOfBounds(int direction) {
    // return SmartDashboard.getNumber("Ramp Lowered Encoder Pos", loweredRampEncoderPos) + SmartDashboard.getNumber("Ramp Threshhold", threshold) > motorEncoder.getPosition() || motorEncoder.getPosition() > SmartDashboard.getNumber("Ramp Raised Encoder Pos", raisedRampEncoderPos) - SmartDashboard.getNumber("Ramp Threshhold", threshold);
    speed = SmartDashboard.getNumber("Ramp Std Speed", .05);
    if (direction == 1) {
      return raisedRampEncoderPos - threshold <= motorEncoder.getPosition();
    } else {
      return loweredRampEncoderPos + threshold >= motorEncoder.getPosition();
    }
  }

  @Override
  public void periodic() {
    // speed=SmartDashboard.getNumber("Ramp Std Speed", .05);
  }
}
