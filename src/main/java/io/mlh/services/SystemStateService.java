package io.mlh.services;

import io.mlh.objects.Metadata;
import io.mlh.utilities.DisplayDataProcessUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SystemStateService {

    private Object displayData;

    private Metadata displayMetadata;

    private boolean sessionStarted;

    public SystemStateService() {
        this.displayMetadata = null;
        this.displayData = null;
        this.sessionStarted = false;
    }

    public Object getDisplayData() {
        if (this.displayData == null) return null;
        return new DisplayDataProcessUtils(displayMetadata.getDisplayElementConfig().getGroupedBy()).process(displayData, displayMetadata);
    }

    public void setDisplayData(Collection displayData) {
        this.displayData = displayData;
    }

    public Metadata getDisplayMetadata() {
        System.out.println("Getting display metadata!");
        return displayMetadata;
    }

    public void setDisplayMetadata(Metadata displayMetadata) {
        System.out.println("Setting display metadata!");
        this.displayMetadata = displayMetadata;
    }

    public boolean isSessionStarted() {
        return sessionStarted;
    }

    public void setSessionStarted(boolean sessionStarted) {
        this.sessionStarted = sessionStarted;
    }

    public void reset() {
        displayData = null;
        displayMetadata = null;
        sessionStarted = false;
    }
}
