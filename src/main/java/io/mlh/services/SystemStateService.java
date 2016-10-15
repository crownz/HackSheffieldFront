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
        return new DisplayDataProcessUtils(displayMetadata.getDisplayElementConfig().getGroupedBy()).process(displayData, displayMetadata);
    }

    public void setDisplayData(Collection displayData) {
        this.displayData = displayData;
    }

    public Metadata getDisplayMetadata() {
        return displayMetadata;
    }

    public void setDisplayMetadata(Metadata displayMetadata) {
        this.displayMetadata = displayMetadata;
    }
}
