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
  STATUS(0, "Master and Slave Status flags"),

  /**
   * Control setpoint ie CH water temperature setpoint (°C).
   */
  TSET(1, "Control setpoint ie CH water temperature setpoint (°C)"),

  /**
   * Master Configuration Flags / Master MemberID Code.
   */
  M_CONFIG_M_MEMBERIDCODE(2, "Master Configuration Flags / Master MemberID Code"),

  /**
   * Slave Configuration Flags / Slave MemberID Code.
   */
  S_CONFIG_S_MEMBERIDCODE(3, "Slave Configuration Flags / Slave MemberID Code"),

  /**
   * Remote Command.
   */
  COMMAND(4, "Remote Command"),

  /**
   * Application-specific fault flags and OEM fault code.
   */
  ASF_FLAGS_OEM_FAULT_CODE(5, "Application-specific fault flags and OEM fault code"),

  /**
   * Remote boiler parameter transfer-enable & read/write flags.
   */
  RBP_FLAGS(6, "Remote boiler parameter transfer-enable & read/write flags"),

  /**
   * Cooling control signal (%).
   */
  COOLING_CONTROL(7, "Cooling control signal (%)"),

  /**
   * Control setpoint for 2e CH circuit (°C).
   */
  TSETCH2(8, "Control setpoint for 2e CH circuit (°C)"),

  /**
   * Remote override room setpoint.
   */
  TROVERRIDE(9, "Remote override room setpoint"),

  /**
   * Number of Transparent-Slave-Parameters supported by slave.
   */
  TSP(10, "Number of Transparent-Slave-Parameters supported by slave"),

  /**
   * Index number / Value of referred-to transparent slave parameter.
   */
  TSP_INDEX_TSP_VALUE(11, "Index number / Value of referred-to transparent slave parameter."),

  /**
   * Size of Fault-History-Buffer supported by slave.
   */
  FHB_SIZE(12, "Size of Fault-History-Buffer supported by slave"),

  /**
   * Index number / Value of referred-to fault-history buffer entry.
   */
  FHB_INDEX_FHB_VALUE(13, "Index number / Value of referred-to fault-history buffer entry."),

  /**
   * Maximum relative modulation level setting (%).
   */
  MAX_REL_MOD_LEVEL_SETTING(14, "Maximum relative modulation level setting (%)"),

  /**
   * Maximum boiler capacity (kW) / Minimum boiler modulation level(%).
   */
  MAX_CAPACITY_MIN_MOD_LEVEL(15,
      "Maximum boiler capacity (kW) / Minimum boiler modulation level(%)"),

  /**
   * Room Setpoint (°C).
   */
  TRSET(16, "Room Setpoint (°C)"),

  /**
   * Relative Modulation Level (%).
   */
  REL_MOD_LEVEL(17, "Relative Modulation Level (%)"),

  /**
   * Water pressure in CH circuit (bar).
   */
  CH_PRESSURE(18, "Water pressure in CH circuit (bar)"),

  /**
   * Water flow rate in DHW circuit. (litres/minute).
   */
  DHW_FLOW_RATE(19, "Water flow rate in DHW circuit. (litres/minute)"),

  /**
   * Day of Week and Time of Day.
   */
  DAY_TIME(20, "Day of Week and Time of Day"),

  /**
   * Calendar date.
   */
  DATE(21, "Calendar date"),

  /**
   * Calendar year.
   */
  YEAR(22, "Calendar year"),

  /**
   * Room Setpoint for 2nd CH circuit (°C).
   */
  TRSETCH2(23, "Room Setpoint for 2nd CH circuit (°C)"),

  /**
   * Room temperature (°C).
   */
  TR(24, "Room temperature (°C)"),

  /**
   * Boiler flow water temperature (°C).
   */
  TBOILER(25, "Boiler flow water temperature (°C)"),

  /**
   * DHW temperature (°C).
   */
  TDHW(26, "DHW temperature (°C)"),

  /**
   * Outside temperature (°C).
   */
  TOUTSIDE(27, "Outside temperature (°C)"),

  /**
   * Return water temperature (°C).
   */
  TRET(28, "Return water temperature (°C)"),

  /**
   * Solar storage temperature (°C).
   */
  TSTORAGE(29, "Solar storage temperature (°C)"),

  /**
   * Solar collector temperature (°C).
   */
  TCOLLECTOR(30, "Solar collector temperature (°C)"),

  /**
   * Flow water temperature CH2 circuit (°C).
   */
  TFLOWCH2(31, "Flow water temperature CH2 circuit (°C)"),

  /**
   * Domestic hot water temperature 2 (°C).
   */
  TDHW2(32, "Domestic hot water temperature 2 (°C)"),

  /**
   * Boiler exhaust temperature (°C).
   */
  TEXHAUST(33, "Boiler exhaust temperature (°C)"),

  /**
   * DHW setpoint upper & lower bounds for adjustment (°C).
   */
  TDHWSET_UB_TDHWSET_LB(48, "DHW setpoint upper & lower bounds for adjustment (°C)"),

  /**
   * Max CH water setpoint upper & lower bounds for adjustment (°C).
   */
  MAXTSET_UB_MAXTSET_LB(49, "Max CH water setpoint upper & lower bounds for adjustment (°C)"),

  /**
   * OTC heat curve ratio upper & lower bounds for adjustment.
   */
  HCRATIO_UB_HCRATIO_LB(50, "OTC heat curve ratio upper & lower bounds for adjustment"),

  /**
   * DHW setpoint (°C) (Remote parameter 1).
   */
  TDHWSET(56, "DHW setpoint (°C) (Remote parameter 1)"),

  /**
   * Max CH water setpoint (°C) (Remote parameters 2).
   */
  MAXTSET(57, "Max CH water setpoint (°C) (Remote parameters 2)"),

  /**
   * OTC heat curve ratio (°C) (Remote parameter 3).
   */
  HCRATIO(58, "OTC heat curve ratio (°C) (Remote parameter 3)"),

  /**
   * Function of manual and program changes in master and remote.
   */
  REMOTE_OVERRIDE_FUNCTION(100, "Function of manual and program changes in master and remote"),

  /**
   * OEM-specific diagnostic/service code.
   */
  OEM_DIAGNOSTIC_CODE(115, "OEM-specific diagnostic/service code"),

  /**
   * Number of starts burner.
   */
  BURNER_STARTS(116, "Number of starts burner"),

  /**
   * Number of starts CH pump.
   */
  CH_PUMP_STARTS(117, "Number of starts CH pump"),

  /**
   * Number of starts DHW pump/valve.
   */
  DHW_PUMPVALVE_STARTS(118, "Number of starts DHW pump/valve"),

  /**
   * Number of starts burner during DHW mode.
   */
  DHW_BURNER_STARTS(119, "Number of starts burner during DHW mode"),

  /**
   * Number of hours that burner is in operation (i.e. flame on).
   */
  BURNER_OPERATION_HOURS(120, "Number of hours that burner is in operation (i.e. flame on)"),

  /**
   * Number of hours that CH pump has been running.
   */
  CH_PUMP_OPERATION_HOURS(121, "Number of hours that CH pump has been running"),

  /**
   * Number of hours that DHW pump has been running or DHW valve.
   */
  DHW_PUMPVALVE_OPERATION_HOURS(122, "Number of hours that DHW pump has been running or DHW valve"),

  /**
   * Number of hours that burner is in operation during DHW mode.
   */
  DHW_BURNER_OPERATION_HOURS(123, "Number of hours that burner is in operation during DHW mode"),

  /**
   * The implemented version of the OpenTherm Protocol Specification.
   */
  OPENTHERM_VERSION_MASTER(124, "The implemented version of the OpenTherm Protocol Specification"),

  /**
   *
   */
  OPENTHERM_VERSION_SLAVE(125, "The implemented version of the OpenTherm Protocol Specification"),

  /**
   * The implemented version of the OpenTherm Protocol Specification.
   */
  MASTER_VERSION(126, "Master product version number and type"),

  /**
   * Slave product version number and type.
   */
  SLAVE_VERSION(127, "Slave product version number and type");

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
   * Initializes a new instance of the enumeration.
   *
   * @param key Key of the enumeration value
   * @param description Description of the enumeration value.
   */
  DataId(final int key, final String description) {
    this.key = key;
    this.description = description;
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
}
