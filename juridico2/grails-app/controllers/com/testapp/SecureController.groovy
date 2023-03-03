package com.testapp

import grails.plugins.springsecurity.Secured

class SecureController {

   @Secured(['ROLE_ADMIN','ROLE_VENTA','ROLE_DICTAMI','ROLE_VALI','ROLE_USER','ROLE_ARCHIVO']) 
   // si evalua si es administador o no 
    def index() { 
       // render 'Secure access only'
        if (isLoggedIn()) {
       redirect(controller:"user", action:"valida")
        }
        else
        {
            redirect(controller: "Logout")
        }
    }

}
