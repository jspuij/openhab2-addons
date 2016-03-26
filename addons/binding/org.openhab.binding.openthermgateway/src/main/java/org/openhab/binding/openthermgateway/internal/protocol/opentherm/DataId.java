/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openhab.binding.openthermgateway.internal.protocol.opentherm;

import java.util.HashMap;
import java.util.Map;

/**
 * DataId enumeration.
 *
 * @author Jan-Willem Spuij
 *
 */
public enum DataId {

  /**
   * Master and Slave Status flags.
   */
  STATUS(0, "Master and Slave Status flags.", StatusFrame.class),

  /**
   * Control setpoint ie CH water temperature setpoint (°C).
   */
  TSET(1, "Control setpoint ie CH water temperature setpoint (°C)", DecimalFrame.class),

  /**
   * Master Configuration Flags / Master MemberID Code.
   */
  M_CONFIG_M_MEMBERIDCODE(2, "Master Configuration Flags / Master MemberID Code",
      MasterInfoFrame.class),

  /**
   * Slave Configuration Flags / Slave MemberID Code.
   */
  S_CONFIG_S_MEMBERIDCODE(3, "Slave Configuration Flags / Slave MemberID Code",
      SlaveInfoFrame.class),

  /**
   * Remote Command.
   */
  COMMAND(4, "Remote Command", DoubleUnsignedIntegerFrame.class),

  /**
   * Application-specific fault flags and OEM fault code.
   */
  ASF_FLAGS_OEM_FAULT_CODE(5, "Application-specific fault flags and OEM fault code",
      AsfFrame.class),

  /**
   * Remote boiler parameter transfer-enable & read/write flags.
   */
  RBP_FLAGS(6, "Remote boiler parameter transfer-enable & read/write flags", RbpFrame.class),

  /**
   * Cooling control signal (%).
   */
  COOLING_CONTROL(7, "Cooling control signal (%)", DecimalFrame.class),

  /**
   * Control setpoint for 2e CH circuit (°C).
   */
  TSETCH2(8, "Control setpoint for 2e CH circuit (°C)", DecimalFrame.class),

  /**
   * Remote override room setpoint.
   */
  TROVERRIDE(9, "Remote override room setpoint", DecimalFrame.class),

  /**
   * Number of Transparent-Slave-Parameters supported by slave.
   */
  TSP(10, "Number of Transparent-Slave-Parameters supported by slave",
      DoubleUnsignedIntegerFrame.class),

  /**
   * Index number / Value of referred-to transparent slave parameter.
   */
  TSP_INDEX_TSP_VALUE(11, "Index number / Value of referred-to transparent slave parameter.",
      DoubleUnsignedIntegerFrame.class),

  /**
   * Size of Fault-History-Buffer supported by slave.
   */
  FHB_SIZE(12, "Size of Fault-History-Buffer supported by slave", DoubleUnsignedIntegerFrame.class),

  /**
   * Index number / Value of referred-to fault-history buffer entry.
   */
  FHB_INDEX_FHB_VALUE(13, "Index number / Value of referred-to fault-history buffer entry.",
      DoubleUnsignedIntegerFrame.class),

  /**
   * Maximum relative modulation level setting (%).
   */
  MAX_REL_MOD_LEVEL_SETTING(14, "Maximum relative modulation level setting (%)",
      DecimalFrame.class),

  /**
   * Maximum boiler capacity (kW) / Minimum boiler modulation level(%).
   */
  MAX_CAPACITY_MIN_MOD_LEVEL(15,
      "Maximum boiler capacity (kW) / Minimum boiler modulation level(%)",
      DoubleUnsignedIntegerFrame.class),

  /**
   * Room Setpoint (°C).
   */
  TRSET(16, "Room Setpoint (°C)", DecimalFrame.class),

  /**
   * Relative Modulation Level (%).
   */
  REL_MOD_LEVEL(17, "Relative Modulation Level (%)", DecimalFrame.class),

