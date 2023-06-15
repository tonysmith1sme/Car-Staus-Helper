package com.huawei.carstatushelper;

public class DataHolder {
    private String chargePower = "0";//充电功率
    private String chargeVolt = "410";
    private String chargeCurrent = "39.3";
    private String chargeGunConnectState = "未连接";
    private String chargerConnectState = "未连接";
    private String chargeRestHour = "0";
    private String chargeRestMinute = "28";
    //    private String chargerState = "0";
    private String currentGearboxLevel = "P";//变速箱挡位
    private String currentWindLevel = "0";
    private String currentTemperature = "26";
    private String energyMode = "HEV";//能耗模式
    private String operationMode = "ECO";//驾驶模式
    private String totalMileage = "26158";
    private String totalHevMileage = "6690";
    private String totalFuelCost = "367.5";//累计燃油消耗
    private String totalElecCost = "2152.1";//累计电量消耗
    private String lastFuelConPhm = "3.6";//最近50公里油耗
    private String lastElecConPhm = "0";//最近50公里电耗
    private String powerMileage = "73";
    private String fuelMileage = "1100";
    private String fuelPb = "99";
    private int fuelPercent = 99;
    private String elecPb = "69";
    private int elecPercent = 69;
    private String enginePower;//功率
    private String totalElecConPhm = "7.6";//累计平均电耗
    private String totalFuelConPhm = "1.4";//累计平均油耗
    private String text;//vin
    private String totalEvMileage = "19468";
    private String carSpeed = "0";//车速
    private String engineSpeed = "0";
    private String youMeng = "0";
    private String shaChe = "0";
    private String gearboxCode = "DHT30";//变速箱名称
    private String instantElecCon = "0";
    private String instantFuelCon = "0";
    private String gearboxType = "ECVT";//变速箱类型
    private String engineSpeedGb = "0";
    private String engineSpeedWarning = "0";
    private String frontMotorSpeed = "0";
    private String rearMotorSpeed = "0";
    private String frontMotorTorque = "0";
    private String rearMotorTorque = "0";
    private String tyrePreLeftFront = "240kPa";
    private String tyrePreRightFront = "245kPa";
    private String tyrePreLeftRear = "240kPa";
    private String tyrePreRightRear = "240kPa";
    private String tyrePressure;
    private String autoType = "HA2HE";//车型代号
    private String currentTravelElecCost = "0.0";//本次行程平均电耗
    private String currentTravelFuelCost = "6.667";//本次行程平均油耗
    private String currentTravelMileage = "4";//本次行程总里程
    private String currentTravelEnergyCost = "0.0度+0.2升";//本次行程能耗
    private String currentComprehensiveElecCost = "30.003";//本次行程综合电耗
    private String currentComprehensiveFuelCost = "5.0";//本次行程综合油耗
    private String currentTravelYuanCost = "1.416";
    private String customMileage1 = "8981.6";
    private String customMileage2 = "4982.9";
    private String powerLevel = "ON";//电源挡位
    private String lowestBatterVoltage = "3.415";
    private String highestBatterVoltage = "3.431";
    private String lowestBatterTemp = "36";
    private String highestBatterTemp = "37";
    private String averageBatterTemp = "36";
    private String externalChargingPower = "2341.1";
    private String controlModeStatus;
    private String acOnOffStatus;
    private String compressorStatus;
    private String defrostModeStatus;
    private String cycleModeStatus;
    private String ventilateStatus;
    private String batteryDeviceState = "放电中";
    private String energyFeedback = "标准";
    private String totalDrivingTime = "0";
    private String waterTemperature;

    private String currentChargingCount;

    private String sensorTemperature;
    private String sensorHumidity;
    private String sensorLight;
    private String sensorSlop;

    private String smallBatteryVoltage;
    private String waterMeterPercent;

    public String getChargePower() {
        return chargePower;
    }

    public void setChargePower(String chargePower) {
        this.chargePower = chargePower;
    }

    public String getChargeVolt() {
        return chargeVolt;
    }

    public void setChargeVolt(String chargeVolt) {
        this.chargeVolt = chargeVolt;
    }

    public String getChargeCurrent() {
        return chargeCurrent;
    }

    public void setChargeCurrent(String chargeCurrent) {
        this.chargeCurrent = chargeCurrent;
    }

    public String getChargeGunConnectState() {
        return chargeGunConnectState;
    }

    public void setChargeGunConnectState(String chargeGunConnectState) {
        this.chargeGunConnectState = chargeGunConnectState;
    }

    public String getChargerConnectState() {
        return chargerConnectState;
    }

    public void setChargerConnectState(String chargerConnectState) {
        this.chargerConnectState = chargerConnectState;
    }

