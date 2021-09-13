# TrueMovie

## How to run
You will require docker to run this project.
A simple `docker-compose up` should build and run the project

Note: The build and run will require some time as the image is quite big and the data needs to be copied.

## View Results
Once the docker is up and running, it will require some time to produce the results.

Results will only be visible in the database once you have this message in the console:

`true-layer-challenge_app_1 exited with code 0`

Please note that the logger isn't working as expected and you may not find printed logs,
please wait for some time after you start the application to find data in the database.

You can view the result in adminer, which will be running in your localhost on port `8080`

`System : PostgreSQL`

`Server : postgres`

`username: postgres`

`password: postgres`

`db: postgres`

You can try accessing the adminer UI directly from here 

`http://localhost:8080/?pgsql=postgres&username=postgres&db=postgres&ns=public&select=moviewiki`

## Stack

The project has been built on scala and spark. SBT was used for the build

## Consideration

1. The movie_metadata and the ratings file are stored in the same repo. This has been done to avoid the issues in retrieving the file from Kaggle
2. Certain filters have been added to filter the datasets, as some values of revenue and budget made no sense
3. The assignment asked to provide `avg_rating` in the final output, hence I made use of the ratings.csv file as well
4. Only few tests have been written for others a placeholder has been left.

## Structure

I have made sure all the filters, metric calculation and readers are separated in different classes.
This design has been chosen to ensure isolation of logic, reusability and easy maintenance.

Also it has been ensured that proper schema based reading has been used. This is more useful incase of columnar data objects.

Currently a fat jar is used to run the project. The jar is created using `sbt assembly`
and can be found in the deploy folder

## Troubleshoot

Generally with a decent internet connection, the project approximately takes 15 mins.

Results will only be visible in the database once you have this message in the console:

`true-layer-challenge_app_1 exited with code 0`

if you encounter `No space left` error please goto

`Docker` -> `Prefernces` -> `Resources` -> `Increase the memory inder advanced tab`

In case of any other error contact me at `cigink@gmail.com`

## Improvements
1. Further in this project, I would like to divide it into separate modules 
2. Fix the logger to ensure timely messages
3. Write more tests