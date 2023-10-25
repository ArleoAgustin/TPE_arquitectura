package app.model.classs;

import java.io.Serializable;
import java.time.LocalDate;

public class Maintenance implements Serializable {

    private Long scooterId;
    private LocalDate timeStartMaintenance;
    private LocalDate timeEndMaintenance;
    private boolean repaired;

    public Maintenance(Maintenance m) {
        this.timeStartMaintenance = m.getTimeStartMaintenance();
        this.timeEndMaintenance = m.getTimeEndMaintenance();
        this.repaired = m.isRepaired();
    }

    public Maintenance() {
    }

    public Long getScooterId() {
        return scooterId;
    }

    public void setScooterId(Long scooterId) {
        this.scooterId = scooterId;
    }

    public LocalDate getTimeStartMaintenance() {
        return timeStartMaintenance;
    }

    public void setTimeStartMaintenance(LocalDate timeStartMaintenance) {
        this.timeStartMaintenance = timeStartMaintenance;
    }

    public LocalDate getTimeEndMaintenance() {
        return timeEndMaintenance;
    }

    public void setTimeEndMaintenance(LocalDate timeEndMaintenance) {
        this.timeEndMaintenance = timeEndMaintenance;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public void setRepaired(boolean repaired) {
        this.repaired = repaired;
    }
}
