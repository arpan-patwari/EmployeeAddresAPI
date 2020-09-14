# arpan-repo



/**************************Employee- Address REST API****************************/

The project can be imported from the git repository https://github.com/arpan-patwari/EmployeeAddressAPI.git
The development has been done in elipse.
The jar file for the project is created in /target/ folder.
You can explicitly run the jar file



*****************************************************************************************

base uri:- http://<domain_name>/api/

For making changes to the "Employee" entity

1) Creating Employee:-

http://localhost:8080/api/employee/createEmployee

Request body:-

{
    "name": "<employee_name>",
    "dateOfBirth": "<YYYY-MM-DD>",
    "associatedEmpAddress": [
        {
            "address": {
                "addrLineOne": "<address_line_one>",
                "addrLineTwo": "<address_line_two>",
                "city": "<city_name>"
            },
            "addressType": "<address_type>"
        }
    ]
}



Response Body:-


{    "id":<id>	
    "name": "<employee_name>",
    "dateOfBirth": "<YYYY-MM-DD>",
    "associatedEmpAddress": [
        {	id":<id>,	
            "address": {
                "addrLineOne": "<address_line_one>",
                "addrLineTwo": "<address_line_two>",
                "city": "<city_name>"
            },
            "addressType": "<address_type>"
        }
    ]
}


Error:
Header:= { status:500 } 
Body={ message:"Failed to create employee"}



2) Viewing Employee:- http://localhost:8080/api/employee/getEmployee/<employee_id>

Response Header: {status:200}
Error:-{status:404}


3) Deleting employee:- http://localhost:8080/api/employee/deleteEmployee/<employee_id>


response: 
header = {status : 200}

error:
header = {status : 404}


4) Updating Employee:- http://localhost:8080/api/employee/updateEmployee/<employee_id>


Request body:-

{
    "name": "<employee_name>",
    "dateOfBirth": "<YYYY-MM-DD>",
    "associatedEmpAddress": [
        {
            "address": {
                "addrLineOne": "<address_line_one>",
                "addrLineTwo": "<address_line_two>",
                "city": "<city_name>"
            },
            "addressType": "<address_type>"
        }
    ]
}



error:
header = {status : 404}

========================================================================================================

For making changes to the "ADDRESS" entity

URL:- http://localhost:8080/api/address/createAddress

1) Creating ADDRESS:-

Request Body:-
{
        "addrLineOne": "<data>",
        "addrLineTwo": "<data>",
        "city": "<city>",
        "associatedEmpAddress": [
            {
                "employee": {
                    "name": "<employe_name>",
                    "dateOfBirth": "<YYYY-MM-DD>"
                },
                "addressType": "<DATA>"
            }
        
        ]
    }


2) Viewing Address- http://localhost:8080/api/address/getAdress/<address_id>

Response Header: {status:200}

Response Body:-

{
	"id":<id>
        "addrLineOne": "<data>",
        "addrLineTwo": "<data>",
        "city": "<city>",
        "associatedEmpAddress": [
            {
                "employee": {
		      "id":"<id>"
                    "name": "<employe_name>",
                    "dateOfBirth": "<YYYY-MM-DD>"
                },
                "addressType": "<DATA>"
            }
        
        ]
    }




Error:-{status:404}


3) Deleting employee:- http://localhost:8080/api/address/deleteAddress/<employee_id>


response: 
header = {status : 200}

error:
header = {status : 404}


4) Update the address by Id:- http://localhost:8080/api/address/updateAddress/{id}

{
        "addrLineOne": "<data>",
        "addrLineTwo": "<data>",
        "city": "<city>",
        "associatedEmpAddress": [
            {
                "employee": {
		      "id":"<id>"
                    "name": "<employe_name>",
                    "dateOfBirth": "<YYYY-MM-DD>"
                },
                "addressType": "<DATA>"
            }
        
        ]
    }

response: 
header = {status : 200}

error:
header = {status : 404}



5) Update the address partially :-  http://localhost:8080/api/employee/updateAddressPartiallly

(update only the field to update. Address Id is mandatory):

{
	"id": <id>
 	"addrLineOne": "<data>",
        "addrLineTwo": "<data>",
        "city": "<city>",
        "associatedEmpAddress": [
            {
                "employee": {
	            "name": "<employe_name>",
                    "dateOfBirth": "<YYYY-MM-DD>"
                },
                "addressType": "<DATA>"
            }
        
        ]
    }

response: 
header = {status : 200}

error:
header = {status : 404}

