// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class IntakeProp extends CommandBase {
  private final Intake intake;

  private boolean finished = false;

  private Timer speedUp = new Timer();

  private int stage;
  /** Creates a new IntakeMotor. */
  public IntakeProp(Intake rIntake) {
    intake = rIntake;
    addRequirements(rIntake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    stage = 0;
    finished = false;
    intake.moveIn();
    speedUp.reset();
    speedUp.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(stage == 0) {
      if(intake.speedUp()) {
        stage = 1;
        speedUp.stop();
      } else {
        SmartDashboard.putNumber("Trying speedup", speedUp.get());
          if(speedUp.get() > IntakeConstants.kSpeedUpFailTime) {
            stage = -1;
            intake.stop();
            speedUp.stop();
            finished = true;
          } else {
            intake.moveIn();
          }
      }
    } else {
      if(intake.objectHeld()) {
        if(stage == 1) {
          intake.stop();
          finished = true;
          stage = -1;
        } else {
          intake.moveIn();
        }
      } else {
        intake.moveIn();
      }
    }
    SmartDashboard.putNumber("Intake Step", stage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
