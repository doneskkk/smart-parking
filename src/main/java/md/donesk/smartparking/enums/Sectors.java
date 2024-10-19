package md.donesk.smartparking.enums;

import lombok.Getter;

@Getter
public enum Sectors {
    ZONE1(5.0), ZONE2(10.0), ZONE3(15.0);

    private final double price;

    Sectors(double price) {
        this.price = price;
    }

}
