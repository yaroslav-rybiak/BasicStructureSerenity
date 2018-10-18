Feature: Creating and deleting students

  Scenario Outline: Create a new student
    When Student <FIRSTNAME> <LASTNAME> <EMAIL> <PROGRAMME> <COURSE01> <COURSE02> is created
    Then Student can be found by id
    When Student is deleted
    Then Student can not be found by id

    Examples:
      | FIRSTNAME | LASTNAME | EMAIL                | PROGRAMME | COURSE01 | COURSE02  |
      | Shep      | Cotton   | scotton0@behance.net | QA        | Java     | WebDriver |
      | Steve     | Tyler    | styler@behance.net   | DEV       | Java     | Spring    |
