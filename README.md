# CreditSaisonMachineCoding

###Saison-Omni Coding Challenge<br>
Backend<br>
Please complete the following coding challenge within one week. Please follow these guidelines<br>
 **Host code on GitHub**<br>
 **Host product on EC2 or Heroku (both offer free tiers).**<br>
 **Your code should stand alone include a good readme / any libraries or dependencies
should be codified into configuration and / or be built up using scripts that you provide.**<br>
 **Please list out any assumptions you have made**<br>
Why?<br>
The coding challenge gives us a chance to review your thought process before we move<br>
forward. Expect questions about it during interviews and expect to discuss design and coding<br>
choices. If you have questions about the challenge, feel free to reach out to your Saison-Omni<br>
contact.<br>
Backend Challenge<br>
Given the data about Food Trucks in San Francisco : here (https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat) <br>
You will build an API that allows the following operations on this data set<br>
 **Search by name of applicant**<br>
 **Search by expiration date, to find whose permits have expired**<br>
 **Search by street name**<br>
 **Add new food truck entry to the dataset**<br>
 **Given a delivery location, find out the closest truck possible.**<br>

Assumptions:
1. Search by street name functionality => street name is mapped to locationDescription column in the csv
2. Search by expiration date, to find whose permits have expired => Functionality is implemented such that if expirationdate is not mentioned, search will be done only by status EXPIRED
3. Add new food truck entry to the dataset => Only locationId and permit are marked as mandatory field i.e request will be successful only if these 2 fields are present at least, others may or may not be present

How to make the requests:
1. Install postman
2. Import postman collection from json - https://github.com/IshanRTripathi/CreditSaisonMachineCoding/blob/master/src/main/resources/static/CreditSaisonMachineCoding.postman_collection.json or from link - https://www.getpostman.com/collections/b15c9bd9161aac42af48, any would work
3. Hit the endpoints to get the expected response along with the correct response code
4. There's an additional endpoint to upload new dataset(csv file) to the database
5. Link to supporting screenshots - https://github.com/IshanRTripathi/CreditSaisonMachineCoding/tree/master/src/main/resources/screenshots

Other functionalities - 
1. Added Validator classes to validate POST requests
2. Added Junit test cases
3. Added functionality to save data from csv files
4. Added other endpoints like - 
   1. get list(of defined size) of permits
   2. Expired permits can search based on status and status + expirationDate
