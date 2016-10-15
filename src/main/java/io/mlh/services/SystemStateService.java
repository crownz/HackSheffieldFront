package io.mlh.services;

import io.mlh.objects.DisplayMetadata;
import org.springframework.stereotype.Service;

@Service
public class SystemStateService {

    private Iterable displayData;

    private DisplayMetadata displayMetadata;

    public SystemStateService() {
    }

    public Iterable getDisplayData() {
        return displayData;
    }

    public void setDisplayData(Iterable displayData) {
        this.displayData = displayData;
    }

    public DisplayMetadata getDisplayMetadata() {
        return displayMetadata;
    }

    public void setDisplayMetadata(DisplayMetadata displayMetadata) {
        this.displayMetadata = displayMetadata;
    }
}
