# Stack that is used
1. Spring-boot
2. Java v11
3. Docker
4. MySQL
5. Kafka

# How to run the service
1. Make sure that you have java and maven installed in your computer
2. Open your terminal and change the directory to this ebanking folder
3. Turn on your docker
4. Create a folder called `db` in the ebanking directory
5. Run `mvn clean install` to install all the dependencies
6. Run the command of `docker compose up -d` to boot up the database and the kafka
7. Run `mvn spring-boot:run` to run the service

# Project Structure
The Project is structured based on the layer
1. `configuration` is where the configuration located like kafka, database, etc. that we need to configure other than the default
2. `controllers` is where all the endpoints located
3. `dao` is where all the data object is located
4. `repository` is where all the SQL query to the database is located
5. `service` is where all the main logic is located based on the fuctionality

# Decission making
1. No test cases since to be honest I'm not really familiar with how to make it
2. The database is using the MySQL since the data is quite straight forward and the it's easier to save the data
3. The kafka message structure is as below. This structure will make it easier to do some calculation and it's more clear if we want to add or deduct the account balance
```
{
    "transactionId": "89d3o179-blbc-465b-o9ee-e2d5f6ofEld46",
    "amount": 100,
    "currency": "GBP",
    "type": "credit",
    "accountIBAN": "CH93-0000-0000-0000-0000-0",
    "date": "2022-08-03",
    "description": "transfer"
}
```
4. Since the response of each statement page required the current exchange rates from third-party, I use the https://exchangerate.host/#/ as the currency exchange since it's free and we can get the latest exchange rates
