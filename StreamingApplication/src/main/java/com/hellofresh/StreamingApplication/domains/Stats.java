package com.hellofresh.StreamingApplication.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stats {
    private long total;
    private double sumX;
    private double avgX;
    private long totalX;
    private long sumY;
    private double avgY;
    private long totalY;

    public void addEvent(Event event) {
        this.sumX = this.sumX + event.getX();
        this.totalX = this.totalX + 1;
        this.avgX = this.sumX / this.totalX;

        this.sumY = this.sumY + event.getY();
        this.totalY = this.totalY + 1;
        this.avgY = (double) this.sumY / this.totalY;

        this.total = this.total + 1;
    }

    public void addStats(Stats stats) {
        this.sumX = this.sumX + stats.getSumX();
        this.totalX = this.totalX + stats.getTotalX();
        this.avgX = this.sumX / this.totalX;

        this.sumY = this.sumY + stats.getSumY();
        this.totalY = this.totalY + stats.getTotalY();
        this.avgY = (double) this.sumY / this.totalY;

        this.total = this.total + stats.getTotal();
    }
}