    public String getChargeRestHour() {
        return chargeRestHour;
    }

    public void setChargeRestHour(String chargeRestHour) {
        this.chargeRestHour = chargeRestHour;
    }

    public String getChargeRestMinute() {
        return chargeRestMinute;
    }

    public void setChargeRestMinute(String chargeRestMinute) {
        this.chargeRestMinute = chargeRestMinute;
    }

//    public String getChargerState() {
//        return chargerState;
//    }

//    public void setChargerState(String chargerState) {
//        this.chargerState = chargerState;
//    }

    public String getCurrentGearboxLevel() {
        return currentGearboxLevel;
    }

    public void setCurrentGearboxLevel(String currentGearboxLevel) {
        this.currentGearboxLevel = currentGearboxLevel;
    }

    public String getCurrentWindLevel() {
        return currentWindLevel;
    }

    public void setCurrentWindLevel(String currentWindLevel) {
        this.currentWindLevel = currentWindLevel;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getEnergyMode() {
        return energyMode;
    }

    public void setEnergyMode(String energyMode) {
        this.energyMode = energyMode;
    }

    public String getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(String operationMode) {
        this.operationMode = operationMode;
    }

    public String getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(String totalMileage) {
        this.totalMileage = totalMileage;
    }

    public String getTotalHevMileage() {
        return totalHevMileage;
    }

    public void setTotalHevMileage(String totalHevMileage) {
        this.totalHevMileage = totalHevMileage;
    }

    public String getTotalFuelCost() {
        return totalFuelCost;
    }

    public void setTotalFuelCost(String totalFuelCost) {
        this.totalFuelCost = totalFuelCost;
    }

    public String getTotalElecCost() {
        return totalElecCost;
    }

    public void setTotalElecCost(String totalElecCost) {
        this.totalElecCost = totalElecCost;
    }

    public String getLastFuelConPhm() {
        return lastFuelConPhm;
    }

    public void setLastFuelConPhm(String lastFuelConPhm) {
        this.lastFuelConPhm = lastFuelConPhm;
    }

    public String getLastElecConPhm() {
        return lastElecConPhm;
    }

    public void setLastElecConPhm(String lastElecConPhm) {
        this.lastElecConPhm = lastElecConPhm;
    }

    public String getPowerMileage() {
        return powerMileage;
    }

    public void setPowerMileage(String powerMileage) {
        this.powerMileage = powerMileage;
    }

    public String getFuelMileage() {
        return fuelMileage;
    }

    public void setFuelMileage(String fuelMileage) {
        this.fuelMileage = fuelMileage;
    }

    public String getFuelPb() {
        return fuelPb;
    }

    public void setFuelPb(String fuelPb) {
        this.fuelPb = fuelPb;
    }

    public int getFuelPercent() {
        return fuelPercent;
    }

    public void setFuelPercent(int fuelPercent) {
        this.fuelPercent = fuelPercent;
    }

    public String getElecPb() {
        return elecPb;
    }

    public void setElecPb(String elecPb) {
        this.elecPb = elecPb;
    }

    public int getElecPercent() {
        return elecPercent;
    }

    public void setElecPercent(int elecPercent) {
        this.elecPercent = elecPercent;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getTotalElecConPhm() {
        return totalElecConPhm;
    }

    public void setTotalElecConPhm(String totalElecConPhm) {
        this.totalElecConPhm = totalElecConPhm;
    }

    public String getTotalFuelConPhm() {
        return totalFuelConPhm;
    }

    public void setTotalFuelConPhm(String totalFuelConPhm) {
        this.totalFuelConPhm = totalFuelConPhm;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTotalEvMileage() {
        return totalEvMileage;
    }

    public void setTotalEvMileage(String totalEvMileage) {
        this.totalEvMileage = totalEvMileage;
    }

    public String getCarSpeed() {
        return carSpeed;
    }

    public void setCarSpeed(String carSpeed) {
        this.carSpeed = carSpeed;
    }

    public String getEngineSpeed() {
        return engineSpeed;
    }

    public void setEngineSpeed(String engineSpeed) {
        this.engineSpeed = engineSpeed;
    }

    public String getYouMeng() {
        return youMeng;
    }

    public void setYouMeng(String youMeng) {
        this.youMeng = youMeng;
    }

    public String getShaChe() {
        return shaChe;
    }

    public void setShaChe(String shaChe) {
        this.shaChe = shaChe;
    }

    public String getGearboxCode() {
        return gearboxCode;
    }

    public void setGearboxCode(String gearboxCode) {
        this.gearboxCode = gearboxCode;
    }

    public String getInstantElecCon() {
        return instantElecCon;
    }

    public void setInstantElecCon(String instantElecCon) {
        this.instantElecCon = instantElecCon;
    }

    public String getInstantFuelCon() {
        return instantFuelCon;
    }

    public void setInstantFuelCon(String instantFuelCon) {
        this.instantFuelCon = instantFuelCon;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public String getEngineSpeedGb() {
        return engineSpeedGb;
    }

    public void setEngineSpeedGb(String engineSpeedGb) {
        this.engineSpeedGb = engineSpeedGb;
    }

    public String getEngineSpeedWarning() {
        return engineSpeedWarning;
    }

    public void setEngineSpeedWarning(String engineSpeedWarning) {
        this.engineSpeedWarning = engineSpeedWarning;
    }

    public String getFrontMotorSpeed() {
        return frontMotorSpeed;
    }

    public void setFrontMotorSpeed(String frontMotorSpeed) {
        this.frontMotorSpeed = frontMotorSpeed;
    }

    public String getRearMotorSpeed() {
        return rearMotorSpeed;
    }

    public void setRearMotorSpeed(String rearMotorSpeed) {
        this.rearMotorSpeed = rearMotorSpeed;
    }

    public String getFrontMotorTorque() {
        return frontMotorTorque;
    }

    public void setFrontMotorTorque(String frontMotorTorque) {
        this.frontMotorTorque = frontMotorTorque;
    }

    public String getRearMotorTorque() {
        return rearMotorTorque;
    }

    public void setRearMotorTorque(String rearMotorTorque) {
        this.rearMotorTorque = rearMotorTorque;
    }

    public String getTyrePreLeftFront() {
        return tyrePreLeftFront;
    }

    public void setTyrePreLeftFront(String tyrePreLeftFront) {
        this.tyrePreLeftFront = tyrePreLeftFront;
    }

    public String getTyrePreRightFront() {
        return tyrePreRightFront;
    }

    public void setTyrePreRightFront(String tyrePreRightFront) {
        this.tyrePreRightFront = tyrePreRightFront;
    }

    public String getTyrePreLeftRear() {
        return tyrePreLeftRear;
    }

    public void setTyrePreLeftRear(String tyrePreLeftRear) {
        this.tyrePreLeftRear = tyrePreLeftRear;
    }

    public String getTyrePreRightRear() {
        return tyrePreRightRear;
    }

    public void setTyrePreRightRear(String tyrePreRightRear) {
        this.tyrePreRightRear = tyrePreRightRear;
    }

    public String getTyrePressure() {
        return tyrePressure;
    }

    public void setTyrePressure(String tyrePressure) {
        this.tyrePressure = tyrePressure;
    }

    public String getAutoType() {
        return autoType;
    }

    public void setAutoType(String autoType) {
        this.autoType = autoType;
    }

    public String getCurrentTravelElecCost() {
        return currentTravelElecCost;
    }

    public void setCurrentTravelElecCost(String currentTravelElecCost) {
        this.currentTravelElecCost = currentTravelElecCost;
    }

    public String getCurrentTravelFuelCost() {
        return currentTravelFuelCost;
    }

    public void setCurrentTravelFuelCost(String currentTravelFuelCost) {
        this.currentTravelFuelCost = currentTravelFuelCost;
    }

    public String getCurrentTravelMileage() {
        return currentTravelMileage;
    }

    public void setCurrentTravelMileage(String currentTravelMileage) {
        this.currentTravelMileage = currentTravelMileage;
    }

    public String getCurrentTravelEnergyCost() {
        return currentTravelEnergyCost;
    }

    public void setCurrentTravelEnergyCost(String currentTravelEnergyCost) {
        this.currentTravelEnergyCost = currentTravelEnergyCost;
    }

    public String getCurrentComprehensiveElecCost() {
        return currentComprehensiveElecCost;
    }

    public void setCurrentComprehensiveElecCost(String currentComprehensiveElecCost) {
        this.currentComprehensiveElecCost = currentComprehensiveElecCost;
    }

    public String getCurrentComprehensiveFuelCost() {
        return currentComprehensiveFuelCost;
    }

    public void setCurrentComprehensiveFuelCost(String currentComprehensiveFuelCost) {
        this.currentComprehensiveFuelCost = currentComprehensiveFuelCost;
    }

    public String getCurrentTravelYuanCost() {
        return currentTravelYuanCost;
    }

    public void setCurrentTravelYuanCost(String currentTravelYuanCost) {
        this.currentTravelYuanCost = currentTravelYuanCost;
    }

    public String getCustomMileage1() {
        return customMileage1;
    }

    public void setCustomMileage1(String customMileage1) {
        this.customMileage1 = customMileage1;
    }

    public String getCustomMileage2() {
        return customMileage2;
    }

    public void setCustomMileage2(String customMileage2) {
        this.customMileage2 = customMileage2;
    }

    public String getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(String powerLevel) {
        this.powerLevel = powerLevel;
    }

    public String getLowestBatterVoltage() {
        return lowestBatterVoltage;
    }

    public void setLowestBatterVoltage(String lowestBatterVoltage) {
        this.lowestBatterVoltage = lowestBatterVoltage;
    }

    public String getHighestBatterVoltage() {
        return highestBatterVoltage;
    }

    public void setHighestBatterVoltage(String highestBatterVoltage) {
        this.highestBatterVoltage = highestBatterVoltage;
    }

    public String getLowestBatterTemp() {
        return lowestBatterTemp;
    }

    public void setLowestBatterTemp(String lowestBatterTemp) {
        this.lowestBatterTemp = lowestBatterTemp;
    }

    public String getHighestBatterTemp() {
        return highestBatterTemp;
    }

    public void setHighestBatterTemp(String highestBatterTemp) {
        this.highestBatterTemp = highestBatterTemp;
    }

    public String getAverageBatterTemp() {
        return averageBatterTemp;
    }

    public void setAverageBatterTemp(String averageBatterTemp) {
        this.averageBatterTemp = averageBatterTemp;
    }

    public String getExternalChargingPower() {
        return externalChargingPower;
    }

    public void setExternalChargingPower(String externalChargingPower) {
        this.externalChargingPower = externalChargingPower;
    }

    public String getControlModeStatus() {
        return controlModeStatus;
    }

    public void setControlModeStatus(String controlModeStatus) {
        this.controlModeStatus = controlModeStatus;
    }

    public String getAcOnOffStatus() {
        return acOnOffStatus;
    }

    public void setAcOnOffStatus(String acOnOffStatus) {
        this.acOnOffStatus = acOnOffStatus;
    }

    public String getCompressorStatus() {
        return compressorStatus;
    }

    public void setCompressorStatus(String compressorStatus) {
        this.compressorStatus = compressorStatus;
    }

    public String getDefrostModeStatus() {
        return defrostModeStatus;
    }

    public void setDefrostModeStatus(String defrostModeStatus) {
        this.defrostModeStatus = defrostModeStatus;
    }

    public String getCycleModeStatus() {
        return cycleModeStatus;
    }

    public void setCycleModeStatus(String cycleModeStatus) {
        this.cycleModeStatus = cycleModeStatus;
    }

    public String getVentilateStatus() {
        return ventilateStatus;
    }

    public void setVentilateStatus(String ventilateStatus) {
        this.ventilateStatus = ventilateStatus;
    }

    public String getBatteryDeviceState() {
        return batteryDeviceState;
    }

    public void setBatteryDeviceState(String batteryDeviceState) {
        this.batteryDeviceState = batteryDeviceState;
    }

    public String getEnergyFeedback() {
        return energyFeedback;
    }

    public void setEnergyFeedback(String energyFeedback) {
        this.energyFeedback = energyFeedback;
    }

    public String getTotalDrivingTime() {
        return totalDrivingTime;
    }

    public void setTotalDrivingTime(String totalDrivingTime) {
        this.totalDrivingTime = totalDrivingTime;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getCurrentChargingCount() {
        return currentChargingCount;
    }

    public void setCurrentChargingCount(String currentChargingCount) {
        this.currentChargingCount = currentChargingCount;
    }

    public String getSensorTemperature() {
        return sensorTemperature;
    }

    public void setSensorTemperature(String sensorTemperature) {
        this.sensorTemperature = sensorTemperature;
    }

    public String getSensorHumidity() {
        return sensorHumidity;
    }

    public void setSensorHumidity(String sensorHumidity) {
        this.sensorHumidity = sensorHumidity;
    }

    public String getSensorLight() {
        return sensorLight;
    }

    public void setSensorLight(String sensorLight) {
        this.sensorLight = sensorLight;
    }

    public String getSensorSlop() {
        return sensorSlop;
    }

    public void setSensorSlop(String sensorSlop) {
        this.sensorSlop = sensorSlop;
    }

    public String getSmallBatteryVoltage() {
        return smallBatteryVoltage;
    }

    public void setSmallBatteryVoltage(String smallBatteryVoltage) {
        this.smallBatteryVoltage = smallBatteryVoltage;
    }

    public String getWaterMeterPercent() {
        return waterMeterPercent;
    }

    public void setWaterMeterPercent(String waterMeterPercent) {
        this.waterMeterPercent = waterMeterPercent;
    }
}
