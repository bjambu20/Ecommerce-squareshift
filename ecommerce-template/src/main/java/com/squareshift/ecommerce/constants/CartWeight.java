package com.squareshift.ecommerce.constants;

public enum CartWeight {
    TWO(2L), FIVE(5L), TWENTY(20L);

    private Long numVal;

    CartWeight(Long numVal) {
        this.numVal = numVal;
    }

    public Long getNumVal() {
        return numVal;
    }
}
