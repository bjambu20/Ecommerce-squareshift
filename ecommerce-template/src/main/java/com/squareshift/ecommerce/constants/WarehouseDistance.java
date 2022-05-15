package com.squareshift.ecommerce.constants;

public enum WarehouseDistance {
    FIVE(5L), TWENTY(20L), FIFTY(50L), FIVE_HUNDRED(500L), EIGHT_HUNDRED(800L);

    private Long numVal;

    WarehouseDistance(Long numVal) {
        this.numVal = numVal;
    }

    public Long getNumVal() {
        return numVal;
    }
}
