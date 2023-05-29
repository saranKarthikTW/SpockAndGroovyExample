package com.example.hostelallocation.entity.amenitytypes;

public enum PowerBackup {

    BACKUP_WITH_AC(1500),
    BACKUP_WITHOUT_AC(700),
    NO_BACKUP(0);

    public final int cost;

    PowerBackup(int cost) {
        this.cost = cost;
    }
}
