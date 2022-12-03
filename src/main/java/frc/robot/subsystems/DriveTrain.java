// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorErrors.TemperatureLimit;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final CANSparkMax motorL = MotorControllerFactory.createSparkMax(Constants.leftDriveMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax motorR = MotorControllerFactory.createSparkMax(Constants.rightDriveMotorPort, TemperatureLimit.NEO);

  private final DifferentialDrive differentialDrive = new DifferentialDrive(motorL, motorR);

  public static final double DEFAULT_LIMIT = 0.75;
 
  public final SlewRateLimiter limiterL = new SlewRateLimiter(DEFAULT_LIMIT);
  public final SlewRateLimiter limiterR = new SlewRateLimiter(DEFAULT_LIMIT);

  private final RelativeEncoder encoderL = motorL.getEncoder();
  private final RelativeEncoder encoderR = motorR.getEncoder();

  public int mode = 0; // 0 = tank, 1 = arcade
  public double speedMult = 0.6;

  public DriveTrain() {
    motorL.setInverted(true);

    encoderL.setPositionConversionFactor(Constants.wheelCircumference);
    encoderR.setPositionConversionFactor(Constants.wheelCircumference);
  
    SmartDashboard.putNumber("Limiter Speed", DEFAULT_LIMIT);
  }

  public void updateLimiter() {
    //limiter.reset(SmartDashboard.getNumber("Limiter Speed", DEFAULT_LIMIT));
  }

  public double getDistance() {
    return Math.max(encoderL.getPosition(), encoderR.getPosition());
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    updateLimiter();
    SmartDashboard.putNumber("left tankdrive speed", leftSpeed * this.speedMult);
    SmartDashboard.putNumber("right tankdrive speed", rightSpeed * this.speedMult);
    differentialDrive.tankDrive(limiterL.calculate(leftSpeed * this.speedMult), limiterR.calculate(rightSpeed * this.speedMult));
  }

  public void arcadeDrive(double speed, double rotation) {
    updateLimiter();
    differentialDrive.arcadeDrive(limiterL.calculate(speed * this.speedMult), rotation * this.speedMult);
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
