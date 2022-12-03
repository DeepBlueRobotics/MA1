// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autonomous;
import frc.robot.commands.Drive;
import frc.robot.commands.IntakePlant;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Ramp;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick leftJoy = new Joystick(Constants.leftJoystickPort);
  private final Joystick rightJoy = new Joystick(Constants.rightJoystickPort);

  private final XboxController controller = new XboxController(Constants.controllerPort);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Intake intake = new Intake();
  private final Ramp ramp = new Ramp();

  public RobotContainer() {
    configureButtonBindings();

    driveTrain.setDefaultCommand(new Drive(driveTrain, leftJoy, rightJoy));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Constants.Input.setButtons(controller);

    // normal intaking (set on press) RT
    new JoystickButton(controller, Constants.Input.RT).whenPressed(() -> {
      intake.setIntakeMotors(SmartDashboard.getNumber("Normal Intake", 0));
    });
    // outtaking (when you release it goes back to intaking) LT
    new JoystickButton(controller, Constants.Input.LT).whenPressed(() -> {
      intake.setIntakeMotors(SmartDashboard.getNumber("Normal Outake", 0));
    }).whenReleased(() -> {
      intake.setIntakeMotors(SmartDashboard.getNumber("Normal Intake", 0));
    });
    // slow outtaking (when you release it stops motors) A
    new JoystickButton(controller, Constants.Input.A).whenPressed(() -> {
      intake.setIntakeMotors(SmartDashboard.getNumber("Slow Outake", 0));
    }).whenReleased(() -> {
      intake.setIntakeMotors(0);
    });
    // intake plant B
    new JoystickButton(controller, Constants.Input.B).whenPressed(() -> {
      new IntakePlant(intake, SmartDashboard.getNumber("Slow Intake", 0));
    });
    // TODO: ramp toggle
    new JoystickButton(controller, Constants.Input.Y).whenPressed(() -> {
      ramp.moveRamp(SmartDashboard.getNumber("Ramp Speed", 0));
    }).whenReleased(() -> {
      ramp.moveRamp(0);
    });
    // stop LB
    new JoystickButton(controller, Constants.Input.LB).whenPressed(() -> {
      intake.setIntakeMotors(0);
    });
    // switch drive modes
    new JoystickButton(rightJoy, 12).whenPressed(() -> {
      if (driveTrain.mode == 0) {
        driveTrain.limiterL.reset(DriveTrain.DEFAULT_LIMIT);
        driveTrain.mode = 1;
      } else {
        driveTrain.mode = 0;
      }
    });
    // switch ramp speeds
    new JoystickButton(rightJoy, 5).whenPressed(() -> {
      if (ramp.speed == 0.01) {
        ramp.speed = 0.005;
      } else {
        ramp.speed = 0.01;
      }
    });
    // switch drive speeds
    new JoystickButton(rightJoy, 6).whenPressed(() -> {
      if (driveTrain.speedMult != 0.2) {
        driveTrain.speedMult = 0.2;
      } else {
        driveTrain.speedMult = 0.6;
      }
    });
  }

  /**
   * Use this to pass the autonomous command to dsrthe main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new Autonomous(driveTrain);
  }
}