  /**
   * Water pressure in CH circuit (bar).
   */
  CH_PRESSURE(18, "Water pressure in CH circuit (bar)", DecimalFrame.class),

  /**
   * Water flow rate in DHW circuit. (litres/minute).
   */
  DHW_FLOW_RATE(19, "Water flow rate in DHW circuit. (litres/minute)", DecimalFrame.class),

  /**
   * Day of Week and Time of Day.
   */
  DAY_TIME(20, "Day of Week and Time of Day", DayTimeFrame.class),

  /**
   * Calendar date.
   */
  DATE(21, "Calendar date", DoubleUnsignedIntegerFrame.class),

  /**
   * Calendar year.
   */
  YEAR(22, "Calendar year", UnsignedIntegerFrame.class),

  /**
   * Room Setpoint for 2nd CH circuit (°C).
   */
  TRSETCH2(23, "Room Setpoint for 2nd CH circuit (°C)", DecimalFrame.class),

  /**
   * Room temperature (°C).
   */
  TR(24, "Room temperature (°C)", DecimalFrame.class),

  /**
   * Boiler flow water temperature (°C).
   */
  TBOILER(25, "Boiler flow water temperature (°C)", DecimalFrame.class),

  /**
   * DHW temperature (°C).
   */
  TDHW(26, "DHW temperature (°C)", DecimalFrame.class),

  /**
   * Outside temperature (°C).
   */
  TOUTSIDE(27, "Outside temperature (°C)", DecimalFrame.class),

  /**
   * Return water temperature (°C).
   */
  TRET(28, "Return water temperature (°C)", DecimalFrame.class),

  /**
   * Solar storage temperature (°C).
   */
  TSTORAGE(29, "Solar storage temperature (°C)", DecimalFrame.class),

  /**
   * Solar collector temperature (°C).
   */
  TCOLLECTOR(30, "Solar collector temperature (°C)", DecimalFrame.class),

  /**
   * Flow water temperature CH2 circuit (°C).
   */
  TFLOWCH2(31, "Flow water temperature CH2 circuit (°C)", DecimalFrame.class),

  /**
   * Domestic hot water temperature 2 (°C).
   */
  TDHW2(32, "Domestic hot water temperature 2 (°C)", DecimalFrame.class),

  /**
   * Boiler exhaust temperature (°C).
   */
  TEXHAUST(33, "Boiler exhaust temperature (°C)", SignedIntegerFrame.class),

  /**
   * DHW setpoint upper & lower bounds for adjustment (°C).
   */
  TDHWSET_UB_TDHWSET_LB(48, "DHW setpoint upper & lower bounds for adjustment (°C)",
      DoubleSignedIntegerFrame.class),

  /**
   * Max CH water setpoint upper & lower bounds for adjustment (°C).
   */
  MAXTSET_UB_MAXTSET_LB(49, "Max CH water setpoint upper & lower bounds for adjustment (°C)",
      DoubleSignedIntegerFrame.class),

  /**
   * OTC heat curve ratio upper & lower bounds for adjustment.
   */
  HCRATIO_UB_HCRATIO_LB(50, "OTC heat curve ratio upper & lower bounds for adjustment",
      DoubleSignedIntegerFrame.class),

  /**
   * DHW setpoint (°C) (Remote parameter 1).
   */
  TDHWSET(56, "DHW setpoint (°C) (Remote parameter 1)", DecimalFrame.class),

  /**
   * Max CH water setpoint (°C) (Remote parameters 2).
   */
  MAXTSET(57, "Max CH water setpoint (°C) (Remote parameters 2)", DecimalFrame.class),

  /**
   * OTC heat curve ratio (°C) (Remote parameter 3).
   */
  HCRATIO(58, "OTC heat curve ratio (°C) (Remote parameter 3)", DecimalFrame.class),

  /**
   * Function of manual and program changes in master and remote.
   */
  REMOTE_OVERRIDE_FUNCTION(100, "Function of manual and program changes in master and remote",
      RemoteOverideFrame.class),

