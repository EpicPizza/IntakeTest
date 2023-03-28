// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DashbaordConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.IntakePiece;
import frc.robot.commands.JoystickArm;
import frc.robot.commands.OuttakePiece;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController controller = new CommandXboxController(0);

  private final Intake intake = new Intake();

  private final Arm arm = new Arm();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    arm.setDefaultCommand(new JoystickArm(arm, controller));
  }

  private void startDashboard() {
    Dashboard.Swerve.Debugging.set(DashbaordConstants.SwerveDebugging);
    Dashboard.Swerve.Driver.set(DashbaordConstants.SwerveDriver);
    Dashboard.Elevator.Debugging.set(DashbaordConstants.ElevatorDebugging);
    Dashboard.Elevator.Driver.set(DashbaordConstants.ElevatorDriver);
    Dashboard.Intake.Debugging.set(DashbaordConstants.IntakeDebugging);
    Dashboard.Intake.Driver.set(DashbaordConstants.IntakeDriver);
    Dashboard.Auto.Debugging.set(DashbaordConstants.AutoDebugging);
    Dashboard.Auto.Driver.set(DashbaordConstants.AutoDriver);
    Dashboard.Tele.Debugging.set(DashbaordConstants.TeleDebugging);
    Dashboard.Tele.Driver.set(DashbaordConstants.TeleDriver);
    Dashboard.Limelight.Driver.set(DashbaordConstants.LimelightDebugging);
    Dashboard.Limelight.Driver.set(DashbaordConstants.LimelightDriver);
    Dashboard.Arm.Driver.set(DashbaordConstants.ArmDebugging);
    Dashboard.Arm.Driver.set(DashbaordConstants.ArmDriver);
  }


  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    controller.a().onTrue(Commands.runOnce(() -> intake.moveOut(), intake));
    controller.b().onTrue(Commands.runOnce(() -> intake.moveIn(), intake));
    controller.x().onTrue(Commands.runOnce(() -> intake.stop(), intake));
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  **/
}
