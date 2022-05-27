How to execute the code :
    Clone the repository in your local: git clone https://github.com/abisht88/EventLogService.git
    Application.java class is the main class. You can run this class even without providing any log file path, in that case it reads the sample logfile.json within the resources folder.
    You can also provie your own log file as a program argument to the Application.java class.
    
 About EventLogService:
    It is microservice which reads a log file and identifies the events. 
    It calculates the evnt duration and flag the alert for an event taking more than 4 ms.
    It persists the data to HSQLDB.
