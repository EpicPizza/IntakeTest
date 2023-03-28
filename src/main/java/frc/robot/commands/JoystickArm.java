// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;

public class JoystickArm extends CommandBase {
  private final Arm arm;
  private final CommandXboxController controller;
  /** Creates a new JoystickArm. */
  public JoystickArm(Arm kArm, CommandXboxController kController) {
    // Use addRequirements() here to declare subsystem dependencies.
    arm = kArm;
    controller = kController;
    addRequirements(kArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = controller.getRawAxis(1);
    if(Math.abs(speed) < ArmConstants.deadband) {
      arm.stop();
    } else {
      arm.setSpeed(speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
