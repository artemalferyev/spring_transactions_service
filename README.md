Transactions Service

This project presents a basic transactions service designed to manage user balances and facilitate transfers between accounts.

Features

Get Balance: Retrieve the current balance for a specific user account.
Add Money: Deposit an amount into a user's account.
Make Transfer: Conduct a transfer of funds from one user to another.
API Endpoints

Get balance for a user
Fetch the balance for a particular user by accessing:

GET /balance/{accountId}

Parameters:

accountId: ID of the user account.
Response: Returns the current balance of the user.

Add money to a user account
Add funds to a user's account using the following endpoint:

POST /balance/add

{
    "recipient": <accountId>,
    "amount": <amount>
}

Parameters:

recipient: ID of the user account you're depositing to.
amount: The amount you wish to deposit.
Response: You'll get the updated balance of the user.

Transfer money between users
For transferring money between two users, use:

POST /balance/transfer

{
    "sender": <senderId>,
    "recipient": <recipientId>,
    "amount": <amount>
}

Parameters:

sender: ID of the account sending the money.
recipient: ID of the account receiving the money.
amount: The amount to be transferred.
Response: A successful transfer will return an HTTP 200 status.

Error Handling

This service incorporates basic error handling. For scenarios like missing account IDs or insufficient funds for a transfer, a corresponding error message will be provided.

Future Improvements

Transition to a persistent database from the current in-memory hashmap.
Enhanced error messages complemented by HTTP status codes.
Expansion of the API to offer additional features such as account creation.
Contributions

Contributions are always welcome! Simply fork this project and submit your enhancements through a pull request.

License

This project is open-sourced and available under the MIT License.
