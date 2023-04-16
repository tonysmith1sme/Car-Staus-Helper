package android.hardware.bydauto.charging;

import androidx.annotation.Keep;

@Keep
public class ChargingTimerInfo {
    public static final int TIMER_24H_HOUR_INVALID = 24;
    public static final int TIMER_24H_HOUR_MAX = 23;
    public static final int TIMER_24H_HOUR_MIN = 0;
    public static final int TIMER_24H_MINITE_INVALID = 60;
    public static final int TIMER_24H_MINITE_MAX = 59;
    public static final int TIMER_24H_MINITE_MIN = 0;
    public static final int TIMER_CYCLE_DAY_DISABLE = 0;
    public static final int TIMER_CYCLE_DAY_ENABLE = 1;
    public static final int TIMER_SWITCH_INVALID = -1;
    public static final int TIMER_SWITCH_OFF = 0;
    public static final int TIMER_SWITCH_ON = 1;
    public static final int TIMER_TYPE_1 = 3;
    public static final int TIMER_TYPE_2 = 4;
    private int timerSwitch = 0;
    private int timerType = 3;
    private int timerUnitHour = 0;
    private int timerUnitMinite = 0;
    private int timerCycleMon = 0;
    private int timerCycleTue = 0;
    private int timerCycleWed = 0;
    private int timerCycleThu = 0;
    private int timerCycleFri = 0;
    private int timerCycleSat = 0;
    private int timerCycleSun = 0;

    public int getTimerSwitch() {
        return this.timerSwitch;
    }

    public void setTimerSwitch(int timerSwitch) {
        this.timerSwitch = timerSwitch;
    }

    public int getTimerType() {
        return this.timerType;
    }

    public void setTimerType(int timerType) {
        this.timerType = timerType;
    }

    public int getTimerUnitHour() {
        return this.timerUnitHour;
    }

    public void setTimerUnitHour(int timerUnitHour) {
        this.timerUnitHour = timerUnitHour;
    }

    public int getTimerUnitMinite() {
        return this.timerUnitMinite;
    }

    public void setTimerUnitMinite(int timerUnitMinite) {
        this.timerUnitMinite = timerUnitMinite;
    }

    public int getTimerCycleMon() {
        return this.timerCycleMon;
    }

    public void setTimerCycleMon(int timerCycleMon) {
        this.timerCycleMon = timerCycleMon;
    }

    public int getTimerCycleTue() {
        return this.timerCycleTue;
    }

    public void setTimerCycleTue(int timerCycleTue) {
        this.timerCycleTue = timerCycleTue;
    }

    public int getTimerCycleWed() {
        return this.timerCycleWed;
    }

    public void setTimerCycleWed(int timerCycleWed) {
        this.timerCycleWed = timerCycleWed;
    }

    public int getTimerCycleThu() {
        return this.timerCycleThu;
    }

    public void setTimerCycleThu(int timerCycleThu) {
        this.timerCycleThu = timerCycleThu;
    }

    public int getTimerCycleFri() {
        return this.timerCycleFri;
    }

    public void setTimerCycleFri(int timerCycleFri) {
        this.timerCycleFri = timerCycleFri;
    }

    public int getTimerCycleSat() {
        return this.timerCycleSat;
    }

    public void setTimerCycleSat(int timerCycleSat) {
        this.timerCycleSat = timerCycleSat;
    }

    public int getTimerCycleSun() {
        return this.timerCycleSun;
    }

    public void setTimerCycleSun(int timerCycleSun) {
        this.timerCycleSun = timerCycleSun;
    }
}