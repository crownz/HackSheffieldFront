package io.mlh.services;

import io.mlh.objects.Metadata;
import io.mlh.utilities.DisplayDataProcessUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SystemStateService {

    private Object displayData;

    private Metadata displayMetadata;

    public SystemStateService() {
        this.displayMetadata = null;
        this.displayData = null;
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
}