  /**
   * OEM-specific diagnostic/service code.
   */
  OEM_DIAGNOSTIC_CODE(115, "OEM-specific diagnostic/service code", UnsignedIntegerFrame.class),

  /**
   * Number of starts burner.
   */
  BURNER_STARTS(116, "Number of starts burner", UnsignedIntegerFrame.class),

  /**
   * Number of starts CH pump.
   */
  CH_PUMP_STARTS(117, "Number of starts CH pump", UnsignedIntegerFrame.class),

  /**
   * Number of starts DHW pump/valve.
   */
  DHW_PUMPVALVE_STARTS(118, "Number of starts DHW pump/valve", UnsignedIntegerFrame.class),

  /**
   * Number of starts burner during DHW mode.
   */
  DHW_BURNER_STARTS(119, "Number of starts burner during DHW mode", UnsignedIntegerFrame.class),

  /**
   * Number of hours that burner is in operation (i.e. flame on).
   */
  BURNER_OPERATION_HOURS(120, "Number of hours that burner is in operation (i.e. flame on)",
      UnsignedIntegerFrame.class),

  /**
   * Number of hours that CH pump has been running.
   */
  CH_PUMP_OPERATION_HOURS(121, "Number of hours that CH pump has been running",
      UnsignedIntegerFrame.class),

  /**
   * Number of hours that DHW pump has been running or DHW valve.
   */
  DHW_PUMPVALVE_OPERATION_HOURS(122, "Number of hours that DHW pump has been running or DHW valve",
      UnsignedIntegerFrame.class),

  /**
   * Number of hours that burner is in operation during DHW mode.
   */
  DHW_BURNER_OPERATION_HOURS(123, "Number of hours that burner is in operation during DHW mode",
      UnsignedIntegerFrame.class),

  /**
   * The implemented version of the OpenTherm Protocol Specification.
   */
  OPENTHERM_VERSION_MASTER(124, "The implemented version of the OpenTherm Protocol Specification",
      DecimalFrame.class),

  /**
   *
   */
  OPENTHERM_VERSION_SLAVE(125, "The implemented version of the OpenTherm Protocol Specification",
      DecimalFrame.class),

  /**
   * The implemented version of the OpenTherm Protocol Specification.
   */
  MASTER_VERSION(126, "Master product version number and type", DoubleUnsignedIntegerFrame.class),

  /**
   * Slave product version number and type.
   */
  SLAVE_VERSION(127, "Slave product version number and type", DoubleUnsignedIntegerFrame.class);

  /**
   * A mapping between the integer and its corresponding
   * enumeration to facilitate lookup by code.
   */
  private static Map<Integer, DataId> mapping;

  /**
   * Key of the enumeration value.
   */
  private final int key;

  /**
   * Description of the enumeration value.
   */
  private final String description;

  /**
   * {@link OpenthermFrame} class that should be instantiated for this {@link DataId}.
   */
  private final Class<? extends OpenthermFrame> frameClass;

  /**
   * Initializes a new instance of the enumeration.
   *
   * @param key Key of the enumeration value.
   * @param description Description of the enumeration value.
   * @param frameClass {@link OpenthermFrame} class that should be instantiated for this
   *          {@link DataId}.
   */
  DataId(final int key, final String description,
      final Class<? extends OpenthermFrame> frameClass) {
    this.key = key;
    this.description = description;
    this.frameClass = frameClass;
  }

  /**
   * Initializes the mapping.
   */
  private static void initMapping() {
    mapping = new HashMap<Integer, DataId>();
    for (DataId d : values()) {
      mapping.put(d.key, d);
    }
  }

  /**
   * Returns a {@link DataId}.
   *
   * @param key the key to use to get the DataId.
   * @return the {@link DataId}.
   */
  static DataId getDataId(final int key) {
    if (mapping == null) {
      initMapping();
    }

    return mapping.get(key);
  }

  /**
   * @return the key
   */
  public int getKey() {
    return key;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the frame class.
   */
  public Class<? extends OpenthermFrame> getFrameClass() {
    return frameClass;
  }
}
