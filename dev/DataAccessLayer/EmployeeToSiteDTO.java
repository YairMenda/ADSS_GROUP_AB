package DataAccessLayer;


import java.time.LocalDateTime;

    public class EmployeeToSiteDTO {

        private String address;
        private String employeeID;


        private EmployeeToSiteController controller;



        public EmployeeToSiteDTO(String address, String employeeID){
            this.address = address;
            this.employeeID= employeeID;
            controller = new EmployeeToSiteController();
        }
        public String getAddress() {
            return address;
        }

        public String getEmployeeID() {
            return employeeID;
        }


    }

