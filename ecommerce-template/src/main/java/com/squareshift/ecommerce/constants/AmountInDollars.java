package com.squareshift.ecommerce.constants;

public enum AmountInDollars {
    TWELVE(12), FIFTEEN(15), TWENTY(20), FIFTY(50), HUNDRED(100), TWO_TWENTY(220),
    FOURTEEN(14), EIGHTEEN(18), TWENTY_FOUR(24), FIFTY_FIVE(55), ONE_HUNDERED_TEN(110), TWO_FIFTY(250),
    SIXTEEN(16), TWENTYFIVE(25), THIRTY(30), EIGHTY(80), ONE_HUNDERED_THIRTY(130), TWO_SENTY(270),
    TWENTY_ONE(21), THIRTY_FIVE(35), NINTY(90), ONE_HUNDERED_FIFTY(150), THREE_HUNDRED(300);

    private int numVal;

    AmountInDollars(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
