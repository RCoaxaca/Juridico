class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

//	"/"(view:"/index")
              "/"(controller:"Secure", action: "index")
              "/logout/$action?"(controller: "logout")
          //    "/"(controller:"user", action: "create")                
		"500"(view:'/error')
	}
}
