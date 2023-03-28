// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Dashboard;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
  private final CANSparkMax armMotor = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);

  /** Creates a new Arm. */
  public Arm() {
    armMotor.restoreFactoryDefaults();
    armMotor.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    Dashboard.Arm.Debugging.putNumber("Arm Speed", armMotor.get());
  }

  public void setSpeed(double speed) {
    armMotor.set(speed * ArmConstants.speedLimit);
  }

  public void stop() {
    armMotor.stopMotor();
  }
}
