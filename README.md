# CSE548-Project4 (Phishing Simulation)
Web app to automate infrastructure setup for cybersecurity awareness evaluation in an organization

This project mainly aims to increase phishing awareness by providing a customized assessment to assess people's action in any given situation thereby improving an organization's ability to understand the level of cybersecurity awareness among its employees. 

## What?

 - One of the objective of organizations carrying out red team assessment is to know the weakness in the IT ecosystem which includes people and network. Organizations take every effort to improve there perimeter security and patch the vulnerabilities found but people remain the weakest link. Phishing plays vital role in understanding the security-awareness of employee. 
- With this tool, an organization can set up a customised phishing website that looks very similar to the organization's website in order to evaluate the level of cybersecurity awareness among its employees. Once the website is setup, the admin can send emails with the link to the phishing website to the employees in the organization with one click. A REST API endpoint is exposed to show which employee actually clicks the link and logins on the phishing website. Once it is known which users are not that aware of possible cybersecurity attacks, the organization can spend resources to train them.

## How to use?
This project is essentially a web app which has been deployed here: https://announcement-admin.herokuapp.com/
Note that one can download the code and run the app on local machine as well although that would require JDK 11 and Maven installed. The steps to run the application on a local machine are provided in one of the following sections.

Step 1: As an admin who is responsible for carrying out the entire exercise of evaluating cybersescurity awareness, you will go to https://announcement-admin.herokuapp.com/admin-console and enter the emails of all the employees that need to be evaluated. In the message section, enter the email body. Include a link to the fake website set up that looks exactly like the organization's website to increase the likelihood of people falling for this. 

Step 2: All employees in the email list will receive an email with the content that was provided in the "message" box in the previous step. A naive employee will enter his/her credentials in the login form. Once they hit submit, they will be notified that they have falled prey to a phishing attack and will be asked to go through government provided cybersecurity training. 

Step 3: Admins can collect data about the employees who fell for the trap using the https://announcement-admin.herokuapp.com/data endpoint and may determine to help these specific employees improve their cybersecurity awareness.

## Installation steps
1. Ensure JDK 11 (https://www.azul.com/downloads/?package=jdk) is installed
2. Ensure Maven (apache-maven-3.8.4) is installed
3. Run "mvn clean install" in the root folder to build the project
4. Run "mvn spring-boot:run" to start the server. The server will listen at port 8080 on localhost
