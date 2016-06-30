@US21977
Feature: US21977

  Scenario Outline: US21977
    #Step -> 1:
    Given I am on the login screen
    Then I Enter the username "<userName>" and password "<password>"
    When i click on "Submit" button
    #Step -> 2:
    When i select "Customer Name" text in "Search By:" dropdown
    When i enter the text "<searchText>" in "Search" inputTextbox
    When i click on "Search" button
    #Step -> 3:
    When i select "9826298" present under "CCID" coloumn
    #Step -> 4:
    When i click on "New Order" button
    When i click on "Accounts" panel
    #Step -> 5:
    When i click on "Get Customer Accounts" button
    Then wait for 2 seconds
    When i click on "Search" button
    When i click on "<customerAccNum>" checkbox
    When i click on "Close" button
    Then wait for 2 seconds
    When i select "<customerAccNum>" present under "Customer Account ID" coloumn
    #Step -> 5:
    When i click on "Get Product Accounts" button
    Then wait for 2 seconds
    When i click on "Search" button
    When i click on "<productAccID>" checkbox
    When i click on "Close" button
    #Step -> 6:
    When i select "<customerName> : <customerAccNum>" text in 1st "Customer Account" dropdown
    When i select "<productAccName> : <productAccID>" text in 1st "Product Account" dropdown
    When i select "<customerName> : <customerAccNum>" text in 2st "Customer Account" dropdown
    When i select "<productAccName> : <productAccID>" text in 2st "Product Account" dropdown
    #Step -> 5:
    When i click on "Get Customer Accounts" button
    Then wait for 2 seconds
    When i click on "Search" button
    When i click on "<customerAccNum2>" checkbox
    When i click on "Close" button
    Then wait for 2 seconds
    When i select "<customerAccNum2>" present under "Customer Account ID" coloumn
    #Step -> 5:
    When i click on "Get Product Accounts" button
    Then wait for 2 seconds
    When i click on "Search" button
    When i click on "<productAccID2>" checkbox
    When i click on "Close" button
    #Step -> 6:
    When i select "<customerName> : <customerAccNum2>" text in 1st "Customer Account" dropdown
    When i select "<customerName> : <productAccID2>" text in 1st "Product Account" dropdown
    When i select "<customerName> : <customerAccNum2>" text in 2st "Customer Account" dropdown
    When i select "<customerName> : <productAccID2>" text in 2st "Product Account" dropdown
    When i click on "Next" button

    Examples: 
      | userName | password | searchText | customerAccNum | productAccID | customerName  | productAccName | customerAccNum2 | productAccID2 |
      | upadmin  | upadmin  | song4      |       71751220 |    130561475 | CUTTER & BUCK | DC INTERNET    |        71751199 |     130519456 |
