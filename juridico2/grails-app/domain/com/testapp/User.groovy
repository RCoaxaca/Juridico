package com.testapp
import catalogos.Venta

class User { 

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
        String apellpa
        String apellma
        String nombre
        
        
        
    
    static mappedBy = [usuario:'cap',usuario:'dic'] 
    static hasMany =[usuario:Venta]
     
         String toString(){
                return username
            }
            
	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
