Feature: Loan Settlement Maturity Validation

  Background:
    Given the settlement cut-off limit is configured to 5 days

  Scenario: Settlement rejected because it is too close to maturity
    # User Logic: Submitted < 5 days before maturity -> NOT DONE
    Given a loan account "LOAN-101" exists with maturity date "2025-12-10"
    When a settlement request is submitted on "2025-12-08"
    # Logic: 2025-12-10 minus 2025-12-08 = 2 Days. 2 < 5.
    Then the settlement status should be "NOT_DONE"

  Scenario: Settlement accepted because it is well in advance
    # User Logic: Submitted > 30 days (example) -> DONE
    Given a loan account "LOAN-102" exists with maturity date "2025-01-01"
    When a settlement request is submitted on "2025-06-01"
    # Logic: ~6 months difference. > 5.
    Then the settlement status should be "DONE"