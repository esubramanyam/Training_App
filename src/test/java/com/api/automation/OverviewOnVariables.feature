Feature: To validate variables

  Background:
    Given def district = "chittoor"

  Scenario: Verify variable data Type
    Given def name = "Tej"
    When def phone_number = "1000"
    Then print "Candidate name: ", name, " with phone number: ", phone_number


  Scenario: To verify variables are reusable
    Then print  district, " district"