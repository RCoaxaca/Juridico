package com.testapp

import org.springframework.dao.DataIntegrityViolationException

class UserRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userRoleInstanceList: UserRole.list(params), userRoleInstanceTotal: UserRole.count()]
    }

    def create() {
        [userRoleInstance: new UserRole(params)]
    }

    def save() {
        def userRoleInstance = new UserRole(params)
        if (!userRoleInstance.save(flush: true)) {
            render(view: "create", model: [userRoleInstance: userRoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userRole.label', default: 'UserRole'), userRoleInstance.id])
        redirect(action: "show", id: userRoleInstance.id)
    }
    
    def save2(Long iduser,Long idrole)
    {
        render "Controlador de pruebas..."
        println(iduser+" id de usuario")
        println(idrole+" id del rol")
        //def userRoleInstance = new UserRole(params)
        def usuario=User.get(iduser)
        def rol=Role.get(idrole)
        def userRoleInstance = new UserRole()
        userRoleInstance.user=usuario
        userRoleInstance.role=rol
        //userRoleInstance.save()
        
        
        if (!userRoleInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }
        
        
        //flash.message = "Usuario " + userInstance.username + " creado correctamente"
        println(usuario.username+" /*--*//*--*/")
        redirect(controller:'user',action:'creado')
        
    }

    def show(Long id) {
        def userRoleInstance = UserRole.get(id)
        if (!userRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), id])
            redirect(action: "list")
            return
        }

        [userRoleInstance: userRoleInstance]
    }

    def edit(Long id) {
        def userRoleInstance = UserRole.get(id)
        if (!userRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), id])
            redirect(action: "list")
            return
        }

        [userRoleInstance: userRoleInstance]
    }

    def update(Long id, Long version) {
        def userRoleInstance = UserRole.get(id)
        if (!userRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userRoleInstance.version > version) {
                userRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userRole.label', default: 'UserRole')] as Object[],
                          "Another user has updated this UserRole while you were editing")
                render(view: "edit", model: [userRoleInstance: userRoleInstance])
                return
            }
        }

        userRoleInstance.properties = params

        if (!userRoleInstance.save(flush: true)) {
            render(view: "edit", model: [userRoleInstance: userRoleInstance])
            return
        }

        flash.message = "La contraseña se actualizo correctamente"
        redirect(action: "show", id: userRoleInstance.id)
    }

    def delete(Long id) {
        def userRoleInstance = UserRole.get(id)
        if (!userRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), id])
            redirect(action: "list")
            return
        }

        try {
            userRoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userRole.label', default: 'UserRole'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userRole.label', default: 'UserRole'), id])
            redirect(action: "show", id: id)
        }
    }
}
