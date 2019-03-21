Feature: Checking parcels

  Scenario Outline: Check parcel by id
    Then Parcel <parcelId> exists

    Examples:
      | parcelId                 |
      | 661131629931422029072715 |
      | 661131629931422029072716 |
